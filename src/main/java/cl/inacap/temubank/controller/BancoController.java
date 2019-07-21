package cl.inacap.temubank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import cl.inacap.temubank.constant.ViewConstant;
import cl.inacap.temubank.exception.ClienteNotFoundException;
import cl.inacap.temubank.models.Cliente;
import cl.inacap.temubank.service.BancoService;

@RestController
public class BancoController {
	
	@Autowired
	BancoService bancoService;
	
	@GetMapping("/banco")
	public ModelAndView getBanco() {
		
		ModelAndView mav = new ModelAndView(ViewConstant.BANCO_VIEW);
		 List<Cliente> clientes = bancoService.getAllClients();
		
		mav.addObject("clientes", clientes);
	
		return mav;
	}
	
	
	@GetMapping("/banco/cliente/crear")
	public ModelAndView crearCliente() {
		
		ModelAndView mav = new ModelAndView(ViewConstant.ADD_CLIENTE_VIEW);
	
		return mav;
	}
	
	@GetMapping("/banco/cliente/eliminar/{id}")
	public RedirectView eliminarCliente(@PathVariable(value = "id") Long clienteId) throws ClienteNotFoundException {
		
		bancoService.dleteClientById(clienteId);
		return new RedirectView("/banco");
	}

}
