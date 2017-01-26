package com.judge_server.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@ComponentScan("com.judge_server")
@EnableAsync
public class JudgeServerWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(JudgeServerWebApplication.class, args);
	}
}
