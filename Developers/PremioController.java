package com.example.demo.controller;

import com.example.demo.model.Cliente;
import com.example.demo.model.Premio;
import com.example.demo.service.ClienteService;
import com.example.demo.service.PremioService;
import com.example.demo.validator.PremioValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Premio")
@CrossOrigin
public class PremioController {
    @Autowired
    PremioValidator premioValidator;
    @Autowired
    PremioService premioService;
    @PostMapping("/adicionarpremio")
    public ResponseEntity<Object> adicionarPremio(@RequestBody Premio premio, BindingResult bindingResult){
        premioValidator.validate(premio, bindingResult);

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }

        Premio novoPremio = new Premio();
        novoPremio.setNomePremio(premio.getNomePremio());
        novoPremio.setDescricao((premio.getDescricao()));
        novoPremio.setPontos_pr(premio.getPontos_pr());

        boolean sucesso = premioService.guardarPremio(novoPremio);

        // Verifica se o registro foi bem-sucedido
        if (sucesso) {
            // Se o registro foi bem-sucedido, retorna uma resposta HTTP de sucesso 200
            return ResponseEntity.ok().body("Prêmio registrado com sucesso.");
        } else {
            // Se houve uma falha ao registrar o prêmio, retorna uma resposta HTTP de erro 400
            return ResponseEntity.badRequest().body("Falha ao registrar o prêmio.");
        }
    }

    @PostMapping("/editarpremio")
    public ResponseEntity<Object> editarPremio(@RequestBody Premio premio) {
        if (premioService.editarPremio(premio)) {
            return ResponseEntity.ok().body("Prêmio editado com sucesso.");
        } else {
            return ResponseEntity.badRequest().body("Falha ao editar o prêmio.");
        }
    }

    @GetMapping("/consultarpremios")
    public List<Premio> ConsultarPremios(){
        return premioService.listaPremio();
    }

    @DeleteMapping("/removerpremio")
    public ResponseEntity<String> removerPremio(@RequestBody Premio premio) {
        boolean removido = premioService.removerPremio(premio);
        if (removido) {
            return ResponseEntity.ok("Prêmio removido com sucesso");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
