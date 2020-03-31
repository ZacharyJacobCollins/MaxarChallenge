package com.maxar.api.JobApi;

import java.util.UUID;

public class Job {

    private UUID jobId;

    public Job(UUID id) {
       this.jobId = id;
    }

    public UUID getId() {
        return this.jobId;
    }
}
