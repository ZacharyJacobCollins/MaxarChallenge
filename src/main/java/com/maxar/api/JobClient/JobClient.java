package com.maxar.api.JobClient;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Component
public class JobClient implements ApplicationListener<ApplicationReadyEvent> {

    public JobClient() {}

    /**
     * Send a request for a single job
     * @param id
     * @return
     */
    public CompletableFuture<JSONObject> getJob(String id){
        CompletableFuture<JSONObject> future = CompletableFuture.supplyAsync(new Supplier<JSONObject>() {
            @Override
            public JSONObject get() {
                OkHttpClient client = new OkHttpClient();
                String queryParam = "/job?id=" + 1;

                Request request = new Request.Builder()
                        .url("http://localhost:8080" + queryParam)
                        .build();

                try {
                    Call call = client.newCall(request);
                    try (Response response = call.execute()) {
                        String str = response.body().string();

                        JSONObject jsonObj = new JSONObject(str);
                        return jsonObj;

//                        return new Job(UUID.fromString((String) jsonObj.get("jobId")));
                    }
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        });

        return future;

    }

    /**
     *  Run method on application startup
     *  Sends all api requests for individual jobs
     *  aggregate responses and print responses to console
     */
    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        List<String> ids = getRequestIds();
        List<CompletableFuture<JSONObject>> futures =
                ids.stream()
                        .map(id -> getJob(id))
                        .collect(Collectors.toList());

        List<JSONObject> result =
                futures.stream()
                        .map(CompletableFuture::join)
                        .collect(Collectors.toList());

        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("jobs", result);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        System.out.println(jsonObject);
    }

    /**
     * Generate a random number of request ids
     * Number of requests are bound between 1000 and 2000
     */
    private List<String> getRequestIds() {
        // move to method, gen number of calls
        List<String> ids = new ArrayList<>();
        int numberOfApiCalls = new Random().nextInt(1000) + 1000;

        for (int i = 0; i < numberOfApiCalls; i++) {
            ids.add(Integer.toString(i));
        }

        return ids;
    }
}