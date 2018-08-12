package com.garudaone.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication(scanBasePackages =   {"com.garudaone.web","com.garudaone.web.repository","com.garudaone.web.model"})
@EnableCaching
public class WebOneMain {

    public static void main(String args[])
    {
        SpringApplication.run(WebOneMain.class);
    }
}
