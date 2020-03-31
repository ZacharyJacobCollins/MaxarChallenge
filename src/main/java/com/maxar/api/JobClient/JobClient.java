package com.maxar.api.JobClient;
import okhttp3.*;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class JobClient implements ApplicationListener<ApplicationReadyEvent> {

    public JobClient() {}

    /**
     * Builds all request objects
     * @return
     */
    private List getRequests() {
        List requests = new ArrayList();
        int numberOfRequests = 1010;
        final int[] i = {0};
        for ( int j = 0; j < numberOfRequests; j++) {
            String queryParam = "/job?id=" + i[0];
            Request request = new Request.Builder()
                    .url("http://localhost:8080" + queryParam)
                    .build();

            requests.add(request);
            i[0]++;
        }
        return requests;
    }

    /**
     * Fire this method on application ready
     * Instantiates an okhttp client, sends and aggregates requests
     * finally prints json response
     * @param applicationReadyEvent
     */
    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        OkHttpClient client = new OkHttpClient();
        List requests = this.getRequests();
        List results = new ArrayList<>();
        AtomicInteger counter = new AtomicInteger(0);

        System.out.println("here" + requests.size());

        for (int i = 0; i < requests.size(); i++) {
            System.out.println(i);

            Call call = client.newCall((Request) requests.get(i));
            int finalI = i;

            call.enqueue(new Callback() {
                public void onResponse(Call call, Response response) throws IOException {
                    results.add(response.body().string());
                    counter.incrementAndGet();

                    System.out.println("received" + counter.get());

                    if (counter.get() == 1010) {
                        System.out.println("Here in this thing");

                        for (Iterator<String> iterator = results.iterator(); iterator.hasNext();) {
                            String string = iterator.next();
                            if (!string.isEmpty()) {
                                System.out.println(string);
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
