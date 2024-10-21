package com.tiozao.tasks.assembler.models;

import com.tiozao.tasks.aplication.controllers.request.PricePlanRequest;

import java.security.Principal;

public class CheckoutIn implements ObjectsIn {

    public CheckoutIn(PricePlanRequest pricePlanRequest, Principal principal) {
        this.pricePlanRequest = pricePlanRequest;
        this.principal = principal;
    }

    private PricePlanRequest pricePlanRequest;

    private Principal principal;

    public PricePlanRequest getPricePlanRequest() {
        return pricePlanRequest;
    }

    public void setPricePlanRequest(PricePlanRequest pricePlanRequest) {
        this.pricePlanRequest = pricePlanRequest;
    }

    public Principal getPrincipal() {
        return principal;
    }

    public void setPrincipal(Principal principal) {
        this.principal = principal;
    }
}
