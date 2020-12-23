package com.rmurugaian.spring.pdf.dto;

import com.google.common.collect.ImmutableMap;
import lombok.Data;

@Data
public class DocumentGeneratorRequest {
    private Long tenantId;
    private DocumentType documentType;
    private String documentName;
    private ImmutableMap<String, Object> data;
}
