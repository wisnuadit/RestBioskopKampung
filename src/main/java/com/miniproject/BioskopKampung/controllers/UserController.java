package com.miniproject.BioskopKampung.controllers;

import com.miniproject.BioskopKampung.RestResponse;
import com.miniproject.BioskopKampung.dtos.user.UserInsertDTO;
import com.miniproject.BioskopKampung.dtos.user.UserInsertResponseDTO;
import com.miniproject.BioskopKampung.services.UserService;
import com.miniproject.BioskopKampung.services.UserServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    private UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public Authentication index(Authentication authentication){
        return authentication;
    }

    @PostMapping("sign-user")
    public ResponseEntity<RestResponse<UserInsertResponseDTO>> insertNewUser(
            @RequestBody UserInsertDTO userDTO
            ){
        return new ResponseEntity<>(
                new RestResponse<>(service.registrationUser(userDTO),
                        "User berhasil ditambahkan",
                        "201"),
                HttpStatus.CREATED
        );
    }
}
