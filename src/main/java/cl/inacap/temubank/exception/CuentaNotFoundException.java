package cl.inacap.temubank.exception;

public class CuentaNotFoundException extends Exception{
	private long cuenta_id;

	public CuentaNotFoundException(long cuenta_id) {
	   super(String.format("Cuenta no encontrado por el id : '%s'", cuenta_id));
	}
}
