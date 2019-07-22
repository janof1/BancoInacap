package cl.inacap.temubank.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.inacap.temubank.exception.ClienteNotFoundException;
import cl.inacap.temubank.models.Cliente;
import cl.inacap.temubank.models.Cuenta;
import cl.inacap.temubank.models.Productos;
import cl.inacap.temubank.repository.ClienteRepository;
import cl.inacap.temubank.service.BancoService;

@Service	
public class BancoServiceImpl implements BancoService{
	
	@Autowired
	ClienteRepository clienteRepository;

	@Override
	public Cliente addClient(Cliente cliente) {

		Cuenta forceCuenta = cliente.getCuenta();

		for (Productos producto : cliente.getCuenta().getProductos()) {
			producto.setCuenta(forceCuenta);
			producto.setSaldoBase((long) 0);	
		}

		Cliente clienteAdded = clienteRepository.save(cliente);

		return clienteAdded;
	}

	@Override
	public List<Cliente> getAllClients() {
		
		return (List<Cliente>) clienteRepository.findAll();
	}

	@Override
	public Boolean deleteClientById(Long id)  throws ClienteNotFoundException{
		
		Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new ClienteNotFoundException(id));
		
		clienteRepository.delete(cliente);
		
		return true;
	}

	@Override
	public Cliente editClient(Cliente clienteUpdated) throws ClienteNotFoundException {
		Cliente  cliente = clienteRepository.findById(clienteUpdated.getId()).orElseThrow(() -> new ClienteNotFoundException(clienteUpdated.getId()));
		cliente.setRut(clienteUpdated.getRut());
		cliente.setNombre(clienteUpdated.getNombre());
		cliente.setApellidoPaterno(clienteUpdated.getApellidoPaterno());
		cliente.setEmail(clienteUpdated.getEmail());
		cliente.setCiudad(clienteUpdated.getCiudad());
		
		for (Productos producto : cliente.getCuenta().getProductos()) {
			for (Productos productoUpdate : clienteUpdated.getCuenta().getProductos()) {
				if(producto.getId() == productoUpdate.getId()) {
					producto.setSaldo(productoUpdate.getSaldo());
					producto.setSaldoBase(productoUpdate.getSaldo() - productoUpdate.getSaldoBase());
				}
			}
		}
		
		
		if(null != cliente) {
			clienteRepository.save(cliente);
		}
			

		return cliente;
	}

	@Override
	public Cliente showClientById(Long id) throws ClienteNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cliente getClientById(Long id) throws ClienteNotFoundException {
		Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new ClienteNotFoundException(id));

		return cliente;
	}

	@Override
	public Cliente getClientByRut(String rut) throws ClienteNotFoundException {
		Cliente cliente = clienteRepository.findByRut(rut);
		return cliente;
	}

}
