package cl.inacap.temubank.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import cl.inacap.temubank.constant.ViewConstant;
import cl.inacap.temubank.exception.ClienteNotFoundException;
import cl.inacap.temubank.models.Cliente;
import cl.inacap.temubank.models.Cuenta;
import cl.inacap.temubank.models.Productos;
import cl.inacap.temubank.repository.ClienteRepository;
import cl.inacap.temubank.repository.RolClienteRepository;
import cl.inacap.temubank.repository.TipoDeProductoRepository;
import cl.inacap.temubank.service.BancoService;

@RestController
public class BancoController {
	
	@Autowired
	BancoService bancoService;
	
	@Autowired
	RolClienteRepository rolClienteRepository;
	
	@Autowired
	TipoDeProductoRepository tipoDeProductoRepository;
	
	@GetMapping("/banco")
	public ModelAndView getBanco(@RequestParam(name="error", required = false)String error,
			@RequestParam(name="save", required = false) String save) {
		
		ModelAndView mav = new ModelAndView(ViewConstant.BANCO_VIEW);
		 List<Cliente> clientes = bancoService.getAllClients();
		
		mav.addObject("error", error);
		mav.addObject("success", save);
		mav.addObject("clientes", clientes);
	
		return mav;
	}
	
	
	@GetMapping("/banco/cliente/crear")
	public ModelAndView crearCliente() {
		
		ModelAndView mav = new ModelAndView(ViewConstant.ADD_CLIENTE_VIEW);
		
		Cliente clienteForm = new Cliente();
		Cuenta cuentaForm = new Cuenta();
		
		List<Productos> listaProductos = new ArrayList<Productos>();
		Productos productosForm = new Productos();
	
		
		for (int i = 1; i <= 3; i++) {
			productosForm.setCuenta(new Cuenta());
			listaProductos.add(productosForm);
	    }
		
		
		cuentaForm.setProductos(listaProductos);
		clienteForm.setCuenta(cuentaForm);
		mav.addObject("cliente", clienteForm);
		mav.addObject("roles", rolClienteRepository.findAll());
		mav.addObject("tipo_producto", tipoDeProductoRepository.findAll());
	
		return mav;
	}
	
	
	@PostMapping("/banco/cliente/addClient")
	public RedirectView addClient( @ModelAttribute("cliente") Cliente cliente) throws ClienteNotFoundException {
		
		Cliente existCliente = bancoService.getClientByRut(cliente.getRut());
		if(null != existCliente) {
			return new RedirectView("/banco?error");
		}
		bancoService.addClient(cliente);
		return new RedirectView("/banco?save");
	}
	
	
	@GetMapping("/banco/cliente/editar/{id}")
	public ModelAndView editarCliente(@PathVariable(value = "id") Long cuentaId) throws ClienteNotFoundException {
		
		ModelAndView mav = new ModelAndView(ViewConstant.EDIT_CLIENTE_VIEW);
		
		Cliente cliente = bancoService.getClientById(cuentaId);
		
	
		
		mav.addObject("cliente", cliente);
		mav.addObject("roles", cliente.getRoles());
		mav.addObject("tipo_producto", tipoDeProductoRepository.findAll());
	
		return mav;
	}
	
	
	@PostMapping("/banco/cliente/editClient")
	public RedirectView editClient( @ModelAttribute("cliente") Cliente cliente) throws ClienteNotFoundException {

		bancoService.editClient(cliente);
		return new RedirectView("/banco?edit");
	}
	
	
	@GetMapping("/banco/cliente/eliminar/{id}")
	public RedirectView eliminarCliente(@PathVariable(value = "id") Long clienteId) throws ClienteNotFoundException {
		
		bancoService.deleteClientById(clienteId);
		return new RedirectView("/banco");
	}

}
