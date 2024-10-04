package com.tiozao.tasks.aplication.controllers.response;

import java.math.BigDecimal;
import java.util.List;

public class SubscriptionPlansResponse {

    private String planName;
    private List<String> features;

    private BigDecimal price;

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public List<String> getFeatures() {
        return features;
    }

    public void setFeatures(List<String> features) {
        this.features = features;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
