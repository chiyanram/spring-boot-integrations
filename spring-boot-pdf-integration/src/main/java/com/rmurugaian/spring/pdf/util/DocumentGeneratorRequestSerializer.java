package com.rmurugaian.spring.pdf.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.rmurugaian.spring.pdf.dto.DocumentGeneratorRequest;
import lombok.SneakyThrows;

import java.io.StringWriter;
import java.util.Base64;

public class DocumentGeneratorRequestSerializer extends StdSerializer<DocumentGeneratorRequest> {
    private static final long serialVersionUID = 6405615824037320159L;

    public DocumentGeneratorRequestSerializer() {
        super(DocumentGeneratorRequest.class);
    }

    @SneakyThrows
    @Override
    public void serialize(
        final DocumentGeneratorRequest value, final JsonGenerator gen, final SerializerProvider provider) {

        final var stringWriter = new StringWriter();
        final var newGen = gen.getCodec().getFactory().createGenerator(stringWriter);

        gen.getCodec().getFactory().getCodec().writeValue(newGen, value);

        final String json = stringWriter.toString();
        final String base64 = new String(Base64.getEncoder().encode(json.getBytes()));
        gen.writeString(base64);
    }
}
