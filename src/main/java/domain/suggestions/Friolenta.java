package domain.suggestions;

import domain.Sensibilidad;
import domain.Categoria;

public class Friolenta implements Critica{
	
	
	public void calificar(Categoria categ,Sensibilidad sensibilidad) {
		sensibilidad.aumentarSensibilidad(categ, 0.1);		
	}

}


