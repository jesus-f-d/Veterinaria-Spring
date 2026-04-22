package com.clinica.spring.service;

import com.clinica.spring.dto.request.MascotaCreateDto;
import com.clinica.spring.dto.response.MascotaResponseDto;
import com.clinica.spring.entity.MascotaEntity;
import com.clinica.spring.entity.PropietarioEntity;
import com.clinica.spring.exception.ResourceNotFoundException;
import com.clinica.spring.repository.MascotaRepository;
import com.clinica.spring.repository.PropietarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MascotaService {

    private final MascotaRepository mascotaRepository;
    private final PropietarioRepository propietarioRepository;
    private final ModelMapper modelMapper;

    public MascotaService(MascotaRepository mascotaRepository,
                          PropietarioRepository propietarioRepository,
                          ModelMapper modelMapper) {
        this.mascotaRepository = mascotaRepository;
        this.propietarioRepository = propietarioRepository;
        this.modelMapper = modelMapper;
    }

    public MascotaResponseDto saveMascota(MascotaCreateDto dto) {

        // Buscar propietario
        PropietarioEntity propietario = propietarioRepository.findById(dto.getPropietarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Propietario no encontrado"));

        // Mapear DTO a entidad y asignar propietario
        MascotaEntity entity = modelMapper.map(dto, MascotaEntity.class);
        entity.setPropietario(propietario);
        entity.setId(null); // evitar conflicto con UUID del dto

        // Guardar y retornar
        MascotaEntity saved = mascotaRepository.save(entity);
        return modelMapper.map(saved, MascotaResponseDto.class);
    }

    public List<MascotaResponseDto> findByEspecie(String especie) {

        return mascotaRepository.findByEspecie(especie).stream()
                .map(entity -> modelMapper.map(entity, MascotaResponseDto.class))
                .collect(Collectors.toList());
    }
}