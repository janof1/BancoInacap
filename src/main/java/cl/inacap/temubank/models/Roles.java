package cl.inacap.temubank.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="roles")
public class Roles {
	@Id
	private Long id;
	@NotBlank
	private String descripcion;
	public Roles() {
		super();
	}
	public Roles(Long id, @NotBlank String descripcion) {
		super();
		this.id = id;
		this.descripcion = descripcion;
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
	
	
	
}
