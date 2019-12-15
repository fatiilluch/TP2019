package exception;

public class TipoDePrendaSinCombinableSegunNivelCapa extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String descripcion() {
		return "El tipo de prenda no tiene una tipo de prenda conbinable con es nivelde capa indicado";
	}

}
