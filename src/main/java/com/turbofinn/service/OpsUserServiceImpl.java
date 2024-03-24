package com.turbofinn.service;

import com.turbofinn.entities.OpsUserEntity;
import com.turbofinn.repository.OpsUserRepository;
import org.springframework.stereotype.Service;

@Service
public class OpsUserServiceImpl implements OpsUserService{

    private OpsUserRepository opsUserRepository;

    public OpsUserServiceImpl(OpsUserRepository opsUserRepository) {
        this.opsUserRepository = opsUserRepository;
    }

    @Override
    public OpsUserEntity create(OpsUserEntity opsUserEntity) {
        return opsUserRepository.save(opsUserEntity);
    }
}
