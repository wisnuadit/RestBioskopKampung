package com.miniproject.BioskopKampung.dtos.customer;

import lombok.Data;

@Data
public class CustomerInsertDTO {

    private final String firstName;
    private final String lastName;
    private final String gender;
    private final String birthDate;
    private final String address;
    private final String phoneNumber;
    private final String email;
}
