package com.miniproject.BioskopKampung.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Customers")
public class Customer {

    @Id
    @Column(name = "CustomerID")
    private String customerId;

    @Column(name = "FirstName")
    private String firstName;

    @Column(name = "LastName")
    private String lastName;

    @Column(name = "Gender")
    private String gender;

    @Column(name = "BirthDate")
    private LocalDate birthDate;

    @Column(name = "Address")
    private String address;

    @Column(name = "PhoneNumber")
    private String phoneNumber;

    @Column(name = "Email")
    private String email;

    @OneToOne
    @JoinColumn(name = "CustomerID")
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "customer")
    private List<BookingTicket> bookingTickets;

    public String fetchFullname(){
        return String.format("%s %s", this.firstName, this.lastName);
    }

    public Customer(String customerId, String firstName, String lastName, String gender, LocalDate birthDate,
                    String address, String phoneNumber, String email) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.birthDate = birthDate;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
}
