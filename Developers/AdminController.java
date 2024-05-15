package com.example.demo.controller;


import com.example.demo.model.Admin;
import com.example.demo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Admin")
@CrossOrigin
public class AdminController {
    @Autowired
    private AdminService adminservice;


    @GetMapping("/verificar")
    public boolean verificar(Admin admin){
        return adminservice.verificarAdmin(admin);
    }

    @PostMapping("/adicionarAdmin")
    public ResponseEntity<Object> adicionarAdmin(Admin admin){
        if(adminservice.guardarAdmin(admin)){
            return ResponseEntity.ok().body("Admin registrado com sucesso.");
        }else{
            return ResponseEntity.badRequest().body("Falha ao registrar o Admin.");
        }
    }

}
