package com.turbofinn.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OpsUserRequest {

    private String name;

    private String email;

    private String mobileNo;

    private String designation;

    private String password;
}
