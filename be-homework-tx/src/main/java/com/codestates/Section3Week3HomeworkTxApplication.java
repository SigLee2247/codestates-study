package com.codestates;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableJpaAuditing
@EnableAsync
@SpringBootApplication
public class Section3Week3HomeworkTxApplication {

	public static void main(String[] args) {
		SpringApplication.run(Section3Week3HomeworkTxApplication.class, args);
	}

}
