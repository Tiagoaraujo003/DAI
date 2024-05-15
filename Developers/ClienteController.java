package com.example.demo.controller;

import com.example.demo.model.Cliente;
import com.example.demo.model.Premio;
import com.example.demo.model.Viagem;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.example.demo.validator.ClienteValidator;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Clientes")
@CrossOrigin
public class ClienteController {
    @Autowired
    ClienteService clienteService;
    @Autowired
    ClienteValidator clienteValidator;
    @Autowired
    ClienteRepository clienteRepository;

    @PostMapping("/registo")
    public ResponseEntity<Object> registo(@RequestBody Cliente cliente, BindingResult bindingResult) {

        clienteValidator.validate(cliente, bindingResult);

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }

        Cliente novocliente = new Cliente();
        novocliente.setNomeCliente(cliente.getNomeCliente());
        novocliente.setEmail(cliente.getEmail());
        novocliente.setPassword(cliente.getPassword());
        novocliente.setDataNascimento(cliente.getDataNascimento());
        novocliente.setGenero(cliente.getGenero());
        String novoCodigo = clienteService.criarcodigos();
        novocliente.setCodigoCliente(novoCodigo);
        novocliente.setPontos(0);

        if (clienteService.ExisteClienteCriado(novocliente)) {
            return ResponseEntity.badRequest().body("Já existe um cliente com o mesmo email ou nome.");
        } else {
            if (clienteService.guardarCliente(novocliente)) {
                return ResponseEntity.ok().body("Cliente registrado com sucesso.");
            } else {
                return ResponseEntity.badRequest().body("Falha ao registrar o cliente.");
            }
        }
    }

    @GetMapping("/login")
    public boolean login(@RequestBody Cliente cliente) {
        return clienteService.LoginCliente(cliente);
    }

    @PostMapping("/editarcliente")
    public ResponseEntity<Object> EditarCliente(@RequestBody Cliente cliente, BindingResult bindingResult) {

        clienteValidator.validate(cliente, bindingResult);

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }


        if (clienteService.ExisteClienteCriado(cliente)) {
            return ResponseEntity.badRequest().body("Já existe um cliente com o mesmo email ou nome.");
        } else {
            if (clienteService.editarCliente(cliente)) {
                return ResponseEntity.ok().body("Cliente editado com sucesso.");
            } else {
                return ResponseEntity.badRequest().body("Falha ao editar o cliente.");
            }
        }
    }

    @PostMapping("/resgatarpremio")
    public ResponseEntity<Object> ResgatarPremio(@RequestBody Cliente cliente, Premio premio) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(cliente.getClienteId());
        if (clienteOptional.isPresent()) {
            Cliente clientenobanco = clienteOptional.get();
            clienteService.receberPremio(clientenobanco, premio);
            return ResponseEntity.ok().body("Prêmio recebido com sucesso.");
        } else {
            return ResponseEntity.badRequest().body("Cliente não encontrado.");
        }
    }

    @DeleteMapping("/removercliente")
    public ResponseEntity<String> RemoverCliente(@RequestBody Cliente cliente) {
        boolean removido = clienteService.removerCliente(cliente);
        if (removido) {
            return ResponseEntity.ok("Cliente removido com sucesso");
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/pontos")
    public ResponseEntity<Integer> obterPontosDoCliente(@RequestBody Cliente cliente) {
        int pontos = clienteService.obterPontosDoCliente(cliente);
        return ResponseEntity.ok().body(pontos);
    }

    @GetMapping("/codigo")
    public ResponseEntity<String> obterCodigoDoCliente(@RequestBody Cliente cliente) {
        String codigo = clienteService.obterCodigoDoCliente(cliente);
        return ResponseEntity.ok().body(codigo);
    }

    @GetMapping("/listaviagens")
    public List<Viagem> listarViagensDoCliente(@RequestBody Cliente cliente) {
        return clienteService.listarViagensDoCliente(cliente);
    }

    @GetMapping("/listapremio")
    public List<Premio> listarPremiosResgatadosDoCliente(@RequestBody Cliente cliente) {
        return clienteService.listarPremiosResgatadosDoCliente(cliente);
    }
}
