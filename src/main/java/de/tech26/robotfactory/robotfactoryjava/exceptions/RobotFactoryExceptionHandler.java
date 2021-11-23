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

    @ExceptionHandler(MorePartsException.class)
    public ResponseEntity<Object> morePartsExceptionHandler(MorePartsException e, WebRequest webRequest){
        RobotFactoryExceptionResponse response = new RobotFactoryExceptionResponse(e.getMessage(), webRequest.getDescription(false), HttpStatus.UNPROCESSABLE_ENTITY);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @ExceptionHandler(MoreThanOneOptionForATypeOfPartException.class)
    public ResponseEntity<Object> moreThanOneOptionForATypeOfPartExceptionHandler(MoreThanOneOptionForATypeOfPartException e, WebRequest webRequest){
        RobotFactoryExceptionResponse response = new RobotFactoryExceptionResponse(e.getMessage(), webRequest.getDescription(false), HttpStatus.UNPROCESSABLE_ENTITY);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @ExceptionHandler(NotEnoughPartsException.class)
    public ResponseEntity<Object> notEnoughPartsExceptionHandler(NotEnoughPartsException e, WebRequest webRequest){
        RobotFactoryExceptionResponse response = new RobotFactoryExceptionResponse(e.getMessage(), webRequest.getDescription(false), HttpStatus.UNPROCESSABLE_ENTITY);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @ExceptionHandler(RanOutOfStockException.class)
    public ResponseEntity<Object> ranOutOfStockExceptionHandler(RanOutOfStockException e, WebRequest webRequest){
        RobotFactoryExceptionResponse response = new RobotFactoryExceptionResponse(e.getMessage(), webRequest.getDescription(false), HttpStatus.UNPROCESSABLE_ENTITY);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Object> nullPointerExceptionHandler( WebRequest webRequest){
        RobotFactoryExceptionResponse response = new RobotFactoryExceptionResponse("Order cannot be empty", webRequest.getDescription(false), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(response, response.getStatus());
    }

}
