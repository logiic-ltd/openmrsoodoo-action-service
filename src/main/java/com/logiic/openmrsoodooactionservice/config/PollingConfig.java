package com.logiic.openmrsoodooactionservice.config;

import com.logiic.openmrsoodooactionservice.service.FeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;

@Configuration
public class PollingConfig {

    @Autowired
    private FeedService feedService;

    @Bean
    public TaskScheduler taskScheduler() {
        return new ConcurrentTaskScheduler();
    }

    @Scheduled(fixedRate = 60000) // Poll every minute
    public void pollOpenMRSFeeds() {
        feedService.processNewFeeds("encounter");
        feedService.processNewFeeds("obs");
        feedService.processNewFeeds("admission");
    }
}