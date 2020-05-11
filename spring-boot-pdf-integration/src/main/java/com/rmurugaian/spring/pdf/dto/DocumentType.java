package com.rmurugaian.spring.pdf.dto;

import lombok.Getter;
import org.springframework.http.MediaType;

@Getter
public enum DocumentType {
    PDF(MediaType.APPLICATION_OCTET_STREAM);

    private final MediaType mediaType;

    DocumentType(final MediaType mediaType) {
        this.mediaType = mediaType;
    }

}
