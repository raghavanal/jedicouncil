package com.garudaone.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages =   {"com.garudaone.web","com.garudaone.web.repository","com.garudaone.web.model"})
public class WebOneMain {

    public static void main(String args[])
    {
        SpringApplication.run(WebOneMain.class);
    }
}
