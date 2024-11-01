package com.tiozao.tasks.domain.service.providers.gatewaypagamento.model;

import java.io.Serializable;

public class Produto implements Serializable {
    private String nome;
    private Long preco;
    private Long quantidade;

    public Produto(){}

    public Produto(String nome, Long preco, Long quantidade) {
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getPreco() {
        return preco;
    }

    public void setPreco(Long preco) {
        this.preco = preco;
    }

    public Long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Long quantidade) {
        this.quantidade = quantidade;
    }
}
