package com.maxar.api.JobApi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobController {

    @GetMapping("/job")
    public Job job(@RequestParam(value = "id", defaultValue = "") String id) {
        return new Job(id);
    }
}