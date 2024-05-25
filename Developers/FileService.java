package com.example.demo.service;

import org.springframework.stereotype.Service;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

@Service
public class FileService {

    public String readFile(String filePath) {
        try {
            byte[] fileBytes = Files.readAllBytes(Paths.get(filePath));
            return new String(fileBytes);
        } catch (IOException e) {
            throw new RuntimeException("Error reading file", e);
        }
    }
}
