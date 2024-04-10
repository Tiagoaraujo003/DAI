package com.example.dai;

import java.util.ArrayList;

public class ListaAdmin {
    private ArrayList<Admin> listaAdmin;

    public ListaAdmin(){
        listaAdmin = new ArrayList<>();
    }

    public void adicionarAdmin(Admin admin){
        listaAdmin.add(admin);
    }

    public void removerAdmin(Admin admin){
        listaAdmin.remove(admin);
    }
}

