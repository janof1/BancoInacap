package cl.inacap.temubank.exception;

public class TipoDeProductosNotFoundException extends Exception{
	private long tipoDeProductos_id;

	public TipoDeProductosNotFoundException(long tipoDeProductos_id) {
	   super(String.format("TipoDeProductos no encontrado por el id : '%s'", tipoDeProductos_id));
	}
}
