package com.rmurugaian.spring.pdf;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Log4j2
public class PdfDocumentGeneratorApplication {

    public static void main(final String[] args) {
        SpringApplication.run(PdfDocumentGeneratorApplication.class, args);
    }

}
