package cl.inacap.temubank.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import cl.inacap.temubank.constant.ViewConstant;
import cl.inacap.temubank.models.Cliente;
import cl.inacap.temubank.models.Cuenta;
import cl.inacap.temubank.models.Productos;
import cl.inacap.temubank.models.UserCredential;
import cl.inacap.temubank.repository.ClienteRepository;
import cl.inacap.temubank.repository.RolClienteRepository;
import cl.inacap.temubank.repository.TipoDeProductoRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {
	
	
	public static final String FORM_VIEW = "form";
	public static final String FORM_RESULT = "resultAddClient";
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	RolClienteRepository rolClienteRepository;
	
	@Autowired
	TipoDeProductoRepository tipoDeProductoRepository;
	
	@GetMapping("/")
	public RedirectView redirecToLogin() {
		return new RedirectView("/login");
	}
	
	@GetMapping("/login")
	public ModelAndView exampleMAV(@RequestParam(name="error", required = false)String error,
			@RequestParam(name="logout", required = false) String loguot) {
		ModelAndView mav = new ModelAndView(ViewConstant.LOGIN_VIEW);
		mav.addObject("user", new UserCredential());
		mav.addObject("error", error);
		mav.addObject("logout", loguot);
		
	
		return mav;
	}
	
	@PostMapping("/loginCheck")
    public RedirectView createTransacciones(@ModelAttribute("user") UserCredential user) {
		Cliente cliente = clienteRepository.findByRut(user.getRut());
		String path = "/login?error";
		if(null != cliente && !cliente.getRoles().isEmpty()) {
			if(cliente.getRoles().get(0).getDescripcion().equals("banco")) {
				path ="/banco";
			}else {
				path ="/cliente/" + cliente.getId();
			}
		}
		
		return new RedirectView(path);
     	
    }
	
	
//	@GetMapping("/loginE")
//	public String exampleLogin(Model model) {
//		Cliente clienteLogin = new Cliente();
//		clienteLogin.setRut("9.551.125-k");
//		Cliente cliente = clienteRepository.findByRut(clienteLogin.getRut());
//		model.addAttribute("clientes", getClientes());
//		return LOGIN_VIEW;
//	}
	
//	@GetMapping("/loginMAV")
//	public ModelAndView exampleMAV() {
//		Cliente clienteLogin = new Cliente();
//		clienteLogin.setRut("9.551.175-k");
//		Cliente cliente = clienteRepository.findByRut(clienteLogin.getRut());
//		
//		ModelAndView mav = new ModelAndView(LOGIN_VIEW);
//		mav.addObject("cliente", cliente);
//		mav.addObject("clientes", getClientes());
//		
//		return mav;
//	}
	
	private List<Cliente> getClientes(){
		List<Cliente> clientes = (List<Cliente>) clienteRepository.findAll();
		return clientes;
	}
	
//	@GetMapping("/request2/{nm}")
//	public ModelAndView loginHttpGet(@PathVariable("nm") String name) {
//		ModelAndView mav = new ModelAndView(LOGIN_VIEW);
//		mav.addObject("nm_login", name);
//		return mav;
//	}
//	
	@GetMapping("/showForm")
	public String showForm(Model model) {
		model.addAttribute("cliente", new Cliente());
		return FORM_VIEW;
	}
	
	@GetMapping("/showFormmav")
	public ModelAndView showForm() {
		ModelAndView mav = new ModelAndView(FORM_VIEW);
		
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
	
	@PostMapping("/addClient")
	public ModelAndView AddClient( @ModelAttribute("cliente") Cliente cliente) {
		
		Cuenta forceCuenta = cliente.getCuenta();
		
		
		for (Productos producto : cliente.getCuenta().getProductos()) {
		   producto.setCuenta(forceCuenta);
		}
		
		
		Cliente clienteAdded = clienteRepository.save(cliente);
		
		ModelAndView mav = new ModelAndView(FORM_RESULT);
		mav.addObject("cliente", clienteAdded);
		return mav;
	}
	

}
