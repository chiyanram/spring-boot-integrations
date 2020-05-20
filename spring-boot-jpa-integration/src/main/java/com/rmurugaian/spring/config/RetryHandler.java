package com.rmurugaian.spring.config;

import net.jodah.failsafe.Failsafe;
import net.jodah.failsafe.RetryPolicy;

public class RetryHandler {

    public static void main(final String[] args) {
        new RetryHandler().downloadFileWithRetry();
    }

    private String downloadFileWithRetry() {
        final RetryPolicy<String> retryPolicy = new RetryPolicy<String>()
            .withMaxAttempts(-1)
            .handleIf(it -> false)
            .handleResultIf("file generation in progress"::equalsIgnoreCase);

        return Failsafe
            .with(retryPolicy)
            .onSuccess(response -> System.out.println("Generated file Url is ".concat(response.getResult())))
            .get(this::downloadFile);
    }

    private String downloadFile() {
        //throw new RuntimeException("Exception occurred");
        return "file generation in progress";
    }
}
