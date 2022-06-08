package com.miniproject.BioskopKampung.repositories;

import com.miniproject.BioskopKampung.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {
}
