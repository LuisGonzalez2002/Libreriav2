package io.bootify.libreriav2.model;

import jakarta.validation.constraints.Size;


public class LectorDTO {

    private Long idLector;

    @Size(max = 255)
    private String nombre;

    @Size(max = 255)
    private String apellido;

    private Integer telefono;

    public Long getIdLector() {
        return idLector;
    }

    public void setIdLector(final Long idLector) {
        this.idLector = idLector;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(final String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(final String apellido) {
        this.apellido = apellido;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(final Integer telefono) {
        this.telefono = telefono;
    }

}
