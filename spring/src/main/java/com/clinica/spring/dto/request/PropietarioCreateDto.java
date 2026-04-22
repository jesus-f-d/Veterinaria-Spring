package com.clinica.spring.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PropietarioCreateDto {

    // nombres y apellidos vienen de RENIEC, no del request
    private String nombres;
    private String apellidos;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email debe tener un formato válido")
    private String email;

    @NotBlank(message = "El teléfono es obligatorio")
    @Pattern(regexp = "\\d{7,9}", message = "El teléfono debe tener entre 7 y 9 dígitos numéricos")
    private String telefono;

    @NotBlank(message = "El DNI es obligatorio")
    @Pattern(regexp = "\\d{8}", message = "El DNI debe tener exactamente 8 dígitos numéricos")
    private String dni;

    @NotBlank(message = "La dirección es obligatoria")
    private String direccion;
}