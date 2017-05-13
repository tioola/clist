package com.clist.front.clistfront;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages={"com.clist.repositories.mongo.infra"})
@ComponentScan(basePackages={"com.clist.front.resources",
							 "com.clist.front.security",
							 "com.clist.front.handlers"})
public class ClistFrontApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClistFrontApplication.class, args);
	}
}
