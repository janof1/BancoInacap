package cl.inacap.temubank.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "productos")
public class Productos {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private Long saldo;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_cuenta")
	@JsonBackReference
	private Cuenta cuenta;
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_de_producto")
	private TipoDeProductos tipodeproducto;
	
	public Productos() {
		super();
	}


	public Productos(Long id, Long saldo, Cuenta cuenta, TipoDeProductos tipodeproducto) {
		super();
		this.id = id;
		this.saldo = saldo;
		this.cuenta = cuenta;
		this.tipodeproducto = tipodeproducto;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Long getSaldo() {
		return saldo;
	}


	public void setSaldo(Long saldo) {
		this.saldo = saldo;
	}


	public Cuenta getCuenta() {
		return cuenta;
	}


	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}


	public TipoDeProductos getTipodeproducto() {
		return tipodeproducto;
	}


	public void setTipodeproducto(TipoDeProductos tipodeproducto) {
		this.tipodeproducto = tipodeproducto;
	}

	
}
