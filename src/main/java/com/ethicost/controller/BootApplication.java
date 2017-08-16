package com.ethicost.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Created by vrum on 16/8/17.
 */
@SpringBootApplication
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.ethicost.model"})
public class BootApplication {

    private static ConfigurableApplicationContext applicationContext;

    public static void main(String[] args) {
        BootApplication.applicationContext = SpringApplication.run(BootApplication.class, args);
    }
}
