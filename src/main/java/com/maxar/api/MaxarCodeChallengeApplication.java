package com.maxar.api;

import com.maxar.api.JobClient.JobClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MaxarCodeChallengeApplication {

	public static void main(String[] args) {
		long startTime = System.nanoTime();

		SpringApplication.run(MaxarCodeChallengeApplication.class, args);

		long endTime = System.nanoTime();
		long timeElapsed = endTime - startTime;
		System.out.println("Execution time in seconds : " + timeElapsed / 1000000000);
	}
}
