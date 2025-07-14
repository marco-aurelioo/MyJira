package com.tiozao.tasks.aplication.controllers.model;

import java.util.Date;

public class TaskDto {

    private String id;
    private String titulo;
    private String descricao;
    private Project projeto;
    private UserProfile responsavel;
    private UserProfile criador;

    private String tipo;
    private String status;
    private String prioridade;
    private Date dataCriacao;
    private Date dataConclusao;



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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Date getDataConclusao() {
        return dataConclusao;
    }

    public void setDataConclusao(Date dataConclusao) {
        this.dataConclusao = dataConclusao;
    }
}
