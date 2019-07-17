package cl.inacap.temubank.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
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
	@OneToOne( fetch=FetchType.LAZY, mappedBy = "tipodeproducto")
    private Productos productos;

	public TipoDeProductos() {
		super();
	}

	public TipoDeProductos(Long id, String descripcion, Productos productos) {
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

	public Productos getProductos() {
		return productos;
	}

	public void setProductos(Productos productos) {
		this.productos = productos;
	}

	
}
