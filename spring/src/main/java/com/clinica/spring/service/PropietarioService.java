package com.clinica.spring.service;

import com.clinica.spring.client.ReniecClient;
import com.clinica.spring.dto.request.PropietarioCreateDto;
import com.clinica.spring.dto.response.MascotaResponseDto;
import com.clinica.spring.dto.response.PropietarioResponseDto;
import com.clinica.spring.dto.response.ReniecResponse;
import com.clinica.spring.entity.PropietarioEntity;
import com.clinica.spring.exception.DuplicateResourceException;
import com.clinica.spring.exception.ExternalServiceException;
import com.clinica.spring.exception.ResourceNotFoundException;
import com.clinica.spring.repository.PropietarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PropietarioService {

    private final PropietarioRepository propietarioRepository;
    private final ModelMapper modelMapper;
    private final ReniecClient reniecClient;

    @Value("${api.token}")
    private String apiToken;

    public PropietarioService(PropietarioRepository propietarioRepository,
                              ModelMapper modelMapper,
                              ReniecClient reniecClient) {
        this.propietarioRepository = propietarioRepository;
        this.modelMapper = modelMapper;
        this.reniecClient = reniecClient;
    }

    public PropietarioResponseDto savePropietario(PropietarioCreateDto dto) {

        // Verificar email duplicado
        propietarioRepository.findByEmail(dto.getEmail()).ifPresent(p -> {
            throw new DuplicateResourceException("Ya existe un propietario registrado con ese email");
        });

        // Llamar a RENIEC para validar DNI
        ReniecResponse reniec;
        try {
            reniec = reniecClient.consultarDni(dto.getDni(), apiToken);
            System.out.println("RENIEC response: " + reniec.getFirstName() + " " + reniec.getFirstLastName() + " " + reniec.getSecondLastName());
        } catch (Exception e) {
            throw new ExternalServiceException("Error al consultar el servicio de RENIEC: " + e.getMessage());
        }

        // Verificar que RENIEC retornó datos válidos
        if (reniec.getFirstName() == null || reniec.getFirstLastName() == null) {
            throw new ExternalServiceException("RENIEC no retornó datos válidos para el DNI: " + dto.getDni());
        }

        // Mapear DTO a entidad
        PropietarioEntity entity = modelMapper.map(dto, PropietarioEntity.class);

        // Usar datos de RENIEC para nombres y apellidos
        entity.setNombres(reniec.getFirstName());
        entity.setApellidos(reniec.getFirstLastName() + " " + reniec.getSecondLastName());

        // Guardar
        PropietarioEntity saved = propietarioRepository.save(entity);

        // Mapear a response
        PropietarioResponseDto response = modelMapper.map(saved, PropietarioResponseDto.class);
        response.setMascotas(null);
        return response;
    }

    public PropietarioResponseDto findById(UUID id) {

        PropietarioEntity entity = propietarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Propietario no encontrado"));

        PropietarioResponseDto response = modelMapper.map(entity, PropietarioResponseDto.class);

        List<MascotaResponseDto> mascotas = entity.getMascotas() == null ? List.of() :
                entity.getMascotas().stream()
                        .map(m -> modelMapper.map(m, MascotaResponseDto.class))
                        .collect(Collectors.toList());

        response.setMascotas(mascotas);
        return response;
    }

    public List<PropietarioResponseDto> findByApellido(String apellido) {

        return propietarioRepository.findByApellido(apellido).stream()
                .map(entity -> {
                    PropietarioResponseDto dto = modelMapper.map(entity, PropietarioResponseDto.class);
                    dto.setMascotas(List.of());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}