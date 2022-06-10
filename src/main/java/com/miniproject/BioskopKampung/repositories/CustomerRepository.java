package com.miniproject.BioskopKampung.repositories;

import com.miniproject.BioskopKampung.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, String> {

    @Query(value = """
            SELECT *
            FROM Customers cus
            WHERE CONCAT(cus.FirstName, ' ', cus.LastName) LIKE %:name%
            """, nativeQuery = true)
    List<Customer> findAllByName(@Param("name") String name);
}
