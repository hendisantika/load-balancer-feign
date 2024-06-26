package com.hendisantika.helloservice2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class HelloService2Application {
    private final Logger logger = LogManager.getLogger(HelloService2Application.class);

    @Autowired
    DiscoveryClient discoveryClient;

    @GetMapping("/")
    public String hello() {
        discoveryClient.getServices().forEach(id -> {
            discoveryClient.getInstances(id).forEach(instance -> {
                logger.info("/hello, host:" + instance.getHost() + ", service_id:" + instance.getServiceId());
            });
        });

        return "This is Hello Service 2: " + LocalDateTime.now();
    }

	public static void main(String[] args) {
		SpringApplication.run(HelloService2Application.class, args);
	}
}
