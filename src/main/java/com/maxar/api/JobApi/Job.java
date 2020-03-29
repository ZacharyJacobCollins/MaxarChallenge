package com.maxar.api.JobApi;

public class Job {

    private String jobId;

    public Job(String id) {
       this.jobId = id;
    }

    public String getId() {
        return this.jobId;
    }
}
