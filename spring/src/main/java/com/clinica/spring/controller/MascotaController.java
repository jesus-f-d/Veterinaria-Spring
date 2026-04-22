package com.clinica.spring.controller;

import com.clinica.spring.dto.request.MascotaCreateDto;
import com.clinica.spring.dto.response.ApiResponse;
import com.clinica.spring.dto.response.MascotaResponseDto;
import com.clinica.spring.service.MascotaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/mascota")
public class MascotaController {

    private final MascotaService mascotaService;

    public MascotaController(MascotaService mascotaService) {
        this.mascotaService = mascotaService;
    }

    @PostMapping("/save")
    public ResponseEntity<ApiResponse<MascotaResponseDto>> save(
            @Valid @RequestBody MascotaCreateDto dto) {

        MascotaResponseDto response = mascotaService.saveMascota(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Mascota registrada exitosamente", response));
    }

    @GetMapping("/find/especie/{especie}")
    public ResponseEntity<ApiResponse<List<MascotaResponseDto>>> findByEspecie(
            @PathVariable String especie) {

        List<MascotaResponseDto> response = mascotaService.findByEspecie(especie);
        return ResponseEntity
                .ok(ApiResponse.ok("Mascotas encontradas", response));
    }
}