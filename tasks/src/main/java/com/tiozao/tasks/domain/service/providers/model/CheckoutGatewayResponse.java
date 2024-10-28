package com.tiozao.tasks.domain.service.providers.model;

public class CheckoutGatewayResponse {

    private String url;
    private String gatewayReferenceId;

    public CheckoutGatewayResponse(){}

    public CheckoutGatewayResponse(String url, String gatewayReferenceId) {
        this.url = url;
        this.gatewayReferenceId = gatewayReferenceId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getGatewayReferenceId() {
        return gatewayReferenceId;
    }

    public void setGatewayReferenceId(String gatewayReferenceId) {
        this.gatewayReferenceId = gatewayReferenceId;
    }
}
