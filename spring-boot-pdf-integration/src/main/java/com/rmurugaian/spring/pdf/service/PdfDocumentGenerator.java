package com.rmurugaian.spring.pdf.service;

import com.itextpdf.text.DocumentException;
import com.rmurugaian.spring.pdf.dto.DocumentGeneratorRequest;
import com.rmurugaian.spring.pdf.dto.DocumentType;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.Context;
import org.w3c.tidy.Tidy;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Base64;

import static com.itextpdf.text.pdf.BaseFont.EMBEDDED;
import static com.itextpdf.text.pdf.BaseFont.IDENTITY_H;

@Service
@Log4j2
class PdfDocumentGenerator implements DocumentGenerator {

    private final ITemplateEngine templateEngine;

    PdfDocumentGenerator(final ITemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    @Override
    @SneakyThrows
    public InputStream generateDocument(final DocumentGeneratorRequest documentGeneratorRequest) {

        final String template =
            new String(Files.readAllBytes(new ClassPathResource("template.html").getFile().toPath()));

        final String footer =
            new String(Files.readAllBytes(new ClassPathResource("footer.html").getFile().toPath()));

        final String header =
            new String(Files.readAllBytes(new ClassPathResource("header.html").getFile().toPath()));

        final String content =
            new String(Files.readAllBytes(new ClassPathResource("policy-contenet.html").getFile().toPath()));

        final byte[] logoImage = Files.readAllBytes(new ClassPathResource("logo.jpg").getFile().toPath());
        final byte[] logoCss = Files.readAllBytes(new ClassPathResource("style.css").getFile().toPath());
        final byte[] footerCss = Files.readAllBytes(new ClassPathResource("footer.css").getFile().toPath());

        final Context context = new Context();
        context.setVariable("data", documentGeneratorRequest.getData());
        context.setVariable("image", new String(Base64.getEncoder().encode(logoImage), StandardCharsets.UTF_8));
        context.setVariable(
            "style",
            templateEngine.process("<style>".concat(new String(logoCss)).concat("</style>"), context));
        context.setVariable(
            "footerStyle",
            templateEngine.process("<style>".concat(new String(footerCss)).concat("</style>"), context));

        context.setVariable("footer", templateEngine.process(footer, context));
        context.setVariable("header", templateEngine.process(header, context));
        context.setVariable("content", templateEngine.process(content, context));

        final String renderedHtmlContent = templateEngine.process(template, context);

        try (final ByteArrayInputStream pdfTemplateHtml =
            new ByteArrayInputStream(renderedHtmlContent.getBytes(StandardCharsets.UTF_8));
            final ByteArrayOutputStream pdfTemplateXHTML = new ByteArrayOutputStream()) {

            final Tidy tidy = new Tidy();
            tidy.setInputEncoding(StandardCharsets.UTF_8.name());
            tidy.setOutputEncoding(StandardCharsets.UTF_8.name());
            tidy.setXHTML(true);
            tidy.parseDOM(pdfTemplateHtml, pdfTemplateXHTML);

            final String xHtml = pdfTemplateXHTML.toString(StandardCharsets.UTF_8);

            final ITextRenderer renderer = new ITextRenderer();
            renderer.getFontResolver().addFont("Code39.ttf", IDENTITY_H, EMBEDDED);
            renderer.setDocumentFromString(xHtml);
            renderer.layout();

            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            renderer.createPDF(byteArrayOutputStream);
            return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        } catch (final IOException | DocumentException ioException) {
            log.error("Unable to create pdf with error message {}", ioException.getMessage(), ioException);
            throw new RuntimeException(ioException);
        }
    }

    @Override
    public DocumentType type() {
        return DocumentType.PDF;
    }
}
