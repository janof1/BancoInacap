package cl.inacap.temubank.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.inacap.temubank.exception.ClienteNotFoundException;
import cl.inacap.temubank.models.Cliente;
import cl.inacap.temubank.repository.ClienteRepository;
import cl.inacap.temubank.service.BancoService;

@Service	
public class BancoServiceImpl implements BancoService{
	
	@Autowired
	ClienteRepository clienteRepository;

	@Override
	public Cliente addClient(Cliente cliente) {
		
		return null;
	}

	@Override
	public List<Cliente> getAllClients() {
		
		return (List<Cliente>) clienteRepository.findAll();
	}

	@Override
	public Boolean dleteClientById(Long id)  throws ClienteNotFoundException{
		
		Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new ClienteNotFoundException(id));
		
		clienteRepository.delete(cliente);
		
		return true;
	}

}
