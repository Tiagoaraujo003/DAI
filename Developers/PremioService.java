package com.example.demo.service;

import com.example.demo.model.Cliente;
import com.example.demo.model.Premio;
import com.example.demo.repository.ParceiroRepository;
import com.example.demo.repository.PremioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class PremioService {
    @Autowired
    PremioRepository premioRepository;

    public List<Premio> listaPremio(){
        List<Premio> listaPremio = premioRepository.findAll();
        return listaPremio;
    }
    public boolean guardarPremio(Premio premio){
        Premio premioSalvo = premioRepository.save(premio);
        return premioSalvo != null;
    }
    public boolean PremioJaExiste(Premio premio){
        Optional<Premio> clienteOptional = premioRepository.findById(premio.getPremioId());
        if (clienteOptional.isPresent()) {
            Premio premionobanco = clienteOptional.get();
            return premionobanco.getNomePremio().equals(premio.getNomePremio());
        } else {
            return false; // Se o admin não existir no banco, retorna false
        }

    }

    public boolean editarPremio(Premio premio) {
        Optional<Premio> premioOptional = premioRepository.findById(premio.getPremioId());

        if (premioOptional.isPresent()) {
            Premio premioNoBanco = premioOptional.get();

            // Verificar e atualizar cada campo apenas se for fornecido um novo valor
            if (premio.getNomePremio() != null && !premio.getNomePremio().isEmpty()) {
                premioNoBanco.setNomePremio(premio.getNomePremio());
            }
            if (premio.getDescricao() != null && !premio.getDescricao().isEmpty()) {
                premioNoBanco.setDescricao(premio.getDescricao());
            }
            if (premio.getPontos_pr() < 0) {
                premioNoBanco.setPontos_pr(premio.getPontos_pr());
            }

            // Salvar o prêmio atualizado no banco de dados
            Premio premioEditado = premioRepository.save(premioNoBanco);

            return premioEditado != null;
        } else {
            return false; // Prêmio não encontrado no banco de dados
        }
    }

    public boolean removerPremio(Premio premio){
        Optional<Premio> premioParaRemover = premioRepository.findByPremioId(premio.getPremioId());

        if(premioParaRemover.isPresent()){
            premioRepository.delete(premioParaRemover.get());
            return true;
        }
            System.out.println("Premio não encontrado");
            return false;

    }
}
