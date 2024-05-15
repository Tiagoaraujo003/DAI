package com.example.demo.repository;


import com.example.demo.model.Admin;
import com.example.demo.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente,Long> {
    Optional<Cliente> findById(Long Id);

}
