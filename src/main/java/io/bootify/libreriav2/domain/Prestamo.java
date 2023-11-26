package io.bootify.libreriav2.domain;

import jakarta.persistence.*;
import io.bootify.libreriav2.model.EstadoPrestamo;
import java.time.LocalDate;
import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@Getter
@Setter
@Table(name = "Prestamos")
@EntityListeners(AuditingEntityListener.class)
public class Prestamo {

    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(
            name = "primary_sequence",
            sequenceName = "primary_sequence",
            allocationSize = 1,
            initialValue = 10000
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "primary_sequence"
    )
    private Long id;


    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EstadoPrestamo estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "libro_idLibro")
    private Libro libro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lector_id")
    private Lector lector;

    @Column(nullable = false)
    private String fechaDevolucion;

    @Column(nullable = false)
    private LocalDate fechaInicio;


    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

}
