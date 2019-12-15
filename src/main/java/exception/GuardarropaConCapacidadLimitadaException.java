package exception;

public class GuardarropaConCapacidadLimitadaException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String descripcion() {
		return "El guardarropa tiene una capacidad limitada";
	}

}
