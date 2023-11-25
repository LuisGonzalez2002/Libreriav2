package io.bootify.libreriav2.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.OffsetDateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@Table(name = "Libroes")
@EntityListeners(AuditingEntityListener.class)
public class Libro {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLibro;

    @Column(unique = true)
    private Long idAutor;

    @Column(nullable = false)
    private String anoPublicacion;

    @Column(nullable = false)
    private String titulo;

    @Column
    private String genero;

    @Column(nullable = false)
    private Integer stock;

    @OneToOne(mappedBy = "libro", fetch = FetchType.LAZY)
    private Prestamo libro;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

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

    public Prestamo getLibro() {
        return libro;
    }

    public void setLibro(final Prestamo libro) {
        this.libro = libro;
    }

    public OffsetDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(final OffsetDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public OffsetDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(final OffsetDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

}
