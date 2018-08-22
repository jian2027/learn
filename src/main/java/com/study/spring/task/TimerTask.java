package com.study.spring.task;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.druid.pool.DruidDataSource;

@Component
public class TimerTask {

    @Scheduled(cron = "0 */5 * * * ?")//每分钟都执行
    public void test(){
        System.out.println("执行"+new Date());
        
    }
}

