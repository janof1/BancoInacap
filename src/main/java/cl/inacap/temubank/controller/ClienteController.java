package cl.inacap.temubank.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import cl.inacap.temubank.constant.ViewConstant;
import cl.inacap.temubank.exception.ClienteNotFoundException;
import cl.inacap.temubank.models.Cliente;
import cl.inacap.temubank.models.Transacciones;
import cl.inacap.temubank.models.Transferencia;
import cl.inacap.temubank.repository.ClienteRepository;
import cl.inacap.temubank.service.ClienteService;

@RestController
public class ClienteController {
	// Get All clientes
	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	ClienteService clienteService;
	
	@GetMapping("/cliente/{id}")
	public ModelAndView getPanelCliente(@PathVariable(value = "id") Long cuentaId) throws ClienteNotFoundException {
		
		ModelAndView mav = new ModelAndView(ViewConstant.CLIENTE_VIEW);
		Cliente ShowCliente = clienteService.getClientById(cuentaId);
	
		mav.addObject("cliente", ShowCliente);
		mav.addObject("totalContable", clienteService.totalcontable(ShowCliente));
		
	
		return mav;
	}
	
	@GetMapping("/cliente/transferencia/{id}")
	public ModelAndView transferencia(@PathVariable(value = "id") Long cuentaId) throws ClienteNotFoundException {
		
		ModelAndView mav = new ModelAndView(ViewConstant.CLIENTE_TRANS_VIEW);
		Cliente ShowCliente = clienteService.getClientById(cuentaId);
		Transferencia tranf = new Transferencia();
		tranf.setCuentaOrigen(ShowCliente);
	
		mav.addObject("cliente", ShowCliente);
		mav.addObject("totalContable", clienteService.totalcontable(ShowCliente));
		mav.addObject("cuentas", clienteService.getAllClients(ShowCliente));
		mav.addObject("transferencia", tranf);
		
	
		return mav;
	}
	
	@PostMapping("/cliente/trans")
	public RedirectView tranferClient( @ModelAttribute("transferencia")  Transferencia transferencia) throws ClienteNotFoundException {
		
		Cliente clienteOrigen = transferencia.getCuentaOrigen();
		Long clienteDestino = transferencia.getCuentaDestino();
		
		Boolean succes = clienteService.tranferir(transferencia);
		
		if(!succes) {
			return new RedirectView("/cliente/transferencia/" + transferencia.getCuentaOrigen().getId() + "?fail");
		}
		
		return new RedirectView("/cliente/transferencia/" + transferencia.getCuentaOrigen().getId() + "?success");
	}
	
	@GetMapping("/cliente/pagos/{id}")
	public ModelAndView pagos(@PathVariable(value = "id") Long cuentaId) throws ClienteNotFoundException {
		
		ModelAndView mav = new ModelAndView(ViewConstant.CLIENTE_CARDS_VIEW);
		Cliente ShowCliente = clienteService.getClientById(cuentaId);
		Transferencia tranf = new Transferencia();
		tranf.setCuentaOrigen(ShowCliente);
	
		mav.addObject("cliente", ShowCliente);
		mav.addObject("totalContable", clienteService.totalcontable(ShowCliente));
		mav.addObject("cuentas", clienteService.getAllClients(ShowCliente));
		mav.addObject("transferencia", tranf);
		
	
		return mav;
	}
	
	@PostMapping("/cliente/pagoLineaDecredito")
	public RedirectView pagoLineaCreditoClient( @ModelAttribute("transferencia")  Transferencia transferencia) throws ClienteNotFoundException {
		
		Cliente clienteOrigen = transferencia.getCuentaOrigen();
		Long clienteDestino = transferencia.getCuentaDestino();
		
		Boolean succes = clienteService.tranferir(transferencia);
		
		if(!succes) {
			return new RedirectView("/cliente/transferencia/" + transferencia.getCuentaOrigen().getId() + "?fail");
		}
		
		return new RedirectView("/cliente/transferencia/" + transferencia.getCuentaOrigen().getId() + "?success");
	}
	
	@PostMapping("/cliente/pagoTarjetaCcredito")
	public RedirectView pagoTarjetaCreditoClient( @ModelAttribute("transferencia")  Transferencia transferencia) throws ClienteNotFoundException {
		
		Cliente clienteOrigen = transferencia.getCuentaOrigen();
		Long clienteDestino = transferencia.getCuentaDestino();
		
		Boolean succes = clienteService.tranferir(transferencia);
		
		if(!succes) {
			return new RedirectView("/cliente/transferencia/" + transferencia.getCuentaOrigen().getId() + "?fail");
		}
		
		return new RedirectView("/cliente/transferencia/" + transferencia.getCuentaOrigen().getId() + "?success");
	}
	
	@PostMapping("/cliente/pagoCuentas")
	public RedirectView pagoCuentasClient( @ModelAttribute("transferencia")  Transferencia transferencia) throws ClienteNotFoundException {
		
		Cliente clienteOrigen = transferencia.getCuentaOrigen();
		Long clienteDestino = transferencia.getCuentaDestino();
		
		Boolean succes = clienteService.tranferir(transferencia);
		
		if(!succes) {
			return new RedirectView("/cliente/transferencia/" + transferencia.getCuentaOrigen().getId() + "?fail");
		}
		
		return new RedirectView("/cliente/transferencia/" + transferencia.getCuentaOrigen().getId() + "?success");
	}
	
	
	@GetMapping("/cliente/transacciones/{id}")
	public ModelAndView transacciones(@PathVariable(value = "id") Long cuentaId) throws ClienteNotFoundException {
		
		ModelAndView mav = new ModelAndView(ViewConstant.CLIENTE_PAY_VIEW);
		Cliente ShowCliente = clienteService.getClientById(cuentaId);
		List<Transacciones> transacciones = clienteService.getTransaccionesById(ShowCliente.getCuenta().getId());
	
	
		mav.addObject("cliente", ShowCliente);
		mav.addObject("transacciones", transacciones);
		mav.addObject("totalContable", clienteService.totalcontable(ShowCliente));
		
		
	
		return mav;
	}
	
	
//	@GetMapping("/clientes")
//	public List<Cliente> getAllClientes(){
//		return (List<Cliente>) clienteRepository.findAll();
//	}
//	// Create a new Cliente
//	@PostMapping("/clientes")
//    public Cliente createCliente(@Valid @RequestBody Cliente cliente) {
//        return clienteRepository.save(cliente);
//    }
//	// Get a Single Cliente
//    @GetMapping("/clientes/{id}")
//    public Cliente getClienteById(@PathVariable(value = "id") Long clienteId) throws ClienteNotFoundException {
//        return clienteRepository.findById(clienteId).orElseThrow(() -> new ClienteNotFoundException(clienteId));
//    }
//
//    // Update a Cliente
//    @PutMapping("/clientes/{id}")
//    public Cliente updateCliente(@PathVariable(value = "id") Long clienteId, @Valid @RequestBody Cliente clienteUpdate) throws ClienteNotFoundException {
//    	Cliente cliente = clienteRepository.findById(clienteId).orElseThrow(() -> new ClienteNotFoundException(clienteId));
//    	cliente.setRut(clienteUpdate.getRut());
//    	cliente.setNombre(clienteUpdate.getNombre());
//    	cliente.setApellidoPaterno(clienteUpdate.getApellidoPaterno());
//    	cliente.setCiudad(clienteUpdate.getCiudad());
//    	cliente.setEmail(clienteUpdate.getEmail());
//    	cliente.setRoles(clienteUpdate.getRoles());
//    	
//        
//    	Cliente updatedCliente = clienteRepository.save(cliente);
//
//    	return updatedCliente;
//    }
//
//    // Delete a Cliente
//    @DeleteMapping("/clientes/{id}")
//    public ResponseEntity<?> deleteCliente(@PathVariable(value = "id") Long clienteId) throws ClienteNotFoundException {
//    	Cliente cliente = clienteRepository.findById(clienteId).orElseThrow(() -> new ClienteNotFoundException(clienteId));
//
//        clienteRepository.delete(cliente);
//
//        return ResponseEntity.ok().build();
//    }
//    
   
}


