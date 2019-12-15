package domain;

public class GestorDeAcciones
{
	private Operacion ultimaAccion;
	
	public void ejecutar(Operacion accion) 
	{
		accion.ejecutar();
		this.setUltimaAccion(accion);
	}

	public void deshacer() 
	{
		this.getUltimaAccion().deshacer();
	}

	public Operacion getUltimaAccion() 
	{
		return ultimaAccion;
	}

	public void setUltimaAccion(Operacion ultimaAccion) 
	{
		this.ultimaAccion = ultimaAccion;
	}
}