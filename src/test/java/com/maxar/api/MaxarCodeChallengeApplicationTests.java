package com.maxar.api;

import com.maxar.api.JobApi.Job;
import com.maxar.api.JobClient.JobClient;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MaxarCodeChallengeApplicationTests {
	@Test
	void contextLoads() { }

	@Test
	public void check_we_can_create_job() throws ExecutionException, InterruptedException, JSONException {
		JobClient client = new JobClient();
		CompletableFuture<JSONObject> future = client.getJob("1");
		JSONObject jsonJob = future.get();
		System.out.println("Json job " + jsonJob.keys());
		System.out.println();
		assertThat(jsonJob.get("jobId"))
				.isNotNull();
	}
}
