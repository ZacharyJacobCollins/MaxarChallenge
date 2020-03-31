package com.maxar.api.JobApi;

import java.util.UUID;

public class Job {

//    private UUID jobId;
    private String jobId;

    public Job(String id) {
       this.jobId = id;
    }

//    public UUID getId() {
//        return this.jobId;
//    }

    public String getId() {
        return this.jobId;
    }
}
