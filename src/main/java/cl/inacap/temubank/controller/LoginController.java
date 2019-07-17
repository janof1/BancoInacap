package cl.inacap.temubank.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cl.inacap.temubank.models.Cliente;
import cl.inacap.temubank.repository.ClienteRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {
	@Autowired
	ClienteRepository clienteRepository;
	@PostMapping("/login")
    public Cliente createTransacciones(@Valid @RequestBody Cliente clienteLogin) {
		Cliente cliente = clienteRepository.findByRut(clienteLogin.getRut());
        
		return cliente;
    }

}
