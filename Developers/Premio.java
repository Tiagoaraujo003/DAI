package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "Premio")
public class Premio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long PremioId;
    @Column(name = "nome" , nullable = false)
    private String NomePremio;
    @Column(name = "descricao" , nullable = false)
    private String Descricao;
    @Column(name = "pontos" , nullable = false)
    private int Pontos_pr;
}
