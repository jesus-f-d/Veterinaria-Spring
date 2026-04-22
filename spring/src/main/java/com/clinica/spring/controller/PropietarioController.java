package com.clinica.spring.controller;

import com.clinica.spring.dto.request.PropietarioCreateDto;
import com.clinica.spring.dto.response.ApiResponse;
import com.clinica.spring.dto.response.PropietarioResponseDto;
import com.clinica.spring.service.PropietarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/propietario")
public class PropietarioController {

    private final PropietarioService propietarioService;

    public PropietarioController(PropietarioService propietarioService) {
        this.propietarioService = propietarioService;
    }

    @PostMapping("/save")
    public ResponseEntity<ApiResponse<PropietarioResponseDto>> save(
            @Valid @RequestBody PropietarioCreateDto dto) {

        PropietarioResponseDto response = propietarioService.savePropietario(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.ok("Propietario registrado exitosamente", response));
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<ApiResponse<PropietarioResponseDto>> findById(
            @PathVariable UUID id) {

        PropietarioResponseDto response = propietarioService.findById(id);
        return ResponseEntity
                .ok(ApiResponse.ok("Propietario encontrado", response));
    }

    @GetMapping("/find/apellido/{apellido}")
    public ResponseEntity<ApiResponse<List<PropietarioResponseDto>>> findByApellido(
            @PathVariable String apellido) {

        List<PropietarioResponseDto> response = propietarioService.findByApellido(apellido);
        return ResponseEntity
                .ok(ApiResponse.ok("Propietarios encontrados", response));
    }
}claude