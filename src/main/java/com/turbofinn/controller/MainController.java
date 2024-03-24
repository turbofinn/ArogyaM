package com.turbofinn.controller;

import com.turbofinn.entities.OpsUserEntity;
import com.turbofinn.service.OpsUserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("createOpsUser")
public class MainController {

    private OpsUserService opsUserService;

    public MainController(OpsUserService opsUserService) {
        this.opsUserService = opsUserService;
    }

    @PostMapping
    public OpsUserEntity create(@RequestBody OpsUserEntity opsUserEntity)
    {
        OpsUserEntity opsUserEntity1 = opsUserService.create(opsUserEntity);
        return opsUserEntity1;
    }

}
