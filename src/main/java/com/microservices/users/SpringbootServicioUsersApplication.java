package com.microservices.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringbootServicioUsersApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootServicioUsersApplication.class, args);
    }

}
