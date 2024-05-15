package com.example.demo.controller;

import com.example.demo.model.Admin;
import com.example.demo.model.Parceiro;
import com.example.demo.model.Premio;
import com.example.demo.repository.ParceiroRepository;
import com.example.demo.service.ClienteService;
import com.example.demo.service.ParceiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/Parceiro")
@CrossOrigin
public class ParceiroController {
    @Autowired
    ParceiroService parceiroService;

    @GetMapping("/verificar")
    public boolean verificarParceiro(Parceiro parceiro){
        return parceiroService.verificarParceiro(parceiro);
    }

    @PostMapping("/adicionarParceiro")
    public ResponseEntity<Object> adicionarParceiro(Parceiro parceiro){
        if(parceiroService.guardarParceiro(parceiro)){
            return ResponseEntity.ok().body("Parceiro registrado com sucesso.");
        }else{
            return ResponseEntity.badRequest().body("Falha ao registrar o Parceiro.");
        }
    }

    @DeleteMapping("/removerparceiro")
    public ResponseEntity<String> removerParceiro(@RequestBody Parceiro parceiro) {
        boolean removido = parceiroService.removerParceiro(parceiro);
        if (removido) {
            return ResponseEntity.ok("Parceiro removido com sucesso");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
