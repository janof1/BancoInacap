package cl.inacap.temubank.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.inacap.temubank.exception.ClienteNotFoundException;
import cl.inacap.temubank.models.Cliente;
import cl.inacap.temubank.models.Productos;
import cl.inacap.temubank.models.Transacciones;
import cl.inacap.temubank.models.Transferencia;
import cl.inacap.temubank.repository.ClienteRepository;
import cl.inacap.temubank.repository.TransaccionesRepository;
import cl.inacap.temubank.service.ClienteService;

@Service
public class ClienteServiceImpl  implements ClienteService{

	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	TransaccionesRepository transaccionesRepository;
	
	@Override
	public List<Transacciones> getTransaccionesById(Long id) {
		List<Transacciones> transacciones = transaccionesRepository.findByCuentaOrigenId(id);
		return transacciones;
	}

	@Override
	public Cliente getClientById(Long id) throws ClienteNotFoundException {
		Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new ClienteNotFoundException(id));

		return cliente;
	}
	
	 public Long totalcontable(Cliente cliente) {
	    	
	    	Long total = (long) 0;
	    	
	    	for (Productos producto : cliente.getCuenta().getProductos()) {
				if(producto.getTipodeproducto().getId() == 2 ) {
					total = total + producto.getSaldo();
				}else if(producto.getTipodeproducto().getId()  == 3) {
					Long diferencia = producto.getSaldo() - producto.getSaldoBase();
					total = total + diferencia;
				}
			}
	    	return total;
	    }

	@Override
	public List<Cliente> getAllClients(Cliente cliente) {
		List<Cliente> clientes = (List<Cliente>) clienteRepository.findAll();
		clientes.remove(cliente);
		return clientes;
	}

	@Override
	public Boolean tranferir(Transferencia transferencia) throws ClienteNotFoundException {
		
		Long idClienteDestino = transferencia.getCuentaDestino();
		
		Cliente clienteDestino = clienteRepository.findById(idClienteDestino).orElseThrow(() -> new ClienteNotFoundException(idClienteDestino));
		Long totalTransferible = calcularTotalTransferible(transferencia.getCuentaOrigen());
		if(totalTransferible >=  transferencia.getMonto()) {
			Long idProducto = moverDinero(transferencia.getCuentaOrigen(), clienteDestino, transferencia.getMonto(), totalTransferible); 
			if(null != idProducto) {
				Transacciones newTransaccion = new Transacciones();
				newTransaccion.setFecha(new Date());
				newTransaccion.setTipo_operacion("Transferencia");
				newTransaccion.setCuentaOrigen(transferencia.getCuentaOrigen().getCuenta());
				newTransaccion.setCuentaDestino(clienteDestino.getCuenta());
				newTransaccion.setMonto(transferencia.getMonto());
				newTransaccion.setId_producto(idProducto);
				transaccionesRepository.save(newTransaccion);
			}
		}else {
			return false;
		}
		
			
		return true;
	}
	
	private Long moverDinero(Cliente origen, Cliente destino, Long montoTransferido, Long totalTransferible) {
		Long idProducto = (long) 0;
		
		totalTransferible = totalTransferible - montoTransferido;
		if(updateSaldoOrigen(origen, totalTransferible)) {
			idProducto = updateSaldoDestino(destino, montoTransferido);
		}
		
				
		return idProducto;
	}
	
	private Long updateSaldoDestino(Cliente cliente, Long monto) {
		Long idProducto = (long)0;
		for (Productos productoD : cliente.getCuenta().getProductos()) {
			if (productoD.getTipodeproducto().getId() == 2) {
				monto = monto + productoD.getSaldo();
				productoD.setSaldo(monto);
				idProducto = productoD.getId();
			}
		}
		
		Cliente updateCliente = clienteRepository.save(cliente);
		
		return idProducto;
	}
	
	private Boolean updateSaldoOrigen(Cliente cliente, Long monto) {
		
		for (Productos productoD : cliente.getCuenta().getProductos()) {
			if (productoD.getTipodeproducto().getId() == 3) {
				if (productoD.getSaldo() <= monto) {
					monto = monto - productoD.getSaldo();
					if (monto >= 0) {
						productoD.setSaldo(productoD.getSaldo());
					} else {
						productoD.setSaldo((long) 0);
						productoD.setSaldoBase(productoD.getSaldo());
					}
				}else if(productoD.getSaldo() >= monto){
					monto = productoD.getSaldo() - monto  ;
					productoD.setSaldoBase(monto);
				}
				if (monto >= 0) {
					for (Productos productoL : cliente.getCuenta().getProductos()) {
						if (productoL.getTipodeproducto().getId() == 2) {
							if (productoL.getSaldo() >= monto && monto > 0) {
								monto = productoL.getSaldo() - monto;
								if (monto >= 0) {
									productoL.setSaldo(monto);
									monto = (long) 0;
								} else {
									productoL.setSaldo((long) 0);
								}
							}else {
								productoL.setSaldo((long) 0);
							}
						}

					}
				}

			}
		}
		
		Cliente updateCliente = clienteRepository.save(cliente);
		
		return true;
	}
	
	
	private Long calcularTotalTransferible(Cliente cliente) {
		Long total = (long) 0;
		for (Productos producto : cliente.getCuenta().getProductos()) {
			if(producto.getTipodeproducto().getId() == 2 ) {
				total = total + producto.getSaldo();
			}else if(producto.getTipodeproducto().getId()  == 3) {
				Long diferencia = producto.getSaldo() - producto.getSaldoBase();
				total = total + diferencia;
			}
		}
		return total;
	}

}
