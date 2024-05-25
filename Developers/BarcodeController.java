package com.example.demo.controller;

import com.example.demo.service.BarcodeService;
import com.example.demo.service.BarcodeService.BarcodeReadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BarcodeController {

    @Autowired
    private BarcodeService barcodeService;

    @PostMapping("/readBarcode")
    public ResponseEntity<String> readBarcode(@RequestBody String imageContent) {
        try {
            String barcodeText = barcodeService.readBarcode(imageContent);
            return ResponseEntity.ok(barcodeText);
        } catch (BarcodeReadException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to read Barcode: " + e.getMessage());
        }
    }
}

