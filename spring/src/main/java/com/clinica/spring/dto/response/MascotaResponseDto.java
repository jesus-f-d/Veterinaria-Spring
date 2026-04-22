package com.clinica.spring.dto.response;

import lombok.*;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MascotaResponseDto {

    private UUID id;
    private String nombre;
    private String especie;
    private String raza;
    private Double peso;
    private Date fechaNacimiento;
}