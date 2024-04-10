package com.example.dai;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Consola {
    private final Scanner scanner = new Scanner(System.in);
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");


    public void escrever(String mensagem) {
        System.out.println(mensagem);
    }

    public void escreverErro(String mensagem) {
        System.err.println(mensagem);
    }

    public String lerString(String mensagem) {
        escrever(mensagem);
        return scanner.nextLine();
    }

    public int lerInteiro(String mensagem) {
        Integer numero = null;
        String texto;

        do {
            escrever(mensagem);
            texto = scanner.nextLine();

            try {
                numero = Integer.valueOf(texto);
            } catch (NumberFormatException e) {
                escreverErro(texto + " não é um número inteiro válido.");
            }

        } while (numero == null);

        return numero;
    }


    public double lerDecimal(String mensagem) {
        Double numero = null;
        String texto;

        do {
            escrever(mensagem);
            texto = scanner.nextLine();

            try {
                numero = Double.valueOf(texto);
            } catch (NumberFormatException e) {
                escreverErro(texto + " não é um número decimal válido.");
            }
        } while (numero == null);

        return numero;
    }

    public LocalDate lerData(String mensagem) {
        LocalDate data = null;

        do {
            escrever(mensagem);
            String texto = scanner.nextLine();

            try {
                data = LocalDate.parse(texto, dateFormatter);
            } catch (Exception e) {
                escreverErro(texto + " não é uma data válida no formato dd/MM/yyyy.");
            }
        } while (data == null);

        return data;
    }

    public char lerChar(String mensagem) {
        char caractere = 0;

        do {
            escrever(mensagem);
            String texto = scanner.nextLine();

            if (texto.length() == 1) {
                caractere = texto.charAt(0);
            } else {
                escreverErro("Por favor, insira apenas um caractere.");
            }
        } while (caractere == 0);

        return caractere;
    }
}



