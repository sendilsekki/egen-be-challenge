package com.egen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@SpringBootApplication
public class EgenBeChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(EgenBeChallengeApplication.class, args);
	}
}
