package com.interest.community.service.impl;

import com.interest.community.annotation.SonTransaction;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: yanghongxiao
 * @Date: 2023/7/5 15:18
 */
@Service
public class SonService {

    @Transactional(rollbackFor = Exception.class)
    @Async("threadPoolTaskExecutor")
    @SonTransaction
    public void sonMethod1(String args,Thread thread){
        System.out.println(args+"开启了线程");
    }

    @Transactional(rollbackFor = Exception.class)
    @Async("threadPoolTaskExecutor")
    @SonTransaction
    public void sonMethod2(String args1, String args2, Thread thread) {
        System.out.println(args1 + "和" + args2 + "开启了线程");
    }

    @Transactional(rollbackFor = Exception.class)
    @Async("threadPoolTaskExecutor")
    @SonTransaction
    public void sonMethod3(String args, Thread thread) {
        System.out.println(args + "开启了线程");
    }

    //sonMethod4方法没有使用线程池
    @Transactional(rollbackFor = Exception.class)
    public void sonMethod4(String args) {
        System.out.println(args + "没有开启线程");
    }
}
