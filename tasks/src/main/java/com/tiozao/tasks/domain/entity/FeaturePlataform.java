package com.tiozao.tasks.domain.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "features_plataform")
public class FeaturePlataform extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String role;
    private String description;

    @ManyToMany(mappedBy = "featuresList", fetch = FetchType.LAZY)
    private List<SubscriptionPlansEntity> subscriptionPlans;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<SubscriptionPlansEntity> getSubscriptionPlans() {
        return subscriptionPlans;
    }

    public void setSubscriptionPlans(List<SubscriptionPlansEntity> subscriptionPlans) {
        this.subscriptionPlans = subscriptionPlans;
    }
}
