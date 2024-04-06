package com.turbofinn.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CreatePatientRequest {

    private String mobileNo;

    private String patientId;

    private String name;

    private String gender;

    private String entryDate;

    private String discountApplicable;

    private String fees;

    private String diagnosticCenterFees;

    private String referralDoctorDiscount;

    private String typeOfDiscount;

    private String investigationType;

    private String reportsTo;

    private String doctorName;

}
