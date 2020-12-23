package com.rmurugaian.spring.pdf.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.rmurugaian.spring.pdf.dto.DocumentGeneratorRequest;

public class DocumentGeneratorRequestDeserializer extends StdDeserializer<DocumentGeneratorRequest> {
    private static final long serialVersionUID = 7914388793231077158L;

    protected DocumentGeneratorRequestDeserializer() {
        super(DocumentGeneratorRequest.class);
    }

    @Override
    public DocumentGeneratorRequest deserialize(final JsonParser p, final DeserializationContext ctxt) {

        return null;
    }
}
