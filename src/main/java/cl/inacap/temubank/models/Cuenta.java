package cl.inacap.temubank.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
@Entity
@Table(name = "cuenta")
public class Cuenta {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String tipo_cuenta;
	
	@JsonIgnore
	@OneToOne( fetch=FetchType.LAZY, mappedBy = "cuenta")
	private Cliente cliente; 

	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cuenta", cascade = CascadeType.ALL)
	@Column(nullable = true)
    @JsonManagedReference
	private List<Productos> productos;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cuentaOrigen", cascade = CascadeType.ALL)
	@Column(nullable = true)
    @JsonManagedReference
    private List<Transacciones> transaccionOrigen;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cuentaDestino", cascade = CascadeType.ALL)
	@Column(nullable = true)
    @JsonManagedReference
    private List<Transacciones> transaccionDestino;
	
	public Cuenta() {
		super();
	}

	public Cuenta(Long id, @NotBlank String tipo_cuenta, Cliente cliente, List<Productos> productos,
			List<Transacciones> transaccionOrigen, List<Transacciones> transaccionDestino) {
		super();
		this.id = id;
		this.tipo_cuenta = tipo_cuenta;
		this.cliente = cliente;
		this.productos = productos;
		this.transaccionOrigen = transaccionOrigen;
		this.transaccionDestino = transaccionDestino;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipo_cuenta() {
		return tipo_cuenta;
	}

	public void setTipo_cuenta(String tipo_cuenta) {
		this.tipo_cuenta = tipo_cuenta;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Productos> getProductos() {
		return productos;
	}

	public void setProductos(List<Productos> productos) {
		this.productos = productos;
	}

	public List<Transacciones> getTransaccionOrigen() {
		return transaccionOrigen;
	}

	public void setTransaccionOrigen(List<Transacciones> transaccionOrigen) {
		this.transaccionOrigen = transaccionOrigen;
	}

	public List<Transacciones> getTransaccionDestino() {
		return transaccionDestino;
	}

	public void setTransaccionDestino(List<Transacciones> transaccionDestino) {
		this.transaccionDestino = transaccionDestino;
	}

	
}
