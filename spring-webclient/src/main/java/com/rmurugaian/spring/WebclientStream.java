package com.rmurugaian.spring;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

public class WebclientStream {

    private static final WebClient WEB_CLIENT = WebClient.builder().baseUrl("http://localhost:9102/").build();

    public static void main(String[] args) {

        String s = WEB_CLIENT.get().uri("/students/events").accept(MediaType.APPLICATION_STREAM_JSON).retrieve().bodyToFlux(String.class).take(5).blockLast();

        System.out.println("******* [] " + s);


    }
}
