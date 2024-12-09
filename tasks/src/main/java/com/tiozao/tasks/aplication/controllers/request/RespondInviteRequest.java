package com.tiozao.tasks.aplication.controllers.request;

public class RespondInviteRequest {
    private Boolean accept;
    private String msg;

    public Boolean getAccept() {
        return accept;
    }

    public void setAccept(Boolean accept) {
        this.accept = accept;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
