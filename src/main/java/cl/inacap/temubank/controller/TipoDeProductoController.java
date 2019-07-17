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

import cl.inacap.temubank.exception.TipoDeProductosNotFoundException;
import cl.inacap.temubank.models.TipoDeProductos;
import cl.inacap.temubank.repository.TipoDeProductoRepository;

@RestController
public class TipoDeProductoController {
	// Get All tipoDeProducto
		@Autowired
		TipoDeProductoRepository tipoDeProductoRepository;
		@GetMapping("/tipodeproductos")
		public List<TipoDeProductos> getAllTipoDeProductos(){
			return (List<TipoDeProductos>) tipoDeProductoRepository.findAll();
		}
		// Create a new tipoDeProducto
		@PostMapping("/tipodeproductos")
	    public TipoDeProductos createTipoDeProductos(@Valid @RequestBody TipoDeProductos tipoDeProductos) {
	        return tipoDeProductoRepository.save(tipoDeProductos);
	    }
		// Get a Single tipoDeProducto
	    @GetMapping("/tipodeproductos/{id}")
	    public TipoDeProductos getTipoDeProductosById(@PathVariable(value = "id") Long tipoDeProductosId) throws TipoDeProductosNotFoundException {
	        return tipoDeProductoRepository.findById(tipoDeProductosId).orElseThrow(() -> new TipoDeProductosNotFoundException(tipoDeProductosId));
	    }

	    // Update a tipoDeProducto
	    @PutMapping("/tipodeproductos/{id}")
	    public TipoDeProductos updateTipoDeProductos(
	    		@PathVariable(value = "id") Long tipoDeProductosId, 
	    		@Valid @RequestBody TipoDeProductos tipoDeProductosUpdate) throws TipoDeProductosNotFoundException {
	    	TipoDeProductos tipoDeProductos = tipoDeProductoRepository.findById(tipoDeProductosId).orElseThrow(() -> new TipoDeProductosNotFoundException(tipoDeProductosId));
	    	tipoDeProductos.setDescripcion(tipoDeProductosUpdate.getDescripcion());
	    	tipoDeProductos.setProductos(tipoDeProductosUpdate.getProductos());
	        
	    	TipoDeProductos updatedtipoDeProductos = tipoDeProductoRepository.save(tipoDeProductos);

	    	return updatedtipoDeProductos;
	    }

	    // Delete a tipoDeProducto
	    @DeleteMapping("/tipodeproductos/{id}")
	    public ResponseEntity<?> deleteTipoDeProductos(@PathVariable(value = "id") Long tipoDeProductosId) throws TipoDeProductosNotFoundException {
	    	TipoDeProductos tipoDeProductos = tipoDeProductoRepository.findById(tipoDeProductosId).orElseThrow(() -> new TipoDeProductosNotFoundException(tipoDeProductosId));

	    	tipoDeProductoRepository.delete(tipoDeProductos);

	        return ResponseEntity.ok().build();
	    }
}
