package com.tiozao.tasks.domain.service.providers.gatewaypagamento.persist;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tiozao.tasks.domain.service.providers.gatewaypagamento.model.Produto;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity(name = "checkout")
public class CheckoutEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JdbcTypeCode(SqlTypes.UUID)
    @Column(unique = true, nullable = false)
    private UUID externalId;

    private String gatewayPaymentId;

    private String pessoa;

//    @Enumerated(EnumType.STRING)
//    private CheckoutStatus status;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "carrinho_json", columnDefinition = "jsonb")
    private String carrinhoJson;

    @Transient
    private List<Produto> produtos;

    private LocalDateTime createDate;

    private LocalDateTime  modifyDate;


    public CheckoutEntity() {}

    public CheckoutEntity(UUID externalId, String pessoa, List<Produto> produtos, String gatewayPaymentId) {
        this.externalId = externalId;
        this.produtos = produtos;
        this.pessoa = pessoa;
        this.carrinhoJson = convertToJson(produtos);
        this.gatewayPaymentId = gatewayPaymentId;
       // this.status = CheckoutStatus.PENDING;
    }

    // Getters e setters

    public List<Produto> getProdutos() {
        if (this.produtos == null) {
            this.produtos = convertFromJson(this.carrinhoJson);
        }
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
        this.carrinhoJson = convertToJson(produtos);
    }

    private String convertToJson(List<Produto> produtos) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(produtos);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao serializar o carrinho para JSON", e);
        }
    }

    private List<Produto> convertFromJson(String json) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(json, new TypeReference<List<Produto>>() {});
        } catch (IOException e) {
            throw new RuntimeException("Erro ao desserializar o carrinho do JSON", e);
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPessoa() {
        return pessoa;
    }

    public void setPessoa(String pessoa) {
        this.pessoa = pessoa;
    }

    public String getCarrinhoJson() {
        return carrinhoJson;
    }

    public void setCarrinhoJson(String carrinhoJson) {
        this.carrinhoJson = carrinhoJson;
    }

    public UUID getExternalId() {
        return externalId;
    }

    public void setExternalId(UUID externalId) {
        this.externalId = externalId;
    }

//    public CheckoutStatus getStatus() {
//        return status;
//    }
//
//    public void setStatus(CheckoutStatus status) {
//        this.status = status;
//    }

    public String getGatewayPaymentId() {
        return gatewayPaymentId;
    }

    public void setGatewayPaymentId(String gatewayPaymentId) {
        this.gatewayPaymentId = gatewayPaymentId;
    }

    public LocalDateTime  getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime  createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime  getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(LocalDateTime  modifyDate) {
        this.modifyDate = modifyDate;
    }

    @PrePersist
    protected void onCreate() {
        this.createDate = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.modifyDate = LocalDateTime.now();
    }
}
