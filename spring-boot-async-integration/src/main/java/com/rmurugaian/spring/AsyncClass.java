package com.rmurugaian.spring;

import lombok.SneakyThrows;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncClass {

    @Async
    @SneakyThrows
    public void asyncSlowTask() {
        Thread.sleep(10000);
    }
}