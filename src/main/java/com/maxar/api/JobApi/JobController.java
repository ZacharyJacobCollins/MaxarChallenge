package com.maxar.api.JobApi;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@RestController
@ResponseBody
@EnableAsync
public class JobController {
    @GetMapping("/job")
    @Async
    public Job job(@RequestParam(value = "id", defaultValue = "") String id) {
        UUID uuid = UUID.randomUUID();
        return new Job("uuid");
    }

    private int getTimeout() {
        Random random = new Random();
        return (random.nextInt(9) + 1);
    }

}