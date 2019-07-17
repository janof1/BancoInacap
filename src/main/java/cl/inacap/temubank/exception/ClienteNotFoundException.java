package cl.inacap.temubank.exception;

public class ClienteNotFoundException extends Exception{
	private long cliente_id;

	public ClienteNotFoundException(long cliente_id) {
	   super(String.format("Cliente no encontrado por el id : '%s'", cliente_id));
	}

}
