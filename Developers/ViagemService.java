package com.example.demo.service;

import com.example.demo.model.Cliente;
import com.example.demo.model.Viagem;
import com.example.demo.repository.ClienteRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class ViagemService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteService clienteService;

    private int pontosDaViagem;

    public void definirPontosDaViagem(int pontos) {
        this.pontosDaViagem = pontos;
        System.out.println("Pontos da viagem definidos como: " + pontos);
    }

    public void validarViagem(Cliente cliente) {
        Long clienteId = cliente.getClienteId();
        Cliente clienteEncontrado = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com ID: " + clienteId));

        LocalDate dataDaViagem = LocalDate.now();
        Viagem viagem = criarViagem(clienteEncontrado, dataDaViagem);

        // Adicionar a viagem ao histórico de viagens do cliente
        clienteEncontrado.getHistoricoViagens().add(viagem);

        // Atualizar os pontos do cliente e salvar no repositório
        clienteService.adicionarPontos(clienteEncontrado, viagem);
        clienteRepository.save(clienteEncontrado);

        System.out.println("Viagem validada para o cliente " + clienteEncontrado.getNomeCliente()
                + ". " + viagem.getPontos() + " pontos adicionados à conta.");
    }

    public Viagem criarViagem(Cliente cliente,LocalDate horarioViagem) {
        return new Viagem(pontosDaViagem, horarioViagem,cliente);
    }
}
