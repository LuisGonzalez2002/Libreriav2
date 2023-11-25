package io.bootify.libreriav2.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public class PrestamoDTO {

    private Long idPrestamo;

    @NotNull
    private Long idLibro;

    @NotNull
    private Long idLector;

    @NotNull
    @Size(max = 255)
    private String fechaPrestamo;

    @NotNull
    @Size(max = 255)
    private String fechaDevolucion;

    private Long libro;

    private Long lector;

    public Long getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(final Long idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public Long getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(final Long idLibro) {
        this.idLibro = idLibro;
    }

    public Long getIdLector() {
        return idLector;
    }

    public void setIdLector(final Long idLector) {
        this.idLector = idLector;
    }

    public String getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(final String fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public String getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(final String fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public Long getLibro() {
        return libro;
    }

    public void setLibro(final Long libro) {
        this.libro = libro;
    }

    public Long getLector() {
        return lector;
    }

    public void setLector(final Long lector) {
        this.lector = lector;
    }

}
