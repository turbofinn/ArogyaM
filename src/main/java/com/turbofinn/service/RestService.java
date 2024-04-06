package com.turbofinn.service;

import com.turbofinn.model.*;
import jakarta.ws.rs.core.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public interface RestService {

    @GetMapping("/hello")
    public Response getQuickHello(@RequestParam(value = "name", defaultValue = "world") String name);

    @PostMapping("/createOpsUser")
    public Response createOpsUser(@RequestBody OpsUserRequest opsUserRequest);

    @PostMapping("/loginnow")
    public Response loginNow(@RequestBody UserLoginRequest loginRequest);

    @GetMapping("/opsUsers")
    public Response getAllOpsUsers();

    @PostMapping("/createPatient")
    public Response createPatient(@RequestBody CreatePatientRequest createPatientRequest);
}
