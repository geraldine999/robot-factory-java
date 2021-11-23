package de.tech26.robotfactory.robotfactoryjava.dtos;

import de.tech26.robotfactory.robotfactoryjava.enums.RobotPartsEnum;

import java.util.List;

public class OrderRequest {

    private List<RobotPartsEnum> components;

    public OrderRequest(List<RobotPartsEnum> components) {
        this.components = components;
    }

    public OrderRequest() {
    }

    public List<RobotPartsEnum> getComponents() {
        return components;
    }

    public void setComponents(List<RobotPartsEnum> components) {
        this.components = components;
    }
}
