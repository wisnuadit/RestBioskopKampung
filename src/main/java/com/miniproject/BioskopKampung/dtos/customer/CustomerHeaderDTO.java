package com.miniproject.BioskopKampung.dtos.customer;

import com.miniproject.BioskopKampung.models.Customer;
import lombok.Data;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Data
public class CustomerHeaderDTO {

    private final String customerId;
    private final String fullName;
    private final String gender;
    private final String birthDate;
    private final String address;
    private final String phoneNumber;
    private final String email;

    public static CustomerHeaderDTO set(Customer customer){
        Locale indo = new Locale("id", "ID");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", indo);
        String birthDate = formatter.format(customer.getBirthDate());

        return new CustomerHeaderDTO(customer.getCustomerId(), customer.fetchFullname(),
                customer.getGender(), birthDate, customer.getAddress(), customer.getPhoneNumber(),
                customer.getEmail());
    }

    public static List<CustomerHeaderDTO> toList(List<Customer> customers){
        List<CustomerHeaderDTO> result = new ArrayList<>();
        for (Customer customer : customers){
            result.add(set(customer));
        }
        return result;
    }
}
