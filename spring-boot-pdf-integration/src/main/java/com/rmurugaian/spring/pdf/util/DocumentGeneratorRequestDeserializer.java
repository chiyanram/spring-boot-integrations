package com.rmurugaian.spring.pdf.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.rmurugaian.spring.pdf.dto.DocumentGeneratorRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class DocumentGeneratorRequestDeserializer extends StdDeserializer<DocumentGeneratorRequest> {
    private static final long serialVersionUID = 7914388793231077158L;

    protected DocumentGeneratorRequestDeserializer() {
        super(DocumentGeneratorRequest.class);
    }

    @Override
    public DocumentGeneratorRequest deserialize(
        final JsonParser p, final DeserializationContext ctxt) {

        return null;
    }

    public static void main(final String[] args) {
        final RestTemplate restTemplate = new RestTemplate();
        final HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("john", "doe");

        final HttpEntity<String> request = new HttpEntity<String>(headers);

        final ResponseEntity<String> response =
            restTemplate.exchange("https://www.getMeData.com", HttpMethod.GET, request, String.class);

        System.out.println(response.getBody());
    }
}
