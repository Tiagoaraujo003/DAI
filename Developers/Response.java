package com.example.demo.model;

public class Response {

    private String barcodeData;

    public Response(String barcodeData) {
        this.barcodeData = barcodeData;
    }

    public String getBarcodeData() {
        return barcodeData;
    }

    public void setBarcodeData(String barcodeData) {
        this.barcodeData = barcodeData;
    }
}
