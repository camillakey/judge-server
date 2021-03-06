package com.judge_server.judge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@ComponentScan("com.judge_server")
@EnableAsync
public class JudgeServerJudgeApplication {

	public static void main(String[] args) {
		SpringApplication.run(JudgeServerJudgeApplication.class, args);
	}
}
