package com.example.demo.repository;

import com.example.demo.model.Admin;
import com.example.demo.model.Premio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PremioRepository extends JpaRepository<Premio, Long> {
    Optional<Premio> findByPremioId(Long Id);

}
