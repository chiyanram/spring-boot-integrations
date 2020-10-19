package com.rmurugaian.rd.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestOperations;

import java.io.File;

@Service
public class DefaultFileService implements FileService {

    private final RestOperations restOperations;
    private final String fileHandlerBaseUrl;
    private final String fileUploadLocation;

    public DefaultFileService(final RestOperations restOperations,
                              @Value("${file.handler.service.baseUrl}") final String fileHandlerBaseUrl,
                              @Value("${file.upload.location}") final String fileUploadLocation) {


        Assert.notNull(restOperations, "restOperations");
        Assert.hasText(fileHandlerBaseUrl, "fileHandlerBaseUrl");
        Assert.hasText(fileUploadLocation, "fileUploadLocation");

        this.restOperations = restOperations;
        this.fileHandlerBaseUrl = fileHandlerBaseUrl;
        this.fileUploadLocation = fileUploadLocation;
    }

    @Override
    public ResponseEntity<String> upload() {
        final LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        final FileSystemResource value = new FileSystemResource(new File(fileUploadLocation));

        map.add("file", value);

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        final HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity =
                new HttpEntity<>(map, headers);

        return restOperations.exchange(fileHandlerBaseUrl.concat("/upload"), HttpMethod.POST, requestEntity, String.class);
    }
}
