package com.tiozao.tasks.domain.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity(name="person_plans")
public class PersonPlansEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private PersonEntity person;

    @ManyToOne
    @JoinColumn(name = "plan_id", nullable = false)
    private SubscriptionPlansEntity plans;

    private LocalDateTime startDate;

    public PersonPlansEntity(SubscriptionPlansEntity subscriptionPlansEntity, PersonEntity personByUserId) {
        this.plans = subscriptionPlansEntity;
        this.person = personByUserId;
        this.startDate = LocalDateTime.now();
    }

    // Getters e Setters
    public PersonEntity getPerson() {
        return person;
    }

    public void setPerson(PersonEntity person) {
        this.person = person;
    }

    public SubscriptionPlansEntity getPlans() {
        return plans;
    }

    public void setPlans(SubscriptionPlansEntity plans) {
        this.plans = plans;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }


}
