package com.turbofinn.entities;

import jakarta.persistence.*;
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
//,
//        indexes = {@Index(name = "mobileNo_index", columnList = "mobileNo"
public class OpsUserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID opsId;

    private String name;

    private String email;

    private String mobileNo;

    private String designation;

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
