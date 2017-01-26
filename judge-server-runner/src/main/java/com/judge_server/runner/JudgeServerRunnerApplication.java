package com.judge_server.runner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.judge_server")
public class JudgeServerRunnerApplication {

	public static void main(String[] args) {
		SpringApplication.run(JudgeServerRunnerApplication.class, args);
	}
}
