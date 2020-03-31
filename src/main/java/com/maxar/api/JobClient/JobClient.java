package com.maxar.api.JobClient;
import okhttp3.*;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class JobClient implements ApplicationListener<ApplicationReadyEvent> {

    public JobClient() {}

    /**
     * Builds all request objects
     * @return
     */
    private List getRequests() {
        List builtRequests = new ArrayList<Request>();
        Random random = new Random();
        int numberOfRequests = (random.nextInt(2000));
        AtomicInteger counter = new AtomicInteger(numberOfRequests);

        for (int j = numberOfRequests; j >= 0; j--) {

            String queryParam = "/job?id=" + counter;

            Request request = new Request.Builder()
                    .url("http://localhost:8080" + queryParam)
                    .build();

            builtRequests.add(request);
            counter.decrementAndGet();
        }

        return builtRequests;
    }

    /**
     * Fire this method on application ready
     * Instantiates an okhttp client, sends and aggregates requests
     * Finally prints json response
     * @param applicationReadyEvent
     */
    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        OkHttpClient client = new OkHttpClient();
        List builtRequests = this.getRequests();
        List results = new ArrayList<>(2000);



        System.out.println("built requests size: " + builtRequests.size());
        for (AtomicInteger i = new AtomicInteger(0); i.get() < builtRequests.size(); i.getAndIncrement()) {

            Request loadedRequest = (Request) builtRequests.get(i.get());
            Call call = client.newCall(loadedRequest);

            call.enqueue(new Callback() {
                public void onResponse(Call call, Response response) {
                    results.add(response.body());

                    System.out.println("built requests size: " + builtRequests.size() + "results" + results.size());
                    if (results.size() == builtRequests.size()) {
                        System.out.println("Here in this thing");

                        int q = 0;
                        for (Iterator<String> iterator = results.iterator(); iterator.hasNext();) {
                            String string = iterator.next();
                            if (!string.isEmpty()) {
                                System.out.println(string + q);
                                q++;
                            }
                        }

                    }
                }

                public void onFailure(Call call, IOException e) {
                    System.out.println("Failure");
                }
            });
        }
    }
}
