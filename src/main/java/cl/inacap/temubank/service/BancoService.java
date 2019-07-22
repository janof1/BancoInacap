package cl.inacap.temubank.service;

import java.util.List;

import cl.inacap.temubank.exception.ClienteNotFoundException;
import cl.inacap.temubank.models.Cliente;


public interface BancoService {

	public abstract Cliente addClient(Cliente cliente);
	
	public abstract List<Cliente> getAllClients();
	
	public abstract Boolean deleteClientById(Long id) throws ClienteNotFoundException; 
	
	public abstract Cliente editClient(Cliente cliente) throws ClienteNotFoundException; 
	
	public abstract Cliente showClientById(Long id) throws ClienteNotFoundException; 
	
	public abstract Cliente getClientById(Long id) throws ClienteNotFoundException; 
	
	public abstract Cliente getClientByRut(String rut) throws ClienteNotFoundException; 
	
	
}
