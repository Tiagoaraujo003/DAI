package com.example.demo.service;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;

@Service
public class BarcodeService {

    public String readBarcode(String imageContent) throws BarcodeReadException {
        try {
            byte[] imageBytes = Base64.getDecoder().decode(imageContent);
            ByteArrayInputStream bis = new ByteArrayInputStream(imageBytes);
            BufferedImage bufferedImage = ImageIO.read(bis);

            LuminanceSource luminanceSource = new BufferedImageLuminanceSource(bufferedImage);
            BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(luminanceSource));
            Result result = new MultiFormatReader().decode(binaryBitmap);
            return result.getText();
        } catch (IOException | NotFoundException e) {
            throw new BarcodeReadException("Failed to read Barcode", e);
        }
    }

    public static class BarcodeReadException extends Exception {
        public BarcodeReadException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}

