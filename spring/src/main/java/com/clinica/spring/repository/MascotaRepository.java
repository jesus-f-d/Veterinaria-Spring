package com.clinica.spring.repository;

import com.clinica.spring.entity.MascotaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MascotaRepository extends JpaRepository<MascotaEntity, UUID> {

    @Query(value = "SELECT * FROM mascotas WHERE especie ILIKE :especie", nativeQuery = true)
    List<MascotaEntity> findByEspecie(@Param("especie") String especie);
}