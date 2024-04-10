package com.example.dai;

public class Premio {
    private int pontosP;
    private String nomeP;
    private String descricaoP;

    public Premio(int pontosP, String nomeP,String descricaoP) {
        this.pontosP = pontosP;
        this.nomeP = nomeP;
    }

    public int getPontosP() {
        return pontosP;
    }

    public void setPontosP(int pontosP) {
        this.pontosP = pontosP;
    }

    public String getNomeP() {
        return nomeP;
    }

    public void setNomeP(String nomeP) {
        this.nomeP = nomeP;
    }

    public String getDescricaoP() {
        return descricaoP;
    }

    public void setDescricaoP(String descricaoP) {
        this.descricaoP = descricaoP;
    }
}
