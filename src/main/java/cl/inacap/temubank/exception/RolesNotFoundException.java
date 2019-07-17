package cl.inacap.temubank.exception;

public class RolesNotFoundException extends Exception{
	private long roles_id;

	public RolesNotFoundException(long roles_id) {
	   super(String.format("Role no encontrado por el id : '%s'", roles_id));
	}

}
