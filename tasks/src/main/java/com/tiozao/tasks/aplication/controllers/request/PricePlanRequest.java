package com.tiozao.tasks.aplication.controllers.request;

import java.math.BigDecimal;

public class PricePlanRequest {
    private String planName;
    private Long price;

    private String urlSuccess;

    private String urlCancel;


    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getUrlSuccess() {
        return urlSuccess;
    }

    public void setUrlSuccess(String urlSuccess) {
        this.urlSuccess = urlSuccess;
    }

    public String getUrlCancel() {
        return urlCancel;
    }

    public void setUrlCancel(String urlCancel) {
        this.urlCancel = urlCancel;
    }
}
