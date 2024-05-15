package com.example.demo.controller;

import com.example.demo.model.Cliente;
import com.example.demo.model.Viagem;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.service.ClienteService;
import com.example.demo.service.ViagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Viagem")
@CrossOrigin

public class ViagemController {
    @Autowired
    ClienteService clienteService;
    @Autowired
    ViagemService viagemService;

    @PostMapping("/viagem/validar")
    public void validarViagem(@RequestBody Cliente cliente) {
        viagemService.validarViagem(cliente);
    }

    @PostMapping("/viagem/pontos")
    public ResponseEntity<String> definirPontosViagem(@RequestParam int pontos) {
        viagemService.definirPontosDaViagem(pontos);

        return ResponseEntity.ok("Pontos da viagem definidos como: " + pontos);
    }
}
