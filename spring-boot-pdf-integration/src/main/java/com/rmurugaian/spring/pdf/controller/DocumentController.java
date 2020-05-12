package com.rmurugaian.spring.pdf.controller;

import com.rmurugaian.spring.pdf.dto.DocumentGeneratorRequest;
import com.rmurugaian.spring.pdf.dto.DocumentType;
import com.rmurugaian.spring.pdf.service.DocumentGeneratorProvider;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;

@Api
@RestController
public class DocumentController {

    private final DocumentGeneratorProvider documentGeneratorProvider;

    public DocumentController(final DocumentGeneratorProvider documentGeneratorProvider) {
        this.documentGeneratorProvider = documentGeneratorProvider;
    }

    @ApiOperation(
        produces = MediaType.APPLICATION_OCTET_STREAM_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE,
        value = "/document/generate")
    @PostMapping(path = "/document/generate",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<InputStreamResource> createDocument(
        @RequestBody final DocumentGeneratorRequest request) {

        final InputStream documentStream = documentGeneratorProvider.generateDocument(request);

        final DocumentType documentType = request.getDocumentType();
        final String contentDisposition = documentType.getDocumentFileName().apply(request.getDocumentName());

        return ResponseEntity
            .ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
            .contentType(documentType.getMediaType())
            .body(new InputStreamResource(documentStream));
    }
}
