package cl.inacap.temubank.exception;

public class TransaccionesNotFoundException extends Exception{
	private long transacciones_id;

	public TransaccionesNotFoundException(long transacciones_id) {
	   super(String.format("TipoDeProductos no encontrado por el id : '%s'", transacciones_id));
	}
}
	