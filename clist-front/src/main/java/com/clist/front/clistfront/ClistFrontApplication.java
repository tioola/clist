package com.clist.front.clistfront;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages="com.clist.front.resources")
public class ClistFrontApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClistFrontApplication.class, args);
	}
}
