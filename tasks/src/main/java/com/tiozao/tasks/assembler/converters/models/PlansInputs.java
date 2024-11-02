package com.tiozao.tasks.assembler.converters.models;

import com.tiozao.tasks.domain.entity.SubscriptionPlansEntity;

import java.util.List;

public class PlansInputs implements ObjectsIn {

    private List<SubscriptionPlansEntity> allPlans;

    public PlansInputs(List<SubscriptionPlansEntity> allPlans) {
        this.allPlans = allPlans;
    }

    public List<SubscriptionPlansEntity> getAllPlans() {
        return allPlans;
    }

}

