package de.tech26.robotfactory.robotfactoryjava.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class RobotFactoryExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> ExceptionHandler(Exception e, WebRequest webRequest){
        RobotFactoryExceptionResponse response = new RobotFactoryExceptionResponse(e.getMessage(), webRequest.getDescription(false), HttpStatus.UNPROCESSABLE_ENTITY);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Object> nullPointerExceptionHandler( WebRequest webRequest){
        RobotFactoryExceptionResponse response = new RobotFactoryExceptionResponse("Order cannot be empty", webRequest.getDescription(false), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(response, response.getStatus());
    }

}
