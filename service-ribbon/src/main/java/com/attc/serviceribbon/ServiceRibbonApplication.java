package com.attc.serviceribbon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@EnableHystrix // 加@EnableHystrix注解开启Hystrix,如果不需要断路器就注释掉
public class ServiceRibbonApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceRibbonApplication.class, args);
        System.out.println("打开：http://localhost:8764/hi?name=cat");
    }

    /*在浏览器上多次访问http://localhost:8764/hi?name=cat,
    浏览器交替显示：
    hi cat,i am from port:8762,
    hi cat,i am from port:8763,
    */

    /*在工程的启动类中,通过@EnableDiscoveryClient向服务中心注册；
    并且向程序的ioc注入一个bean: restTemplate;
    并通过@LoadBalanced注解表明这个restRemplate开启负载均衡的功能。*/
    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
