package cl.inacap.temubank.exception;

public class ProductosNotFoundException extends Exception{
	private long productos_id;

	public ProductosNotFoundException(long productos_id) {
	   super(String.format(" Producto no encontrado por el id : '%s'", productos_id));
	}

}
