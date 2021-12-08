package de.tech26.robotfactory.robotfactoryjava.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderResponse {
    @JsonProperty("order_id")
    private String orderId;
    private double total;

    public OrderResponse(String orderId, double total) {
        this.orderId = orderId;
        this.total = total;
    }

    public OrderResponse() {
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
