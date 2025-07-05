package com.tiozao.tasks.aplication.controllers.model;

public class Task {

    private String id;
    private String titulo;
    private String descricao;
    private Project projeto;
    private UserProfile responsavel;
    private UserProfile criador;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Project getProjeto() {
        return projeto;
    }

    public void setProjeto(Project projeto) {
        this.projeto = projeto;
    }

    public UserProfile getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(UserProfile responsavel) {
        this.responsavel = responsavel;
    }

    public UserProfile getCriador() {
        return criador;
    }

    public void setCriador(UserProfile criador) {
        this.criador = criador;
    }
}
