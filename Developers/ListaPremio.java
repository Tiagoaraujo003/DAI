package com.example.dai;


import java.util.ArrayList;

public class ListaPremio {
    private ArrayList<Premio> listaPremios;
    private Consola con;

    public ListaPremio(){
        listaPremios = new ArrayList<>();

    }

    public void adicionarPremio(Premio premio){
        listaPremios.add(premio);
    }

    public void removerPremio(Premio premio){
        listaPremios.remove(premio);
    }

    public void ConsultarListaPremios(){
        for(Premio premio : listaPremios){
            System.out.println(premio);
        }
    }

    public void ciarPremio(){
        String nomeP = con.lerString("Nome do prémio:");
        String descricaoP = con.lerString("Descrição do prémio:");
        int pontosP = con.lerInteiro("Pontos do prémio:");

        Premio premio = new Premio(pontosP,nomeP,descricaoP);

        adicionarPremio(premio);
    }

    public void editarPremio(Premio premio) {

        // Opções para o usuário escolher
        System.out.println("Escolha o que deseja editar:");
        System.out.println("1. Nome do prêmio");
        System.out.println("2. Descrição do prêmio");
        System.out.println("3. Pontos do prêmio");

        int escolha = con.lerInteiro("Insira o número da opção desejada:");

        switch (escolha) {
            case 1:
                String novoNomeP = con.lerString("Insira o novo nome do prêmio:");
                // Código para atualizar o nome do prêmio no sistema
                premio.setNomeP(novoNomeP);
                break;
            case 2:
                String novaDescricaoP = con.lerString("Insira a nova descrição do prêmio:");
                // Código para atualizar a descrição do prêmio no sistema
                premio.setDescricaoP(novaDescricaoP);
                break;
            case 3:
                int novosPontosP = con.lerInteiro("Insira os novos pontos do prêmio:");
                // Código para atualizar os pontos do prêmio no sistema
                premio.setPontosP(novosPontosP);
                break;
            default:
                System.out.println("Opção inválida.");
                break;
        }
    }
}
