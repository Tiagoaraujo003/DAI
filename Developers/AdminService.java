package com.example.demo.service;

import com.example.demo.model.Admin;
import com.example.demo.model.Cliente;
import com.example.demo.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    AdminRepository adminRepository;

    public List<Admin> imprimir(){
        return adminRepository.findAll();
    }
    public boolean verificarAdmin(Admin admin){
        Optional<Admin> adminOptional = adminRepository.findById(admin.getId());
        if (adminOptional.isPresent()) {
            Admin adminNoBanco = adminOptional.get();
            return adminNoBanco.getPin() == (admin.getPin());
        } else {
            return false; // Se o admin não existir no banco, retorna false

        }
    }

    public boolean editarAdmin( Admin editarAdmin){
        Optional<Admin> adminOptional = adminRepository.findById(editarAdmin.getId());

        if (adminOptional.isPresent()) {
            Admin adminNoBanco = adminOptional.get();

            // Verificar e atualizar cada campo apenas se for fornecido um novo valor
            if (editarAdmin.getPin() != ' ' && editarAdmin.getPin() < 0) {
                adminNoBanco.setId(editarAdmin.getId());
            }
            if (editarAdmin.getEmail() != null && !editarAdmin.getEmail().isEmpty()) {
                adminNoBanco.setEmail(adminNoBanco.getEmail());
            }


            // Salvar o cliente atualizado no banco de dados
            Admin adminEditado = adminRepository.save(adminNoBanco);

            return adminEditado != null;
        } else {
            return false; // Cliente não encontrado no banco de dados
        }

    }

    public boolean guardarAdmin(Admin admin){
        Admin adminsalvo = new Admin();
        adminsalvo.setEmail(admin.getEmail());
        adminsalvo.setPin(admin.getPin());
        Admin adminSalvo = adminRepository.save(adminsalvo);
        return adminSalvo != null;
    }
}
