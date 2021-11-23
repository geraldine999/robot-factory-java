package de.tech26.robotfactory.robotfactoryjava.exceptions;

import org.springframework.http.HttpStatus;


public class RobotFactoryExceptionResponse {
    private String message;
    private String details;
    private HttpStatus status;

    public RobotFactoryExceptionResponse(String message, String details, HttpStatus status) {
        this.message = message;
        this.details = details;
        this.status = status;
    }

    public RobotFactoryExceptionResponse() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }


    public HttpStatus getStatus() {
        return status;
    }

}
