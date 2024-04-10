package com.example.dai;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

public class SistemaAutocarro {

    private ListaCliente listaClientes;
    private int pontosPorViagem; // Defina a quantidade de pontos a serem adicionados por viagem

    public SistemaAutocarro(ListaCliente listaClientes) {
        this.listaClientes = listaClientes;
    }

    public int getPontosPorViagem() {
        return pontosPorViagem;
    }

    public void setPontosPorViagem(int pontosPorViagem) {
        this.pontosPorViagem = pontosPorViagem;
        System.out.println("Quantidade de pontos por viagem atualizada para: " + pontosPorViagem);
    }

    public void validarViagem(String codigoCliente) {
        Cliente cliente = listaClientes.encontrarClientePorCodigo(codigoCliente);
        if (cliente != null) {
            // Se o cliente existir, adiciona os pontos à sua conta
            cliente.adicionarPontos(pontosPorViagem);
            System.out.println("Viagem validada para o cliente " + cliente.getNomeC() + ". " + pontosPorViagem + " pontos adicionados à conta.");
        } else {
            System.out.println("Cliente com código " + codigoCliente + " não encontrado.");
        }
    }

    public void validarViagemPorQRCode(String caminhoImagemQRCode) {
        try {
            BufferedImage imagem = ImageIO.read(new File(caminhoImagemQRCode));
            LuminanceSource source = new BufferedImageLuminanceSource(imagem);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
            Result resultado = new MultiFormatReader().decode(bitmap);
            String codigoCliente = resultado.getText();
            validarViagem(codigoCliente);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao ler o QR code: " + e.getMessage());
        }
    }

}
