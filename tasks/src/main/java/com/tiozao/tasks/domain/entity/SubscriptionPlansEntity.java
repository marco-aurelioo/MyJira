package com.tiozao.tasks.domain.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity(name = "subscription_plans")
public class SubscriptionPlansEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;
    private BigDecimal price;
    private String alias;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "subscription_plan_features",  // Nome da tabela de junção
            joinColumns = @JoinColumn(name = "subscription_plan_id"),  // FK para subscription_plans
            inverseJoinColumns = @JoinColumn(name = "features_plataform_id")  // FK para features_plataform
    )
    private List<FeaturePlataformEntity> featuresList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public List<FeaturePlataformEntity> getFeaturesList() {
        return featuresList;
    }

    public void setFeaturesList(List<FeaturePlataformEntity> featuresList) {
        this.featuresList = featuresList;
    }
}
