package com.grupo1.demo.auth;

import com.grupo1.demo.models.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    String firstname;
    String lastname;
    String email;
    String phone;
    String username;
    String password;
    private Role role; 
}
