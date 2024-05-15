package com.example.demo.service;

import com.example.demo.model.Admin;
import com.example.demo.model.Parceiro;
import com.example.demo.model.Premio;
import com.example.demo.repository.ParceiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ParceiroService {
    @Autowired
    ParceiroRepository parceiroRepository;
    public boolean verificarParceiro(Parceiro parceiro){
        Optional<Parceiro> parceiroOptional = parceiroRepository.findById(parceiro.getId());
        if (parceiroOptional.isPresent()) {
            Parceiro parceiroNoBanco = parceiroOptional.get();
            return parceiroNoBanco.getPin() == (parceiro.getPin());
        } else {
            return false; // Se o parceiro não existir no banco, retorna false
        }
    }

    public boolean guardarParceiro(Parceiro parceiro){
        Parceiro parceirosalvo = new Parceiro();
        parceirosalvo.setNome(parceiro.getNome());
        parceirosalvo.setPin(parceiro.getPin());
        Parceiro adminSalvo = parceiroRepository.save(parceirosalvo);
        return adminSalvo != null;
    }

    public boolean removerParceiro(Parceiro parceiro){
        Optional<Parceiro> parceiroParaRemover = parceiroRepository.findById(parceiro.getId());

        if(parceiroParaRemover.isPresent()){
            parceiroRepository.delete(parceiroParaRemover.get());
            return true;
        }
        System.out.println("Premio não encontrado");
        return false;

    }
}
