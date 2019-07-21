package cl.inacap.temubank.models;

public class UserCredential {
	
	private String rut;

	public UserCredential() {
		super();
	}

	public UserCredential(String rut) {
		super();
		this.rut = rut;
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

}
