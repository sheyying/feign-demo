package com.common.config;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;

/**
 * 注意：该线程池被所有的异步任务共享，而不属于某一个异步任务
 * 描述：配置异步任务的线程池
 * Created by sheying on 2018/06/11.
 */
//@Configuration
public class AsyncTaskExecutePool implements AsyncConfigurer {

    @Override
    public Executor getAsyncExecutor() {
        // 线程池
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        // 当前线程数
        taskExecutor.setCorePoolSize(10);
        // 最大线程数
        taskExecutor.setMaxPoolSize(50);
        // 线程池所使用的缓冲队列
        taskExecutor.setQueueCapacity(25);
        // 等待任务在关机时完成--表明等待所有线程执行完
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        // 等待时间 （默认为0，此时立即停止），并没等待xx秒后强制停止
        taskExecutor.setAwaitTerminationSeconds(60 * 15);
        // 线程名称前缀
        taskExecutor.setThreadNamePrefix("MyAsync-");
        // 初始化
        taskExecutor.initialize();
        return taskExecutor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {// 异步任务中异常处理
        return new AsyncUncaughtExceptionHandler() {
            @Override
            public void handleUncaughtException(Throwable arg0, Method arg1, Object... arg2) {
                System.out.println("===================" + arg0.getMessage() + "=================" + arg0);
                System.out.println("exception method: " + arg1.getName());
            }
        };
    }
}
