package com.tiozao.tasks.assembler.converters.models;

import com.tiozao.tasks.aplication.controllers.response.SubscriptionPlansResponse;

import java.util.List;

public class PlansOutput implements ObjectsOut{

    private List<SubscriptionPlansResponse> listSubscriptions;

    public PlansOutput(List<SubscriptionPlansResponse> listSubscriptions) {
        this.listSubscriptions = listSubscriptions;
    }

    public List<SubscriptionPlansResponse> getAllPlans() {
        return listSubscriptions;
    }
}
