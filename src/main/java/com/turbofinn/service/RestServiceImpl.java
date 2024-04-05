package com.turbofinn.service;

import com.turbofinn.common.ValidationUtils;
import com.turbofinn.entities.OpsUserEntity;
import com.turbofinn.model.HelloWorld;
import com.turbofinn.model.OpsUserRequest;
import com.turbofinn.model.UserLoginRequest;
import com.turbofinn.repository.OpsUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RestServiceImpl implements RestService {

    private static final Logger logger = LoggerFactory.getLogger(RestServiceImpl.class);

    @Autowired
    private OpsUserRepository opsUserRepository;

    public RestServiceImpl() {
    }

    @Override
    public ResponseEntity<?> getQuickHello(String name) {
        HelloWorld helloWorld = new HelloWorld("Hello " + name + "!");
        return ResponseEntity.ok(helloWorld);
    }

    @Override
    public ResponseEntity<?> createOpsUser(OpsUserRequest opsUserRequest) {
        try {
            // Validate mobile number
            if (!ValidationUtils.isValidMobileNumber(opsUserRequest.getMobileNo())) {
                logger.info("Invalid mobile number: {}", opsUserRequest.getMobileNo());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Mobile number must be 10 digits.");
            }

            // Validate email
            if (!ValidationUtils.isValidEmail(opsUserRequest.getEmail())) {
                logger.info("Invalid email format: {}", opsUserRequest.getEmail());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid email format.");
            }

            OpsUserEntity transaction = new OpsUserEntity(null, opsUserRequest.getName(), opsUserRequest.getEmail(), opsUserRequest.getMobileNo(), opsUserRequest.getDesignation(), opsUserRequest.getPassword());
            transaction = opsUserRepository.save(transaction);
            return ResponseEntity.status(HttpStatus.CREATED).body(transaction);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create user: " + e.getMessage());
        }
    }


    @Override
    public ResponseEntity<?> loginNow(UserLoginRequest loginRequest) {
        try {
            // Retrieve user details from the database based on the provided email
            OpsUserEntity user = opsUserRepository.fetchByMobileNo(loginRequest.getMobileNo());
            if (user != null && user.getPassword().equals(loginRequest.getPassword())) {
                // Authentication successful
                return ResponseEntity.ok("Login successful");
            } else {
                // Authentication failed
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to login: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> getAllOpsUsers() {
        try {
            List<OpsUserEntity> opsUsers = opsUserRepository.findAll();
            return ResponseEntity.ok(opsUsers);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to fetch users: " + e.getMessage());
        }
    }
}
