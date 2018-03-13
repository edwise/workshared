package com.edwise.uploader.uploader.controller;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class UploadController {
    private static String UPLOADED_FOLDER = "/Users/eduardo.anton/mine/uploader/";

    @PutMapping(value = "/upload", produces = "application/json")
    public String singleFileUpload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "{\"status\": \"emptyFile\"}";
        }

        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);
            System.out.println("File " + file.getOriginalFilename() + " saved");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "{\"status\": \"ok\"}";
    }

}