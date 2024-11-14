package com.tiozao.tasks.aplication.controllers.response;

public class ProjectResponse {

    private String nome;
    private String description;

    private String alias;

    public ProjectResponse(String projectName, String description, String alias) {
        this.nome = projectName;
        this.description = description;
        this.alias = alias;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
