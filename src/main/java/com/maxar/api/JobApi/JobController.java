package com.maxar.api.JobApi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
public class JobController {

    @GetMapping("/randomWaitEndpoint")
    public Job job(@RequestParam(value = "id", defaultValue = "") String id) throws InterruptedException {
        return new Job(UUID.randomUUID());
    }

    /**
     * Used to sleep current thread to trigger artificial wait time
     * @throws InterruptedException
     */
    private void triggerArtificialWait() throws InterruptedException {
        Random random = new Random();
        int randomSeconds = (random.nextInt(9) + 1);
        TimeUnit.SECONDS.sleep(randomSeconds);
    }
}