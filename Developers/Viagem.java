package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "VIAGEM")
public class Viagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pontos_viagem" , nullable = false)
    private int pontos;

    @Column(name = "horario_viagem", nullable = false)
    private LocalDate horarioViagem;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    // Construtor com parâmetros
    public Viagem(int pontos, LocalDate horarioViagem, Cliente cliente) {
        this.pontos = pontos;
        this.horarioViagem = horarioViagem;
        this.cliente = cliente;
    }


}