package com.miniproject.BioskopKampung.services;

import com.miniproject.BioskopKampung.dtos.customer.CustomerHeaderDTO;
import com.miniproject.BioskopKampung.dtos.customer.CustomerInsertDTO;
import com.miniproject.BioskopKampung.dtos.customer.CustomerInsertResponseDTO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface CustomerService {

    public List<CustomerHeaderDTO> findAllCustomers();
    public CustomerInsertResponseDTO insertNewCustomer(Authentication authentication,
                                                       CustomerInsertDTO customerDTO);
}
