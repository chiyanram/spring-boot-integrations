package com.rmurugaian.spring.pdf.dto;

import lombok.Getter;
import org.springframework.http.MediaType;

import java.util.function.Function;

@Getter
public enum DocumentType {
    PDF(MediaType.APPLICATION_OCTET_STREAM, (name) -> "attachment; filename=".concat(name).concat(".pdf"));

    private final MediaType mediaType;
    private final Function<String, String> documentFileName;

    DocumentType(final MediaType mediaType, final Function<String, String> documentFileName) {
        this.mediaType = mediaType;
        this.documentFileName = documentFileName;
    }

    public String getDocumentFileName(final String name) {
        return documentFileName.apply(name);
    }
}
