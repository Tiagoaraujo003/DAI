package com.example.dai;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.*;


public class Cliente{
    private String nomeC;
    private String passC;
    private String emailC;
    private LocalDate dataNascimento;
    private String nmrTeleC;
    private String moradaC;
    private char generoC;
    private String codC;
    private int pontosC;
    private ArrayList listaPremiosC;
    private ArrayList listaViagens;
    private static int contador = 1; // contador para os codigos
    private static Set<String> codigosUtilizados = new HashSet<>();// guardas os codigo que ja foram utilizados


    public Cliente(String nomeC, String passC, String emailC, LocalDate dataNascimento, char generoC, String codC, int pontosC) {
        this.nomeC = nomeC;
        this.passC=passC;
        this.emailC = emailC;
        this.dataNascimento = dataNascimento;
        this.setGeneroC(generoC);
        this.codC= gerarCodigoUnico();
        this.pontosC=pontosC;
        this.listaPremiosC=new ArrayList<>();
        this.listaViagens= new ArrayList();
    }

    public String getNomeC() {
        return nomeC;
    }

    public void setNomeC(String nomeC) {
        this.nomeC = nomeC;
    }

    public String getPassC() {
        return passC;
    }

    public void setPassC(String passC) {
        this.passC = passC;
    }

    public String getEmailC() {
        return emailC;
    }

    public void setEmailC(String emailC) {
        this.emailC = emailC;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento=dataNascimento;
    }

    public int getPontosC() {
        return pontosC;
    }

    public void setPontosC(int pontosC) {
        this.pontosC = pontosC;
    }

    public String getCodC() {
        return codC;
    }

    public char getGeneroC() {
        return generoC;
    }

    public void setGeneroC(char generoC) {
        this.generoC=generoC;
    }

    public int getpontosC(){
        return pontosC;
    }


    public static String gerarCodigoUnico() { //cria codigo a medida que os clientes se registram
        String codigo;                        // e o hashset verifica se eles não são repetidos se neste caso forem cria outro
        do {
            codigo = "C" + contador++;
        } while (codigosUtilizados.contains(codigo));
        codigosUtilizados.add(codigo);
        return codigo;
    }

    public static void gerarQRCode(String codigoCliente, String caminhoDestino) {
        int largura = 300;
        int altura = 300;
        String formatoImagem = "png";

        // Configurar hints para codificação do QR code
        HashMap<EncodeHintType, String> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

        try {
            // Criar objeto QRCodeWriter
            QRCodeWriter qrWriter = new QRCodeWriter();

            // Codificar o texto do QR code em uma matriz de bits
            BitMatrix bitMatrix = qrWriter.encode(codigoCliente, BarcodeFormat.QR_CODE, largura, altura, hints);

            // Escrever a matriz de bits em um arquivo de imagem
            Path caminho = FileSystems.getDefault().getPath(caminhoDestino);
            MatrixToImageWriter.writeToPath(bitMatrix, formatoImagem, caminho);
            System.out.println("QR code gerado com sucesso em: " + caminhoDestino);
        } catch (WriterException | IOException e) {
            System.out.println("Erro ao gerar o QR code: " + e.getMessage());
        }
    }

    public void adicionarPontos(int pontos) {
        this.pontosC += pontos;
    }

    public void verPontos(){
        System.out.println(getPontosC());
    }

    public void verCodigo(){
        System.out.println(getCodC());
    }

    public void resgatarPremio(Premio premio){
        listaPremiosC.add(premio);
    }

}

