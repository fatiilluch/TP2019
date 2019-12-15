package domain.suggestions;

import domain.Operacion;

public class AceptarSugerencia extends Operacion
{

	@Override
	public void ejecutar() 
	{
		this.getGuardarropa().agregarAListaAceptado(getAtuendo());
		this.getAtuendo().aceptarAtuendo();
		
	}

	@Override
	public void deshacer() 
	{
		this.getGuardarropa().elimAtuendorAceptado(this.getAtuendo());
	}

	
}