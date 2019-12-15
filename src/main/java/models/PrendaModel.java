package models;

import domain.Prenda;
import domain.Guardarropa;
import db.EntityManagerHelper;
import java.util.List;
import domain.Color;
import repositories.RepositorioGuardarropa;
import repositories.factories.FactoryRepositorioGuardarropa;

public class PrendaModel extends Model{
	private static PrendaModel instance;
	private int valorDeGuardarropa;

	public static PrendaModel getInstance() {
		if(instance == null) {
			instance = new PrendaModel();
		}
		
		return instance;
	}

	@Override
	public List<Prenda> buscarTodos() {
		List<Prenda> prendas =  EntityManagerHelper.getEntityManager().createQuery("from Prenda").getResultList();
		return prendas;
	}

	@Override
	public Prenda buscar(int id) {
		return EntityManagerHelper.getEntityManager().find(Prenda.class, id);
	}

	private List<Prenda> buscarPrendaPorGuardarropa(int idGuardarropa){
		String idGuardarropas = String.valueOf(idGuardarropa);
		return EntityManagerHelper.getEntityManager().createQuery("from Prenda where idGuardarropa= "+idGuardarropas).getResultList();
	}

	@Override
	public Integer buscarGuardarropaPorPrenda(int idPrenda){
		RepositorioGuardarropa repositorioGuardarropa = FactoryRepositorioGuardarropa.get();
		List<Guardarropa> todosLosGuardarropas = repositorioGuardarropa.buscarTodos();
		todosLosGuardarropas.stream().forEach(guardarropa -> devolverIntSiTienePrenda(guardarropa,idPrenda));
		return valorDeGuardarropa;
	}

	public void devolverIntSiTienePrenda(Guardarropa guardarropa, int idPrenda){
		if(guardarropa.tienePrenda(idPrenda)){
			valorDeGuardarropa = guardarropa.getId();
		}
	}

	@Override
	public List<Prenda> busquedaCondicional(int id) {
		return this.buscarPrendaPorGuardarropa(id);
	}
}
