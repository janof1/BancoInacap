package cl.inacap.temubank.models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="transacciones")
public class Transacciones {
	@Id
	private Long id;
	
	private Date fecha;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "n_cuenta_origen")
	@JsonBackReference
	private Cuenta cuentaOrigen;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "n_cuenta_destino")
	@JsonBackReference
	private Cuenta cuentaDestino;
	
	private Long monto;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_producto")
	@JsonBackReference
	private Productos producto;
	
	
	private String tipo_operacion;
	
	public Transacciones() {
		super();
	}

	public Transacciones(Long id, Date fecha, Cuenta cuentaOrigen, Cuenta cuentaDestino, Long monto, Productos producto,
			String tipo_operacion) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.cuentaOrigen = cuentaOrigen;
		this.cuentaDestino = cuentaDestino;
		this.monto = monto;
		this.producto = producto;
		this.tipo_operacion = tipo_operacion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Cuenta getCuentaOrigen() {
		return cuentaOrigen;
	}

	public void setCuentaOrigen(Cuenta cuentaOrigen) {
		this.cuentaOrigen = cuentaOrigen;
	}

	public Cuenta getCuentaDestino() {
		return cuentaDestino;
	}

	public void setCuentaDestino(Cuenta cuentaDestino) {
		this.cuentaDestino = cuentaDestino;
	}

	public Long getMonto() {
		return monto;
	}

	public void setMonto(Long monto) {
		this.monto = monto;
	}

	public Productos getProducto() {
		return producto;
	}

	public void setProducto(Productos producto) {
		this.producto = producto;
	}

	public String getTipo_operecion() {
		return tipo_operacion;
	}

	public void setTipo_operecion(String tipo_operacion) {
		this.tipo_operacion = tipo_operacion;
	}
	
}
