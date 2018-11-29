package com.attc.servicehi;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@RestController
/* 在微服务架构中为例保证程序的可用性，防止程序出错导致网络阻塞，
* 出现了断路器模型。断路器的状况反应了一个程序的可用性和健壮性，
* 它是一个重要指标。Hystrix Dashboard是作为断路器状态的一个组件，
* 提供了数据监控和友好的图形化界面。*/
//下面4个注解都是扩展： 断路器监控(Hystrix Dashboard)
@EnableDiscoveryClient
@EnableHystrix//必须
@EnableCircuitBreaker
@EnableHystrixDashboard//开启HystrixDashboar
public class ServiceHiApplication {

    /**
     * 访问地址 http://localhost:8762/actuator/hystrix.stream
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(ServiceHiApplication.class, args);
        System.out.println("打开：http://localhost:8762/hi?name=cat ");
        //System.out.println("Hystrix Dashboar监控数据：http://localhost:8762/actuator/hystrix.stream"); 有点问题，貌似springCloud版本问题
        //图形界面
        System.out.println("Hystrix Dashboard图形界面地址：http://localhost:8762/hystrix");
        System.out.println("在图形界面依次输入：http://localhost:8762/actuator/hystrix.stream 、2000 、miya；点确定。");
        System.out.println("在另一个窗口输入： http://localhost:8762/hi?name=cat");
        System.out.println("重新刷新hystrix.stream网页，你会看到良好的图形化界面");
    }

    @Bean
    public ServletRegistrationBean getServlet(){
        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
        registrationBean.setLoadOnStartup(1);
        registrationBean.addUrlMappings("/actuator/hystrix.stream");
        registrationBean.setName("HystrixMetricsStreamServlet");
        return registrationBean;
    }


    @Value("${server.port}")
    String port;

    @RequestMapping("/hi")
    @HystrixCommand(fallbackMethod = "hiError")//扩展断路器
    public String home(@RequestParam(value = "name", defaultValue = "cat") String name) {
        return "hi " + name + " ,i am from port:" + port;
    }

    public String hiError(String name) {
        return "hi,"+name+",sorry,error!";
    }
}
