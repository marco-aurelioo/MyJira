package com.tiozao.tasks.aplication.dtos;

public class OrganizationDTO {

    private String titulo;

    public OrganizationDTO(String name) {
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

}
