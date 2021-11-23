package de.tech26.robotfactory.robotfactoryjava.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderResponse {
    @JsonProperty("order_id")
    private int orderId;
    private double total;

    public OrderResponse(int orderId, double total) {
        this.orderId = orderId;
        this.total = total;
    }

    public OrderResponse() {
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
