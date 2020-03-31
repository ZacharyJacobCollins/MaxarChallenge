package com.maxar.api.JobApi;

import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.*;

@RestController
@ResponseBody
public class JobController {

    @GetMapping("/job")
    @Async("asyncExecutor")
    public Object job(@RequestParam(value = "id", defaultValue = "") String id) throws InterruptedException, TimeoutException, ExecutionException {
        // Get random between 1 and 10
        Random random = new Random();
        int num = (random.nextInt(9) + 1) * 1000;


        // Sleep between 1 and 10 seconds
        UUID uuid = UUID.randomUUID();

        CompletableFuture completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(num);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return new Job(uuid);
        });

        long timeout = num;

        System.out.println("timeout is" + timeout);

        return completableFuture.get(timeout, TimeUnit.SECONDS);
    }

}