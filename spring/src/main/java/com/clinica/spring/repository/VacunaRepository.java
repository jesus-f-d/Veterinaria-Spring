package com.clinica.spring.repository;

import com.clinica.spring.entity.VacunaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VacunaRepository extends JpaRepository<VacunaEntity, UUID> {
}