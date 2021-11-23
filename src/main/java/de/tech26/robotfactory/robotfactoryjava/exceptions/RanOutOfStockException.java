package de.tech26.robotfactory.robotfactoryjava.exceptions;

public class RanOutOfStockException extends RuntimeException{
    public RanOutOfStockException(String message) {
        super(message);
    }
}
