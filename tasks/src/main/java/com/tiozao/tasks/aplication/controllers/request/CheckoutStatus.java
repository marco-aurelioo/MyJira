package com.tiozao.tasks.aplication.controllers.request;

public class CheckoutStatus {
    private String status;

    public CheckoutStatus(){}

    public CheckoutStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
