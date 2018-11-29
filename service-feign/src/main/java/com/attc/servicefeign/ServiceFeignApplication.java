package com.attc.servicefeign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@EnableFeignClients//@EnableFeignClients注解开启Feign的功能
public class ServiceFeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceFeignApplication.class, args);
        System.out.println("打开：http://localhost:8765/hi?name=cat");
    }

    /*启动程序，多次访问http://localhost:8765/hi?name=cat,
    浏览器交替显示：
    hi cat,i am from port:8762,
    hi cat,i am from port:8763,
    */
}
