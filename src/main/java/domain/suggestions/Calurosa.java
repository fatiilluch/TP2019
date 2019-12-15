package domain.suggestions;

import domain.Sensibilidad;
import domain.Categoria;

public class Calurosa implements Critica{
	
	public void calificar(Categoria categ ,  Sensibilidad sensibilidad) {
		sensibilidad.disminuirSensibilidad(categ, 0.1);
	}
}


