package io.bootify.libreriav2.model;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class LectorDTO {

    private Long idLector;

    @Size(max = 255)
    private String nombre;

    @Size(max = 255)
    private String apellidos;


}
