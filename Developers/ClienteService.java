package com.example.demo.service;

import com.example.demo.model.*;
import com.example.demo.repository.ClienteRepository;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.*;

@Service
public class ClienteService {
   @Autowired
    private ClienteRepository clienteRepository;

    public boolean guardarCliente(Cliente cliente){
        Cliente clienteSalvo = clienteRepository.save(cliente);
        return clienteSalvo != null;
    }

    public boolean clienteEncontrado(Cliente cliente) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(cliente.getClienteId());

        // Verifica se o cliente está presente no repositório
        return clienteOptional.isPresent();
    }

    public List<Cliente> imprimir(){
        return clienteRepository.findAll();
    }

    public boolean ExisteClienteCriado(Cliente cliente){
        Optional<Cliente> clienteOptional = clienteRepository.findById(cliente.getClienteId());
        if (clienteOptional.isPresent()) {
            Cliente clienteNoBanco = clienteOptional.get();
            return clienteNoBanco.getEmail().equals(cliente.getEmail())
                    || clienteNoBanco.getNomeCliente().equals(cliente.getNomeCliente());
        } else {
            return false; // Se o admin não existir no banco, retorna false
        }
    }

    public boolean LoginCliente(Cliente cliente){
        Optional<Cliente> clienteOptional = clienteRepository.findById(cliente.getClienteId());

        if (clienteOptional.isPresent()) {
            Cliente clienteNoBanco = clienteOptional.get();
            return clienteNoBanco.getEmail().equals(cliente.getEmail())
            || clienteNoBanco.getPassword().equals(cliente.getPassword());
        } else {
            return false; // Se o admin não existir no banco, retorna false
        }
    }

    public String criarcodigos() {
        // Prefixo "C" para o código
        String prefixo = "C";

        // Define o tamanho máximo para o número aleatório
        int maximo = 999999999;

        // Lista para armazenar os códigos já gerados
        List<String> codigosGerados = new ArrayList<>();

        // Gera um novo código até que um código único seja encontrado
        while (true) {
            // Gera um número aleatório de 1 a 999999999 (9 dígitos)
            int numeroAleatorio = new Random().nextInt(maximo) + 1;

            // Concatena o prefixo com o número aleatório formatado
            String novoCodigo = prefixo + String.format("%09d", numeroAleatorio);

            // Verifica se o novo código já foi gerado anteriormente
            if (!codigosGerados.contains(novoCodigo)) {
                // Se o novo código não estiver na lista de códigos gerados, retorna o código
                codigosGerados.add(novoCodigo); // Adiciona o novo código à lista de códigos gerados
                return novoCodigo;
            }
        }
    }

    public static byte[] gerarQRCode(Cliente cliente) {
        int largura = 300;
        int altura = 300;
        String formatoImagem = "png";

        // Configurar hints para codificação do QR code
        HashMap<EncodeHintType, String> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

        try {
            // Criar objeto QRCodeWriter
            QRCodeWriter qrWriter = new QRCodeWriter();

            // Gerar o código QR a partir do código do cliente
            String codigoCliente = cliente.getCodigoCliente();

            // Codificar o texto do QR code em uma matriz de bits
            BitMatrix bitMatrix = qrWriter.encode(codigoCliente,
                    BarcodeFormat.QR_CODE, largura, altura, hints);

            // Converter a matriz de bits em uma imagem QR code
            BufferedImage imagemQR = MatrixToImageWriter.toBufferedImage(bitMatrix);

            // Converter a imagem para um array de bytes
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(imagemQR, formatoImagem, outputStream);
            byte[] qrCodeBytes = outputStream.toByteArray();

            return qrCodeBytes;
        } catch (WriterException | IOException e) {
            System.out.println("Erro ao gerar o QR code: " + e.getMessage());
            return null;
        }
    }


    public boolean editarCliente( Cliente editarcliente){
        Optional<Cliente> clienteOptional = clienteRepository.findById(editarcliente.getClienteId());

        if (clienteOptional.isPresent()) {
            Cliente clienteNoBanco = clienteOptional.get();

            // Verificar e atualizar cada campo apenas se for fornecido um novo valor
            if (editarcliente.getNomeCliente() != null && !editarcliente.getNomeCliente().isEmpty()) {
                clienteNoBanco.setNomeCliente(editarcliente.getNomeCliente());
            }
            if (editarcliente.getPassword() != null && !editarcliente.getPassword().isEmpty()) {
                clienteNoBanco.setPassword(editarcliente.getPassword());
            }
            if (editarcliente.getDataNascimento() != null) {
                clienteNoBanco.setDataNascimento(editarcliente.getDataNascimento());
            }
            if (editarcliente.getGenero() != null && !editarcliente.getGenero().isEmpty()) {
                clienteNoBanco.setGenero(editarcliente.getGenero());
            }

            // Salvar o cliente atualizado no banco de dados
            Cliente clienteEditado = clienteRepository.save(clienteNoBanco);

            return clienteEditado != null;
        } else {
            return false; // Cliente não encontrado no banco de dados
        }

    }
    public void adicionarPontos(Cliente cliente, Viagem viagem){
        int pontosAtuais = cliente.getPontos();
        int novosPontos = pontosAtuais + viagem.getPontos();
        cliente.setPontos(novosPontos);
    }

    public void receberPremio(Cliente cliente, Premio premio) {
        List<Premio> premiosResgatados = cliente.getPremiosResgatados();
        premiosResgatados.add(premio);
        cliente.setPremiosResgatados(premiosResgatados);
        clienteRepository.save(cliente);
    }

    public int obterPontosDoCliente(Cliente cliente) {
        return cliente.getPontos();
    }

    public String obterCodigoDoCliente(Cliente cliente) {
        return cliente.getCodigoCliente();
    }


    public List<Viagem> listarViagensDoCliente(Cliente cliente) {
        Cliente clientenobanco = clienteRepository.findById(cliente.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com ID: " + cliente.getClienteId()));

        return cliente.getHistoricoViagens();
    }

    public List<Premio> listarPremiosResgatadosDoCliente(Cliente cliente) {
        Cliente clientenobanco = clienteRepository.findById(cliente.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com ID: " + cliente.getClienteId()));

        return cliente.getPremiosResgatados();
    }

    public boolean removerCliente(Cliente cliente){
        Optional<Cliente> clienteParaRemover = clienteRepository.findById(cliente.getClienteId());

        if(clienteParaRemover.isPresent()){
            clienteRepository.delete(clienteParaRemover.get());
            return true;
        }
        System.out.println("cliente não encontrado");
        return false;

    }

}