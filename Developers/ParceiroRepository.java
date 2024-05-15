package com.example.demo.repository;

import com.example.demo.model.Parceiro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParceiroRepository extends JpaRepository<Parceiro, Long> {
    Optional<Parceiro> findById(Long Id);
}
