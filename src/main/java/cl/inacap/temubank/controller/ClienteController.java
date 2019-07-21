package cl.inacap.temubank.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import cl.inacap.temubank.constant.ViewConstant;
import cl.inacap.temubank.exception.ClienteNotFoundException;
import cl.inacap.temubank.models.Cliente;
import cl.inacap.temubank.repository.ClienteRepository;

@RestController
public class ClienteController {
	// Get All clientes
	@Autowired
	ClienteRepository clienteRepository;
	
	@GetMapping("/crear")
	public ModelAndView exampleMAV() {
		
		ModelAndView mav = new ModelAndView(ViewConstant.ADD_CLIENTE_VIEW);
	
		return mav;
	}
	
	
	@GetMapping("/clientes")
	public List<Cliente> getAllClientes(){
		return (List<Cliente>) clienteRepository.findAll();
	}
	// Create a new Cliente
	@PostMapping("/clientes")
    public Cliente createCliente(@Valid @RequestBody Cliente cliente) {
        return clienteRepository.save(cliente);
    }
	// Get a Single Cliente
    @GetMapping("/clientes/{id}")
    public Cliente getClienteById(@PathVariable(value = "id") Long clienteId) throws ClienteNotFoundException {
        return clienteRepository.findById(clienteId).orElseThrow(() -> new ClienteNotFoundException(clienteId));
    }

    // Update a Cliente
    @PutMapping("/clientes/{id}")
    public Cliente updateCliente(@PathVariable(value = "id") Long clienteId, @Valid @RequestBody Cliente clienteUpdate) throws ClienteNotFoundException {
    	Cliente cliente = clienteRepository.findById(clienteId).orElseThrow(() -> new ClienteNotFoundException(clienteId));
    	cliente.setRut(clienteUpdate.getRut());
    	cliente.setNombre(clienteUpdate.getNombre());
    	cliente.setApellidoPaterno(clienteUpdate.getApellidoPaterno());
    	cliente.setCiudad(clienteUpdate.getCiudad());
    	cliente.setEmail(clienteUpdate.getEmail());
    	cliente.setRoles(clienteUpdate.getRoles());
    	
        
    	Cliente updatedCliente = clienteRepository.save(cliente);

    	return updatedCliente;
    }

    // Delete a Cliente
    @DeleteMapping("/clientes/{id}")
    public ResponseEntity<?> deleteCliente(@PathVariable(value = "id") Long clienteId) throws ClienteNotFoundException {
    	Cliente cliente = clienteRepository.findById(clienteId).orElseThrow(() -> new ClienteNotFoundException(clienteId));

        clienteRepository.delete(cliente);

        return ResponseEntity.ok().build();
    }
}


