package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "CLIENTE")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ClienteId;
    @Column(name = "nome" , nullable = false)
    private String NomeCliente;
    @Column(name = "email" , nullable = false)
    private String Email;
    @Column(name = "password" , nullable = false)
    private String Password;
    @Column(name = "datanasc" , nullable = false)
    private LocalDate DataNascimento;
    @Column(name = "genero" , nullable = false)
    private String Genero;
    @Column(name = "codigo")
    private String CodigoCliente;
    @Column(name = "pontos")
    private int Pontos;

    @ManyToMany
    @JoinTable(
            name = "CLIENTE_PREMIO", joinColumns = @JoinColumn(name = "cliente_id"),
            inverseJoinColumns = @JoinColumn(name = "premio_id")
    )
    private List<Premio> premiosResgatados;

    @OneToMany(mappedBy = "cliente")
    private List<Viagem> historicoViagens;

}
