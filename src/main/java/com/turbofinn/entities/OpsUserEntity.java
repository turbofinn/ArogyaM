package com.turbofinn.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OpsUserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID opsId;

    private String name;

    private String email;

    private String mobileNo;

    private String designation;

    private String password;

}
