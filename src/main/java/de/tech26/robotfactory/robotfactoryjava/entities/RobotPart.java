package de.tech26.robotfactory.robotfactoryjava.entities;

import de.tech26.robotfactory.robotfactoryjava.enums.TypeOfPartEnum;

public class RobotPart {
    private String id;
    private String name;
    private int stock;
    private double price;
    private TypeOfPartEnum typeOfPart;

    public RobotPart(String id, String name, int stock, double price, TypeOfPartEnum typeOfPart) {
        this.id = id;
        this.name = name;
        this.stock = stock;
        this.price = price;
        this.typeOfPart = typeOfPart;
    }

    public RobotPart() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public TypeOfPartEnum getTypeOfPart() {
        return typeOfPart;
    }

    public void setTypeOfPart(TypeOfPartEnum typeOfPart) {
        this.typeOfPart = typeOfPart;
    }
}
