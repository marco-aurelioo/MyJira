package com.tiozao.tasks.aplication.controllers.model;

public class ConfigurationStep {

    private String StatusName;
    private String colorCode;
    private Integer stepOrder;
    private Integer maxWhip;

    public String getStatusName() {
        return StatusName;
    }

    public void setStatusName(String statusName) {
        StatusName = statusName;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public Integer getStepOrder() {
        return stepOrder;
    }

    public void setStepOrder(Integer stepOrder) {
        this.stepOrder = stepOrder;
    }

    public Integer getMaxWhip() {
        return maxWhip;
    }

    public void setMaxWhip(Integer maxWhip) {
        this.maxWhip = maxWhip;
    }
}
