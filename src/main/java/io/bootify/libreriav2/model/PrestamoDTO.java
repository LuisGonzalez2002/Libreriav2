package io.bootify.libreriav2.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PrestamoDTO {

    private Long idPrestamo;

    @NotNull
    private EstadoPrestamo estado;

    private Long libro;

    private Long lector;

    @NotNull
    private LocalDate fechaInicio;

    @NotNull
    @Size(max = 255)
    private String fechaDevolucion;
}
