package cl.inacap.temubank.models;

import java.util.Date;

public class Transferencia {
	
	private Cliente cuentaOrigen;
	private Long cuentaDestino;
	private Long monto;
	private Date fecha;
	private String operacion;
	
	public Transferencia() {
		super();
	}

	public Transferencia(Cliente cuentaOrigen, Long cuentaDestino, Long monto, Date fecha, String operacion) {
		super();
		this.cuentaOrigen = cuentaOrigen;
		this.cuentaDestino = cuentaDestino;
		this.monto = monto;
		this.fecha = fecha;
		this.operacion = operacion;
	}

	public Cliente getCuentaOrigen() {
		return cuentaOrigen;
	}

	public void setCuentaOrigen(Cliente cuentaOrigen) {
		this.cuentaOrigen = cuentaOrigen;
	}

	public Long getCuentaDestino() {
		return cuentaDestino;
	}

	public void setCuentaDestino(Long cuentaDestino) {
		this.cuentaDestino = cuentaDestino;
	}

	public Long getMonto() {
		return monto;
	}

	public void setMonto(Long monto) {
		this.monto = monto;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getOperacion() {
		return operacion;
	}

	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}

	
}
