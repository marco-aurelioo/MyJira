package com.tiozao.tasks.aplication.dtos;

public class OrganizationDTO {

    private String id;
    private String titulo;

    public OrganizationDTO(String name, String id) {
        this.id = id;
        this.titulo = name;
    }


    public OrganizationDTO() {
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
