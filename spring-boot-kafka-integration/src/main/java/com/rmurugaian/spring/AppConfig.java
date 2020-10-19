package com.rmurugaian.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.ErrorHandler;
import org.springframework.kafka.listener.SeekToCurrentErrorHandler;
import org.springframework.util.backoff.FixedBackOff;

import java.util.List;

@Configuration
public class AppConfig {

    @Bean
    public DeadLetterPublishingRecoverer recoverer(KafkaTemplate<String, List<String>> template) {
        return new DeadLetterPublishingRecoverer(template);
    }

    @Bean
    public ErrorHandler errorHandler(final DeadLetterPublishingRecoverer recoverer) {
        return new SeekToCurrentErrorHandler(recoverer, new FixedBackOff(0L, 2L));
    }
}
