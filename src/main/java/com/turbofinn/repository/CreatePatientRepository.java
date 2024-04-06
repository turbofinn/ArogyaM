package com.turbofinn.repository;

import com.turbofinn.entities.CreatePatientEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@Transactional
public interface CreatePatientRepository extends JpaRepository<CreatePatientEntity, UUID> {

    @Query(value = "SELECT * FROM create_patient_tb WHERE patient_id = :patientId", nativeQuery = true)
    CreatePatientRepository fetchByPatientId(@Param("patientId") UUID opsId);

    @Query(value = "SELECT * FROM create_patient_tb WHERE mobile_no = :mobileNo", nativeQuery = true)
    CreatePatientEntity fetchByPatientMobileNo(@Param("mobileNo") String mobileNo);

}