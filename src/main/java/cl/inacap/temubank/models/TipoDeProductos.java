package cl.inacap.temubank.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tipo_de_productos")
public class TipoDeProductos {
	@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String descripcion;
	
	@JsonIgnore
	@OneToMany( fetch=FetchType.LAZY, mappedBy = "tipodeproducto")
	@Column(nullable = true)
	private List<Productos> productos;

	public TipoDeProductos() {
		super();
	}

	public TipoDeProductos(Long id, String descripcion, List<Productos> productos) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.productos = productos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Productos> getProductos() {
		return productos;
	}

	public void setProductos(List<Productos> productos) {
		this.productos = productos;
	}

	
}
