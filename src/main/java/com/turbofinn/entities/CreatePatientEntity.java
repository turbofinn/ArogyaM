package com.turbofinn.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "create_patient_tb")
public class CreatePatientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID patientId;

    @Size(min = 10, max = 10, message = "Invalid Mobile Number")
    private String mobileNo;

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

    public CreatePatientEntity(UUID patientId, String mobileNo, String name, String gender, String entryDate, String discountApplicable, String fees, String diagnosticCenterFees, String referralDoctorDiscount, String typeOfDiscount, String investigationType, String reportsTo, String doctorName)
    {
        super();
        this.patientId = patientId;
        this.mobileNo = mobileNo;
        this.name = name;
        this.gender = gender;
        this.entryDate = entryDate;
        this.discountApplicable = discountApplicable;
        this.fees = fees;
        this.diagnosticCenterFees = diagnosticCenterFees;
        this.referralDoctorDiscount = referralDoctorDiscount;
        this.typeOfDiscount = typeOfDiscount;
        this.investigationType = investigationType;
        this.reportsTo = reportsTo;
        this.doctorName = doctorName;
    }
}
