package com.example.dai;

import java.util.ArrayList;

public class ListaParceiro {
    private ArrayList<Parceiro> listaParceiros;
    ListaPremio listaP;

    public ListaParceiro(){
        listaParceiros=new ArrayList<>();
        listaP = new ListaPremio();
    }

    public void adicionarParceiro(Parceiro parceiro){
        listaParceiros.add(parceiro);
    }

    public void removerParceiro(Parceiro parceiro){
        listaParceiros.remove(parceiro);
    }

}
