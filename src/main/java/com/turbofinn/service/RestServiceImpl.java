package com.turbofinn.service;

import com.google.gson.GsonBuilder;
import com.turbofinn.common.Constants;
import com.turbofinn.common.ValidationUtils;
import com.turbofinn.entities.*;
import com.turbofinn.model.*;
import com.turbofinn.repository.*;
import com.google.gson.Gson;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.turbofinn.common.Constants;

import java.util.List;

@Component
public class RestServiceImpl implements RestService {

    private static final Logger logger = LoggerFactory.getLogger(RestServiceImpl.class);

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Autowired
    private OpsUserRepository opsUserRepository;

    @Autowired
    private CreatePatientRepository createPatientRepository;

    public RestServiceImpl() {
    }

    @Override
    public Response getQuickHello(String name) {
        name = "World";
        HelloWorld helloWorld = new HelloWorld("Hello " + name + "!");
        return Response.ok().entity(helloWorld).build();
    }

    public Response createOpsUser(OpsUserRequest opsUserRequest) {
        logger.info("OpsUserRequest is :: " + gson.toJson(opsUserRequest));

        OpsUserResponse opsUserResponse = new OpsUserResponse();

        try {
            // Validate mobile number
            if (!ValidationUtils.isValidMobileNumber(opsUserRequest.getMobileNo())) {
                logger.info("Invalid Ops User Mobile Number: " + opsUserRequest.getMobileNo());
                return Response.status(Integer.parseInt(Constants.InvalidInputCodeError))
                        .entity("Invalid Mobile Number, Should be 10 digits")
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }

            // Validate email
            if (!ValidationUtils.isValidEmail(opsUserRequest.getEmail())) {
                logger.info("Invalid email format: " + opsUserRequest.getEmail());
                return Response.status(Integer.parseInt(Constants.InvalidInputCodeError))
                        .entity("Invalid email format, Should be in proper format")
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }

            // Check if mobile number already exists
            OpsUserEntity existingOpsUser = opsUserRepository.fetchByMobileNo(opsUserRequest.getMobileNo());
            if (existingOpsUser != null) {
                logger.info("Duplicate mobile number, Invalid User: " + opsUserRequest.getMobileNo());
                return Response.status(Integer.parseInt(Constants.InvalidInputCodeError))
                        .entity("Duplicate mobile number, Invalid User")
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }

            OpsUserEntity newOpsUser = new OpsUserEntity(null, opsUserRequest.getName(), opsUserRequest.getEmail(), opsUserRequest.getMobileNo(), opsUserRequest.getDesignation(), opsUserRequest.getPassword());
            newOpsUser = opsUserRepository.save(newOpsUser);

            // Build response with success code and OpsUserResponse
            opsUserResponse.setName(newOpsUser.getName());
            opsUserResponse.setEmail(newOpsUser.getEmail());
            opsUserResponse.setMobileNo(newOpsUser.getMobileNo());
            opsUserResponse.setDesignation(newOpsUser.getDesignation());
            opsUserResponse.setPassword(newOpsUser.getPassword());
            return Response.status(Integer.parseInt(Constants.Success))
                    .entity(opsUserResponse)
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        } catch (Exception e) {
            // Build response with error code and error message
            return Response.status(Integer.parseInt(Constants.InvalidInputCodeError))
                    .entity(e.getMessage())
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
    }


    @Override
    public Response loginNow(UserLoginRequest loginRequest) {
        try {
            // Retrieve user details from the database based on the provided email
            OpsUserEntity user = opsUserRepository.fetchByMobileNo(loginRequest.getMobileNo());
            if (user != null && user.getPassword().equals(loginRequest.getPassword())) {
                // Authentication successful
                return Response.ok().build();
            } else {
                // Authentication failed
                return Response.status(Response.Status.UNAUTHORIZED).entity("Invalid email or password").build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @Override
    public Response getAllOpsUsers() {
        try {
            List<OpsUserEntity> opsUsers = opsUserRepository.findAll();
            return Response.ok(opsUsers).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).type(MediaType.TEXT_PLAIN).build();
        }
    }

    @Override
    public Response createPatient(CreatePatientRequest createPatientRequest) {
        try {
            // Validate mobile number
            if (!ValidationUtils.isValidMobileNumber(createPatientRequest.getMobileNo())) {
                logger.info("Invalid patient mobile number: " + createPatientRequest.getMobileNo());
                return Response.status(Integer.parseInt(Constants.InvalidInputCodeError)).entity(createPatientRequest.getMobileNo()).type(MediaType.TEXT_PLAIN).build();
            }

            CreatePatientEntity existingPatient = createPatientRepository.fetchByPatientMobileNo(createPatientRequest.getMobileNo());
            if (existingPatient != null) {
                logger.info("Duplicate patient mobile number, Invalid Patient: " + createPatientRequest.getMobileNo());
                return Response.status(Integer.parseInt(Constants.InvalidInputCodeError)).entity(createPatientRequest.getMobileNo()).type(MediaType.TEXT_PLAIN).build();
            }

            CreatePatientEntity patiententry = new CreatePatientEntity(null, createPatientRequest.getMobileNo(), createPatientRequest.getName(), createPatientRequest.getGender(), createPatientRequest.getEntryDate(), createPatientRequest.getDiscountApplicable(), createPatientRequest.getFees(), createPatientRequest.getDiagnosticCenterFees(), createPatientRequest.getReferralDoctorDiscount(), createPatientRequest.getTypeOfDiscount(), createPatientRequest.getInvestigationType(), createPatientRequest.getReportsTo(), createPatientRequest.getDoctorName());
            patiententry = createPatientRepository.save(patiententry);
            return Response.status(Integer.parseInt(Constants.Success)).entity(patiententry).type(MediaType.TEXT_PLAIN).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).type(MediaType.TEXT_PLAIN).build();
        }
    }
}
