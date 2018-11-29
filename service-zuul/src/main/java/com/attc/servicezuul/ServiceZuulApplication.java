package com.attc.servicezuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
@EnableDiscoveryClient
public class ServiceZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceZuulApplication.class, args);
        System.out.println("依次运行这五个工程;打开浏览器");
        System.out.println("访问：http://localhost:8769/api-a/hi?name=cat ;");
        System.out.println("访问：http://localhost:8769/api-b/hi?name=cat&token=22 ;");
    }
}
