package cl.inacap.temubank.service;

import java.util.List;

import cl.inacap.temubank.exception.ClienteNotFoundException;
import cl.inacap.temubank.models.Cliente;
import cl.inacap.temubank.models.Transacciones;
import cl.inacap.temubank.models.Transferencia;

public interface ClienteService {
	
	public abstract List<Transacciones> getTransaccionesById(Long id); 
	public abstract List<Transacciones> getTransaccionesByIdDestino(Long id);
	
	public abstract List<Cliente> getAllClients(Cliente cliente); 
	
	public abstract Cliente getClientById(Long id) throws ClienteNotFoundException; 
	public abstract Long totalcontable(Cliente cliente);
	
	public abstract Boolean tranferir(Transferencia transferencia) throws ClienteNotFoundException;
	
	
	
	

}
