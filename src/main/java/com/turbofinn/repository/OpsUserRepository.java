package com.turbofinn.repository;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.turbofinn.entities.OpsUserEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@Transactional
public interface OpsUserRepository extends JpaRepository<OpsUserEntity, UUID> {

    @Query(value = "SELECT * FROM OPS_USER_ENTITY", nativeQuery = true)
    public List<OpsUserEntity> findAllValues();
    /*String query = "select * from ops_user_entity";
    logger("Query Result : " + query);*/

}
