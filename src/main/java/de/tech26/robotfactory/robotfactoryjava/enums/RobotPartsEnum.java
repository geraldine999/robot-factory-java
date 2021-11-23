package de.tech26.robotfactory.robotfactoryjava.enums;

public enum RobotPartsEnum {
    A("Humanoid Face", 10.28, 9, TypeOfPartEnum.FACE),
    B("LCD Face", 24.07, 7, TypeOfPartEnum.FACE),
    C("Steampunk Face", 13.30, 0, TypeOfPartEnum.FACE),
    D("Arms with Hands", 28.94, 1, TypeOfPartEnum.ARMS),
    E("Arms with Grippers", 12.39, 3, TypeOfPartEnum.ARMS),
    F("Mobility with Wheels",30.77, 2, TypeOfPartEnum.MOBILITY),
    G("Mobility with Legs", 55.13, 15, TypeOfPartEnum.MOBILITY),
    H("Mobility with Tracks", 50.00, 7, TypeOfPartEnum.MOBILITY),
    I("Material Bioplastic", 90.12, 92, TypeOfPartEnum.MATERIAL),
    J("Material Metallic", 82.31, 15, TypeOfPartEnum.MATERIAL);


    private String name;
    private double price;
    private int stock;
    private TypeOfPartEnum part;

    RobotPartsEnum(String name, double price, int stock, TypeOfPartEnum part) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.part = part;
    }

    public TypeOfPartEnum getPart() {
        return part;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setPart(TypeOfPartEnum part) {
        this.part = part;
    }

    RobotPartsEnum() {
    }
}
