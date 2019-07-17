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

import cl.inacap.temubank.exception.RolesNotFoundException;
import cl.inacap.temubank.models.Roles;
import cl.inacap.temubank.repository.RolClienteRepository;

@RestController
public class RolesController {
	
	@Autowired
	RolClienteRepository rolClienteRepository;
	
	@GetMapping("/roles")
	public List<Roles> getAllRoles(){
		return (List<Roles>) rolClienteRepository.findAll();
	}
	// Create a new roles
	@PostMapping("/roles")
    public Roles createRol(@Valid @RequestBody Roles roles) {
        return rolClienteRepository.save(roles);
    }
	// Get a Single roles
    @GetMapping("/roles/{id}")
    public Roles getRolById(@PathVariable(value = "id") Long rolId) throws RolesNotFoundException {
        return rolClienteRepository.findById(rolId).orElseThrow(() -> new RolesNotFoundException(rolId));
    }

    // Update a roles
    @PutMapping("/roles/{id}")
    public Roles updateRol(@PathVariable(value = "id") Long rolId, @Valid @RequestBody Roles rolUpdate) throws RolesNotFoundException {
    	Roles rol = rolClienteRepository.findById(rolId).orElseThrow(() -> new RolesNotFoundException(rolId));
    	rol.setDescripcion(rolUpdate.getDescripcion());
        
    	Roles updatedRoles = rolClienteRepository.save(rol);

    	return updatedRoles;
    }

    // Delete a Cliente
    @DeleteMapping("/roles/{id}")
    public ResponseEntity<?> deleteRol(@PathVariable(value = "id") Long rolId) throws RolesNotFoundException {
    	Roles rol = rolClienteRepository.findById(rolId).orElseThrow(() -> new RolesNotFoundException(rolId));

    	rolClienteRepository.delete(rol);

        return ResponseEntity.ok().build();
    }

}
