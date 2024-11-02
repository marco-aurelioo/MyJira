package com.tiozao.tasks.domain.entity;

import jakarta.persistence.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "projects")
public class ProjectEntity  extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String projectName;
    private String projectAlias;

    @ManyToOne
    private PersonEntity owner;

    @ManyToOne(optional = false)
    @JoinColumn(name = "organization_id", nullable = false)
    private OrganizationEntity organization;

    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
        this.projectAlias = gerarSigla(projectName);
    }

    private static String gerarSigla(String frase) {
        List<String> palavrasIgnoradas = Arrays.asList("de", "e", "da", "do", "das", "dos");
        List<String> palavras = Arrays.stream(frase.split(" "))
                .filter(palavra -> !palavrasIgnoradas.contains(palavra.toLowerCase()))
                .collect(Collectors.toList());

        StringBuilder sigla = new StringBuilder();
        for (String palavra : palavras) {
            sigla.append(Character.toUpperCase(palavra.charAt(0)));
            if (sigla.length() == 3) {
                break;
            }
        }


        if (sigla.length() < 3) {
            for (String palavra : palavras) {
                for (int i = 1; i < palavra.length(); i++) {
                    if (sigla.length() < 3) {
                        sigla.append(Character.toUpperCase(palavra.charAt(i)));
                    } else {
                        break;
                    }
                }
                if (sigla.length() == 3) {
                    break;
                }
            }
        }

        return sigla.toString();
    }

    public String getProjectAlias() {
        return projectAlias;
    }

    public void setProjectAlias(String projectAlias) {
        this.projectAlias = projectAlias;
    }

    public PersonEntity getOwner() {
        return owner;
    }

    public void setOwner(PersonEntity owner) {
        this.owner = owner;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public OrganizationEntity getOrganization() {
        return organization;
    }

    public void setOrganization(OrganizationEntity organization) {
        this.organization = organization;
    }
}
