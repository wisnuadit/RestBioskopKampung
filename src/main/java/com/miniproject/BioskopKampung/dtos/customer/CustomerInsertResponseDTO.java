package com.miniproject.BioskopKampung.dtos.customer;

import com.miniproject.BioskopKampung.models.Customer;
import lombok.Data;

@Data
public class CustomerInsertResponseDTO {

    private final String customerId;
    private final String fullName;

    public static CustomerInsertResponseDTO set(Customer customer){
        return new CustomerInsertResponseDTO(customer.getCustomerId(), customer.fetchFullname());
    }
}
