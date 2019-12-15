package domain.suggestions;

import domain.Operacion;

public class RechazarSugerencia extends Operacion
{
	
	
	@Override
	public void ejecutar() 
	{
		this.getGuardarropa().agregarAListaRechazado(this.getAtuendo());
		this.getAtuendo().rechazarAtuendo();
	}

	@Override
	public void deshacer() 
	{
		this.getGuardarropa().elimAtuendoRechazado(this.getAtuendo());
	}


}