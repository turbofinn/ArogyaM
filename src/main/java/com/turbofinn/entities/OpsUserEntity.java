package com.turbofinn.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "ops_user_tb")
//indexes = {@Index(name = "mobileNo_index", columnList = "mobileNo"
public class OpsUserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID opsId;

    @Size(min = 1, max = 100, message = "Invalid Name")
    private String name;

    private String email;

    private String mobileNo;

    @Size(min = 1, max = 100, message = "Invalid Designation")
    private String designation;

    @Size(min = 1, max = 4, message = "Invalid Password")
    private String password;

    @CreatedDate
    private LocalDateTime rowCreateTs;

    public OpsUserEntity(UUID opsId, String name, String email, String mobileNo, String designation, String password)
    {
        super();
        this.opsId = opsId;
        this.name = name;
        this.email = email;
        this.mobileNo = mobileNo;
        this.designation = designation;
        this.password = password;
    }

}
