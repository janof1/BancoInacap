package cl.inacap.temubank.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;



@Entity
@Table(name = "cliente")
public class Cliente {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String rut;
	
	private String nombre;
	
	private String apellidoPaterno;
	
	private String email;

	private String ciudad;
	
	
	@JoinTable(
	name="rol_cliente",
	joinColumns = @JoinColumn(
			name="Id_cliente",
			referencedColumnName="Id"
			),
	inverseJoinColumns = @JoinColumn(
			name="Id_rol", 
			referencedColumnName="Id")
	)
	@ManyToMany
	private List<Roles> roles;
	@OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "numero_cuenta", referencedColumnName = "id")
	private Cuenta cuenta;
	
	public Cliente() {
		super();
	}

	public Cliente(Long id, String rut, String nombre, String apellidoPaterno, String email, String ciudad,
			List<Roles> roles, Cuenta cuenta) {
		super();
		this.id = id;
		this.rut = rut;
		this.nombre = nombre;
		this.apellidoPaterno = apellidoPaterno;
		this.email = email;
		this.ciudad = ciudad;
		this.roles = roles;
		this.cuenta = cuenta;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public List<Roles> getRoles() {
		return roles;
	}

	public void setRoles(List<Roles> roles) {
		this.roles = roles;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

}
