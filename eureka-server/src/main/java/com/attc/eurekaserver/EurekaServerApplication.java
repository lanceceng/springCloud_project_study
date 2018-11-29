package com.attc.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
        System.out.println("打开：http://localhost:8761/");
    }
}

/*
* 其中服务注册中心Eureka Server，是一个实例，
* 当成千上万个服务向它注册的时候，它的负载是非常高的，
* 这在生产环境上是不太合适的，可以扩展将Eureka Server集群化。
* 在eureka-server工程中resources文件夹下，
* 创建配置文件            application-peer1.yml；
* 并且创建另外一个配置文件  application-peer2.yml。
* eureka-server就已经改造完毕
* 按照官方文档的指示，需要改变etc/hosts.ini，linux系统通过vim /etc/hosts ,加上
* 127.0.0.1 peer1
* 127.0.0.1 peer2
* windows电脑，在c:/windows/systems32/drivers/etc/hosts 修改。
*/
