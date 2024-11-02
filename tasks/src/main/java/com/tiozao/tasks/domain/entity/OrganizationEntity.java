package com.tiozao.tasks.domain.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity(name="Organization")
public class OrganizationEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private PersonEntity owner;

    @ManyToMany
    @JoinTable(
            name = "organization_administrators",
            joinColumns = @JoinColumn(name = "organization_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id")
    )
    private List<PersonEntity> adms;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PersonEntity getOwner() {
        return owner;
    }

    public void setOwner(PersonEntity owner) {
        this.owner = owner;
    }

    public List<PersonEntity> getAdms() {
        return adms;
    }

    public void setAdms(List<PersonEntity> adms) {
        this.adms = adms;
    }
}
