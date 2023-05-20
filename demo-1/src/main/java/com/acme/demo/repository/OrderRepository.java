package com.acme.demo.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.acme.demo.entity.Orders;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {
    // Custom queries or methods if needed
}
