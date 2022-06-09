package com.miniproject.BioskopKampung.controllers;

import com.miniproject.BioskopKampung.RestResponse;
import com.miniproject.BioskopKampung.dtos.customer.CustomerHeaderDTO;
import com.miniproject.BioskopKampung.dtos.customer.CustomerInsertDTO;
import com.miniproject.BioskopKampung.dtos.customer.CustomerInsertResponseDTO;
import com.miniproject.BioskopKampung.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("customer")
public class CustomerController {

    private CustomerService service;

    @Autowired
    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<RestResponse<List<CustomerHeaderDTO>>> findAllCustomers(){
        int size = service.findAllCustomers().size();
        return new ResponseEntity<>(
                new RestResponse<>(service.findAllCustomers(),
                        "Customer berhasil ditemukan: " + size,
                        "200"),
                HttpStatus.OK
        );
    }

    @PostMapping("insert-new-customer")
    public ResponseEntity<RestResponse<CustomerInsertResponseDTO>> insertNewCustomer(
            @RequestBody CustomerInsertDTO customerDTO, Authentication authentication
    ){
        return new ResponseEntity<>(
                new RestResponse<>(service.insertNewCustomer(authentication, customerDTO),
                        "Customer berhasil dibuat",
                        "201"),
                HttpStatus.CREATED
        );
    }
}
