package com.example.dai;

import java.time.LocalDate;

public class Viagens {
    private LocalDate dataViagem;

    public Viagens(LocalDate dataViagem){
        this.dataViagem=dataViagem;
    }

    public LocalDate getDataViagem() {
        return dataViagem;
    }

    public void setDataViagem(LocalDate dataViagem) {
        this.dataViagem = dataViagem;
    }
}
