package domain.suggestions;

import domain.Sensibilidad;
import domain.Categoria;
public interface Critica {
	
	public void calificar(Categoria categ,Sensibilidad sensibilidad);
}
