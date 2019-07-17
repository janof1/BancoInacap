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

import cl.inacap.temubank.exception.ProductosNotFoundException;
import cl.inacap.temubank.models.Productos;
import cl.inacap.temubank.repository.ProductosRepository;

@RestController
public class ProductosController {
	
	@Autowired
	ProductosRepository productosRepository;
	
	@GetMapping("/productos")
	public List<Productos>getAllProductos(){
		return ( List<Productos>)productosRepository.findAll();
	}
	// Create a new productos
	@PostMapping("/productos")
    public Productos createNote(@Valid @RequestBody Productos productos) {
        return productosRepository.save(productos);
    }
	
	// Get a Single productos
    @GetMapping("/productos/{id}")
    public Productos getProductoById(@PathVariable(value = "id") Long productoId) throws ProductosNotFoundException {
        return productosRepository.findById(productoId).orElseThrow(() -> new ProductosNotFoundException(productoId));
    }
    
    // Update a productos
    @PutMapping("/productos/{id}")
    public Productos updateProducto(@PathVariable(value = "id") Long productoId, @Valid @RequestBody Productos productoUpdate) throws ProductosNotFoundException {
    	Productos producto = productosRepository.findById(productoId).orElseThrow(() -> new ProductosNotFoundException(productoId));
    	producto.setCuenta(productoUpdate.getCuenta());
    	producto.setSaldo(productoUpdate.getSaldo());
    	producto.setTipodeproducto(productoUpdate.getTipodeproducto());
        
    	Productos updatedProducto = productosRepository.save(producto);

    	return updatedProducto;
    }
    
    // Delete a productos
    @DeleteMapping("/productos/{id}")
    public ResponseEntity<?> deleteCliente(@PathVariable(value = "id") Long productoId) throws ProductosNotFoundException {
    	Productos producto = productosRepository.findById(productoId).orElseThrow(() -> new ProductosNotFoundException(productoId));

    	productosRepository.delete(producto);

        return ResponseEntity.ok().build();
    }

}
