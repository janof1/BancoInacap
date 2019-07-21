package cl.inacap.temubank.service;

import java.util.List;

import cl.inacap.temubank.exception.ClienteNotFoundException;
import cl.inacap.temubank.models.Cliente;


public interface BancoService {

	public abstract Cliente addClient(Cliente cliente);
	
	public abstract List<Cliente> getAllClients();
	
	public abstract Boolean dleteClientById(Long id) throws ClienteNotFoundException; 
}
