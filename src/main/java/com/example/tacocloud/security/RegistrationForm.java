package com.example.tacocloud.security;

import com.example.tacocloud.data.Users;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Slf4j
@Data
public class RegistrationForm {
    private String username;
    private String password;
    private String fullname;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String phone;
    public Users toUser(BCryptPasswordEncoder passwordEncoder) {
        log.info("GOT FROM REGISTRATION FORM WITH USERNAME: " + username + " AND PASSWORD: " + password);
        return new Users(username, passwordEncoder.encode(password), fullname, street, city, state, zip, phone);
    }
}
