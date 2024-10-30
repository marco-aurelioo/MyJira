package com.tiozao.tasks.assembler.models;

import com.tiozao.tasks.domain.service.providers.gatewaypagamento.model.Produto;

import java.util.List;

public class CheckoutOut implements ObjectsOut{

    private List<Produto> produtos;

    private String urlSuccess;

    private String urlCancel;

    private String externalId;

    public CheckoutOut(List<Produto> produtos, String urlSuccess, String urlCancel, String externalId) {
        this.produtos = produtos;
        this.urlSuccess = urlSuccess;
        this.urlCancel = urlCancel;
        this.externalId = externalId;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
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

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }
}
