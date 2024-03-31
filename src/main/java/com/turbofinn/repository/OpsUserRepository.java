package com.turbofinn.repository;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.turbofinn.entities.OpsUserEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@Transactional
public interface OpsUserRepository extends JpaRepository<OpsUserEntity, UUID> {

    @Query(value = "SELECT * FROM ops_user_tb WHERE ops_id = :opsId", nativeQuery = true)
    public OpsUserEntity fetchByOpsId(@Param("opsId") UUID opsId);

    @Query(value = "SELECT * FROM ops_user_tb WHERE mobile_no = :mobileNo", nativeQuery = true)
    public OpsUserEntity fetchByMobileNo(@Param("mobileNo") String mobileNo);

}
