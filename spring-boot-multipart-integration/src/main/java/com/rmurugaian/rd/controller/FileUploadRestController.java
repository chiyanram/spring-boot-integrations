package com.rmurugaian.rd.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/upload")
public class FileUploadRestController {

    private static final Logger logger = LoggerFactory.getLogger(FileUploadRestController.class);

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> upload(@RequestParam("file") final MultipartFile file) throws IOException {

        final String originalFilename = file.getOriginalFilename();

        logger.debug("Contents of the file : {} ", new String(file.getBytes()));

        return ResponseEntity.ok(originalFilename);
    }
}