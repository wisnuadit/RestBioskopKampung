package com.miniproject.BioskopKampung.services;

import com.miniproject.BioskopKampung.dtos.customer.CustomerHeaderDTO;
import com.miniproject.BioskopKampung.dtos.customer.CustomerInsertDTO;
import com.miniproject.BioskopKampung.dtos.customer.CustomerInsertResponseDTO;
import com.miniproject.BioskopKampung.models.Customer;
import com.miniproject.BioskopKampung.models.User;
import com.miniproject.BioskopKampung.repositories.CustomerRepository;
import com.miniproject.BioskopKampung.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class CustomerServiceImplementation {

    private CustomerRepository customerRepository;
    private UserRepository userRepository;

    @Autowired
    public CustomerServiceImplementation(CustomerRepository customerRepository, UserRepository userRepository) {
        this.customerRepository = customerRepository;
        this.userRepository = userRepository;
    }

    public List<CustomerHeaderDTO> findAllCustomers(){
        return CustomerHeaderDTO.toList(customerRepository.findAll());
    }

    public CustomerInsertResponseDTO insertNewCustomer(UserDetails userDetails, CustomerInsertDTO customerDTO){
        User user = userRepository.findByUsername(userDetails.getUsername()).get();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate birthDate = LocalDate.parse(customerDTO.getBirthDate(), formatter);

        Customer customer = new Customer(user, customerDTO.getFirstName(), customerDTO.getLastName(),
                customerDTO.getGender(), birthDate, customerDTO.getAddress(),
                customerDTO.getPhoneNumber(), customerDTO.getEmail());

        customerRepository.save(customer);
        return CustomerInsertResponseDTO.set(customer);
    }
}
