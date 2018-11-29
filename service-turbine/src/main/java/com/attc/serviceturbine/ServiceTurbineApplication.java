package com.attc.serviceturbine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@RestController
@EnableHystrix
@EnableHystrixDashboard
@EnableCircuitBreaker
@EnableTurbine
public class ServiceTurbineApplication {

    /**
     * http://localhost:8764/turbine.stream
     */
    public static void main(String[] args) {
        SpringApplication.run(ServiceTurbineApplication.class, args);
        System.out.println("打开：http://localhost:8764/turbine.stream, 就会ping出相关的监控数据");
        //查看多个服务的监控数据
        System.out.println("依次请求：");
        System.out.println("http://localhost:8762/hi?name=cat ");
        System.out.println("http://localhost:8763/hi?name=dog ");
        System.out.println("打开:http://localhost:8763/hystrix ");
        System.out.println("输入监控流:http://localhost:8764/turbine.stream, 点确定就能看到了。");
        //就可以看到这个页面聚合了2个service的hystrix dashbord数据。
    }
}
