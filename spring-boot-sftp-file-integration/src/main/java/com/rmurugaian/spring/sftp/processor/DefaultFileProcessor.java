package com.rmurugaian.spring.sftp.processor;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class DefaultFileProcessor implements FileProcessor {

    @Override
    public void processFile(final String file) {
        log.info("file {} received to process", file);

    }
}
