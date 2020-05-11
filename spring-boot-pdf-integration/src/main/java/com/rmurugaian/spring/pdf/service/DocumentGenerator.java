package com.rmurugaian.spring.pdf.service;

import com.rmurugaian.spring.pdf.dto.DocumentGeneratorRequest;
import com.rmurugaian.spring.pdf.dto.DocumentType;

import java.io.InputStream;

public interface DocumentGenerator {

    InputStream generateDocument(DocumentGeneratorRequest documentGeneratorRequest);

    DocumentType type();
}
