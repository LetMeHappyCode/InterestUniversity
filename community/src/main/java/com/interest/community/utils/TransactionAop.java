package com.interest.community.utils;

import com.interest.community.annotation.MainTransaction;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
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

    }


}
