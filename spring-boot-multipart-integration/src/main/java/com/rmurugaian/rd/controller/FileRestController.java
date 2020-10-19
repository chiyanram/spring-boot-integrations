package com.rmurugaian.rd.controller;

import com.rmurugaian.rd.service.FileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileRestController {

    private final FileService fileService;

    public FileRestController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("/upload")
    public ResponseEntity<String> upload() {

        return fileService.upload();
    }
}
