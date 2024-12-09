package com.tiozao.tasks.aplication.controllers.request;

public class InviteToTeamRequest {

    private String name;
    private String pessoaId;

    public InviteToTeamRequest() {}

    public InviteToTeamRequest(String name, String pessoaId) {
        this.name = name;
        this.pessoaId = pessoaId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPessoaId() {
        return pessoaId;
    }

    public void setPessoaId(String pessoaId) {
        this.pessoaId = pessoaId;
    }
}
