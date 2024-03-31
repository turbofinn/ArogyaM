package com.turbofinn.service;

import com.turbofinn.model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public interface RestService {

    @GetMapping("/hello")
    public ResponseEntity<?> getQuickHello(@RequestParam(value = "name", defaultValue = "world") String name);

    @PostMapping("/createOpsUser")
    public ResponseEntity<?> createOpsUser(@RequestBody OpsUserRequest opsUserRequest);

    @PostMapping("/loginnow")
    public ResponseEntity<?> loginNow(@RequestBody UserLoginRequest loginRequest);

    @GetMapping("/opsUsers")
    public ResponseEntity<?> getAllOpsUsers();
}
