package com.clinica.spring.dto.response;

import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PropietarioResponseDto {

    private UUID id;
    private String nombres;
    private String apellidos;
    private String email;
    private String telefono;
    private String direccion;
    private Date fechaRegistro;
    private List<MascotaResponseDto> mascotas;
}