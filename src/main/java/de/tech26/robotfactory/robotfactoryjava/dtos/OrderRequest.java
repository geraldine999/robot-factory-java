package de.tech26.robotfactory.robotfactoryjava.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;


import java.util.List;

public class OrderRequest {

    @JsonProperty("components")
    private List<String> componentsId;

    public OrderRequest(List<String> componentsId) {
        this.componentsId = componentsId;
    }

    public List<String> getComponentsId() {
        return componentsId;
    }

    public void setComponentsId(List<String> componentsId) {
        this.componentsId = componentsId;
    }

    public OrderRequest() {
    }
}
