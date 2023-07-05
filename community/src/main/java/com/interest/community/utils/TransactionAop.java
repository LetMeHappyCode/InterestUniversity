package com.interest.community.utils;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.interest.community.annotation.MainTransaction;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

@Component
@Aspect
public class TransactionAop {
    //用来存储各线程计数器数据(每次执行后会从map中删除)
    private static final Map<String,Object> map = new HashMap<>();

    @Resource
    private PlatformTransactionManager transactionManager;

    @Around("@annotation(mainTransaction))")
    public void mainIntercept(ProceedingJoinPoint joinPoint , MainTransaction mainTransaction) throws Throwable{
        //当前线程名
        Thread thread = Thread.currentThread();
        String threadName = thread.getName();
        //初始化计数器
        CountDownLatch mainDownLatch = new CountDownLatch(1);
        CountDownLatch sonDownLatch = new CountDownLatch(mainTransaction.value());
        //记录子线程的运行状态，只要有一个失败就变为true
        AtomicBoolean rollBackFlag = new AtomicBoolean(false);
        //保存每个子线程的异常，把每个线程的自定义异常向vector插入，其余异常末尾插入，使用vector代替list
        Vector<Throwable> exceptionVector = new Vector<>();

        map.put(threadName+"mainDownLatch",mainDownLatch);
        map.put(threadName+"sonDownLatch",sonDownLatch);
        map.put(threadName+"rollBackFlag",rollBackFlag);
        map.put(threadName+"exceptionVector",exceptionVector);

        try {
            joinPoint.proceed();
        } catch (Throwable e) {
            exceptionVector.add(0,e);
            rollBackFlag.set(true);
            mainDownLatch.countDown();
        }

        if (!rollBackFlag.get()){
            try {
                sonDownLatch.await();
                mainDownLatch.countDown();
            } catch (InterruptedException e) {
                rollBackFlag.set(true);
                exceptionVector.add(0,e);
            }
        }

        if (CollectionUtils.isNotEmpty(map)) {
            map.remove(threadName+"mainDownLatch");
            map.remove(threadName+"sonDownLatch");
            map.remove(threadName+"rollBackFlag");
            map.remove(threadName+"exceptionVector");
            throw exceptionVector.get(0);
        }
    }

    @Around("@annotation(com.interest.community.annotation.SonTransaction)")
    public void sonIntercept(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        Thread thread = (Thread) args[args.length - 1];
        String threadName = thread.getName();
        CountDownLatch mainDownLatch = (CountDownLatch) map.get(threadName + "mainDownLatch");
        if (mainDownLatch == null) {
            joinPoint.proceed();
            return;
        }
        CountDownLatch sonDownLatch = (CountDownLatch) map.get(threadName + "sonDownLatch");
        AtomicBoolean rollBackFlag = (AtomicBoolean) map.get(threadName + "rollBackFlag");
        Vector<Throwable> exceptionVector = (Vector<Throwable>) map.get(threadName + "exceptionVector");

        if (rollBackFlag.get()) {
            sonDownLatch.countDown();
            return;
        }

        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(DefaultTransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus status = transactionManager.getTransaction(def);

        try {
            joinPoint.proceed();

            sonDownLatch.countDown();
            mainDownLatch.await();

            if (rollBackFlag.get()) {
                transactionManager.rollback(status);
            } else {
                transactionManager.commit(status);
            }
        } catch (Throwable e) {
            exceptionVector.add(0,e);
            transactionManager.rollback(status);
            rollBackFlag.set(true);
            mainDownLatch.countDown();
            sonDownLatch.countDown();
        }
    }


}
