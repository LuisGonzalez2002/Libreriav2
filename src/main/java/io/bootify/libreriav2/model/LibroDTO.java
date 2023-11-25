package io.bootify.libreriav2.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public class LibroDTO {

    private Long idLibro;

    private Long idAutor;

    @NotNull
    @Size(max = 255)
    private String anoPublicacion;

    @NotNull
    @Size(max = 255)
    private String titulo;

    @Size(max = 255)
    private String genero;

    @NotNull
    private Integer stock;

    public Long getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(final Long idLibro) {
        this.idLibro = idLibro;
    }

    public Long getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(final Long idAutor) {
        this.idAutor = idAutor;
    }

    public String getAnoPublicacion() {
        return anoPublicacion;
    }

    public void setAnoPublicacion(final String anoPublicacion) {
        this.anoPublicacion = anoPublicacion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(final String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(final String genero) {
        this.genero = genero;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(final Integer stock) {
        this.stock = stock;
    }

}
