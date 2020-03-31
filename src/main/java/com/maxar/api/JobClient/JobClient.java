package com.maxar.api.JobClient;
import com.maxar.api.JobApi.Job;
import okhttp3.*;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Component
public class JobClient implements ApplicationListener<ApplicationReadyEvent> {

    public JobClient() {}

    public CompletableFuture<Job> getJob(String id){
        OkHttpClient client = new OkHttpClient();

        CompletableFuture<Job> future = CompletableFuture.supplyAsync(new Supplier<Job>() {
            @Override
            public Job get() {
                OkHttpClient client = new OkHttpClient();
                String queryParam = "/job?id=" + 1;

                Request request = new Request.Builder()
                        .url("http://localhost:8080" + queryParam)
                        .build();

                try {
                    final Response response = client.newCall(request).execute();
                    System.out.println(response.body());
                    Job job = new Job(response.body().string());
                    return job;
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        });

        return future;

    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        // move to method, gen number of calls
        List<String> ids = new ArrayList<>();
        int numberOfApiCalls = new Random().nextInt(2000);

        for (int i = 0; i<numberOfApiCalls; i++) {
            ids.add(Integer.toString(i));
        }

        List<CompletableFuture<Job>> futures =
                ids.stream()
                        .map(id -> getJob(id))
                        .collect(Collectors.toList());

        List<Job> result =
                futures.stream()
                        .map(CompletableFuture::join)
                        .collect(Collectors.toList());

        result.forEach((item) ->
            System.out.println(item.getId())
        );
    }
}