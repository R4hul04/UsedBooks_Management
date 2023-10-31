package com.project.usedtextbooks.dto;

public class Response {
    private boolean success;
    private String message;
    private Double price;

    // 构造函数，getters，setters等
    public Response(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public Response(boolean success, String message, Double price) {
        this.success = success;
        this.message = message;
        this.price = price;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    // ... getters and setters ...
}
