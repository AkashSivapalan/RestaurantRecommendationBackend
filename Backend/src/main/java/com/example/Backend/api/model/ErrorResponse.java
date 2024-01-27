package com.example.Backend.api.model;

public class ErrorResponse {

    private String message;

    private String description;

    public ErrorResponse(String message, String description) {
        this.message = message;
        this.description = description;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "message='" + message + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}