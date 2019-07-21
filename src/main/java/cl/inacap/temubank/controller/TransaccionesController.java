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

import cl.inacap.temubank.exception.ClienteNotFoundException;
import cl.inacap.temubank.models.Transacciones;
import cl.inacap.temubank.repository.TransaccionesRepository;

@RestController
public class TransaccionesController {
	
	// Get All Transacciones
		@Autowired
		TransaccionesRepository transaccionesRepository;
		@GetMapping("/transacciones")
		public List<Transacciones> getAllTransacciones(){
			return (List<Transacciones>) transaccionesRepository.findAll();
		}
		// Create a new Transacciones
		@PostMapping("/transacciones")
	    public Transacciones createTransacciones(@Valid @RequestBody Transacciones transacciones) {
	        return transaccionesRepository.save(transacciones);
	    }
		// Get a Single Transacciones
	    @GetMapping("/transacciones/{id}")
	    public Transacciones getTransaccionesById(@PathVariable(value = "id") Long transaccionId) throws ClienteNotFoundException {
	        return transaccionesRepository.findById(transaccionId).orElseThrow(() -> new ClienteNotFoundException(transaccionId));
	    }

	    // Update a Transacciones
	    @PutMapping("/transacciones/{id}")
	    public Transacciones updateTransacciones(@PathVariable(value = "id") Long transaccionId, @Valid @RequestBody Transacciones transaccionUpdate) throws ClienteNotFoundException {
	    	Transacciones transacciones = transaccionesRepository.findById(transaccionId).orElseThrow(() -> new ClienteNotFoundException(transaccionId));
	    	transacciones.setMonto(transaccionUpdate.getMonto());
	    	transacciones.setFecha(transaccionUpdate.getFecha());
	    	transacciones.setTipo_operacion(transaccionUpdate.getTipo_operacion());
	   
	    	
	        
	    	Transacciones updatedTransacciones = transaccionesRepository.save(transacciones);

	    	return updatedTransacciones;
	    }

	    // Delete a Transacciones
	    @DeleteMapping("/transacciones/{id}")
	    public ResponseEntity<?> deleteTransacciones(@PathVariable(value = "id") Long transaccionId) throws ClienteNotFoundException {
	    	Transacciones transacciones = transaccionesRepository.findById(transaccionId).orElseThrow(() -> new ClienteNotFoundException(transaccionId));

	    	transaccionesRepository.delete(transacciones);

	        return ResponseEntity.ok().build();
	    }
	    
	 // Get all Transacciones por id de cuenta
	    @GetMapping("/transacciones/cuenta/{id}")
	    public List<Transacciones> getTransaccionesByIdCuenta(@PathVariable(value = "id") Long CuentaId) {
	        return (List<Transacciones>) transaccionesRepository.findByCuentaOrigenId(CuentaId);
	    }

}
