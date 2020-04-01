package com.maxar.api.JobApi;

import java.util.UUID;

public class Job {

    private UUID jobId;

    public Job(UUID jobId) {
       this.jobId = jobId;
    }

    public UUID getJobId() {
        return this.jobId;
    }
}
