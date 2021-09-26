package com.qf.my.worker.provider;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.qf.my.worker.provider.mapper")
public class MyWorkerProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyWorkerProviderApplication.class, args);
    }

}
