package com.attc.serviceribbon.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HelloService {

    /**
     * 注入restful模板
     */
    @Autowired
    RestTemplate restTemplate;

    /**
     * 要调用的服务
     * @param name
     * @return
     */
    @HystrixCommand(fallbackMethod = "hiError")
    public String hiService(String name) {
        return restTemplate.getForObject("http://SERVICE-HI/hi?name="+name,String.class);
    }

    /*改造HelloService类，在hiService方法上加上@HystrixCommand注解。该注解对该方法创建了熔断器的功能，
    并指定了fallbackMethod熔断方法，熔断方法直接返回了一个字符串，字符串为"hi,"+name+",sorry,error!"。*/

    public String hiError(String name) {
        return "hi,"+name+",sorry,error!";
    }

    /*启动：service-ribbon 工程，当我们访问http://localhost:8764/hi?name=cat,浏览器显示：

    hi forezp,i am from port:8762

    此时关闭 service-hi 工程，当我们再访问http://localhost:8764/hi?name=cat，浏览器会显示：

    hi ,cat,orry,error!*/



}
