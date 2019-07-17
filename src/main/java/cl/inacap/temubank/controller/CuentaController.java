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

import cl.inacap.temubank.exception.CuentaNotFoundException;
import cl.inacap.temubank.models.Cuenta;
import cl.inacap.temubank.repository.CuentaRepository;

@RestController
public class CuentaController {
	
	@Autowired
	CuentaRepository cuentaRepository;
	
	@GetMapping("/cuentas")
	public List<Cuenta> getAllCuentas(){
		return ( List<Cuenta>)cuentaRepository.findAll();
	}
	// Create a new Cuenta
	@PostMapping("/cuentas")
    public Cuenta createCuenta(@Valid @RequestBody Cuenta cuenta) {
        return cuentaRepository.save(cuenta);
    }
	
	// Get a Single Cuenta
    @GetMapping("/cuentas/{id}")
    public Cuenta getCuentasById(@PathVariable(value = "id") Long cuentaId) throws CuentaNotFoundException {
        return cuentaRepository.findById(cuentaId).orElseThrow(() -> new CuentaNotFoundException(cuentaId));
    }
    
    // Update a Cuenta
    @PutMapping("/cuentas/{id}")
    public Cuenta updateCuenta(@PathVariable(value = "id") Long cuentaId, @Valid @RequestBody Cuenta cuentaUpdate) throws CuentaNotFoundException {
    	Cuenta cuenta = cuentaRepository.findById(cuentaId).orElseThrow(() -> new CuentaNotFoundException(cuentaId));
    	cuenta.setCliente(cuentaUpdate.getCliente());
    	cuenta.setProductos(cuentaUpdate.getProductos());
    	cuenta.setTipo_cuenta(cuentaUpdate.getTipo_cuenta());
    	
    	
        
    	Cuenta updatedCuenta = cuentaRepository.save(cuenta);

    	return updatedCuenta;
    }

    // Delete a Cuenta
    @DeleteMapping("/cuentas/{id}")
    public ResponseEntity<?> deleteCuenta(@PathVariable(value = "id") Long cuentaId) throws CuentaNotFoundException {
    	Cuenta cuenta = cuentaRepository.findById(cuentaId).orElseThrow(() -> new CuentaNotFoundException(cuentaId));

    	cuentaRepository.delete(cuenta);

        return ResponseEntity.ok().build();
    }


}
