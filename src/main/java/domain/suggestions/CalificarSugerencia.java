package domain.suggestions;

import domain.Operacion;


import java.util.List;
import domain.Prenda;
import domain.Sensibilidad;
import domain.Categoria;

public class CalificarSugerencia extends Operacion {
	
	private Sensibilidad sensibilidadUsuario;
	private Critica critica_Sup;
	private Critica critica_Inf;
	
	public CalificarSugerencia (Sensibilidad sensibilidadUsuario,Critica sup, Critica inf) {
		this.sensibilidadUsuario = sensibilidadUsuario;
		this.critica_Sup = sup;
		this.critica_Inf = inf;
	}
	
	@Override
	public void ejecutar() {
		this.evaluarPrendas(this.getAtuendo().getParteSuperior());
	}

	@Override
	public void deshacer() {
		// TODO Auto-generated method stub
		this.getGuardarropa().elimAtuendorAceptado(this.getAtuendo());
	}
	
	public void evaluarPrendas(List<Prenda> prendas) {
		switch (this.getCategoriaDe(prendas)) {
			case Superior:
				 this.critica_Sup.calificar(this.getCategoriaDe(prendas),this.sensibilidadUsuario);
				break;
			case Inferior:
				this.critica_Inf.calificar(this.getCategoriaDe(prendas),this.sensibilidadUsuario);
			default:
				break;
		}
	}
	private Categoria getCategoriaDe(List<Prenda> prendas) {
		return prendas.get(0).getCategoria();
	}
}
