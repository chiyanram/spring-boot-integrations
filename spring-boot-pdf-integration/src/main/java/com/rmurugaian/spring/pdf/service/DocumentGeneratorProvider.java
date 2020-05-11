package com.rmurugaian.spring.pdf.service;

import com.google.common.collect.ImmutableMap;
import com.rmurugaian.spring.pdf.dto.DocumentGeneratorRequest;
import com.rmurugaian.spring.pdf.dto.DocumentType;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Set;
import java.util.function.Function;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toMap;

@Component
@Log4j2
public class DocumentGeneratorProvider {

    private final ImmutableMap<DocumentType, DocumentGenerator> documentGeneratorsByType;

    public DocumentGeneratorProvider(final Set<DocumentGenerator> documentGenerators) {
        this.documentGeneratorsByType =
            documentGenerators
                .stream()
                .collect(collectingAndThen(toMap(DocumentGenerator::type, Function.identity()), ImmutableMap::copyOf));
    }

    public InputStream generateDocument(final DocumentGeneratorRequest documentGeneratorRequest) {
        final DocumentGenerator documentGenerator =
            documentGeneratorsByType.get(documentGeneratorRequest.getDocumentType());

        return documentGenerator.generateDocument(documentGeneratorRequest);
    }

}
