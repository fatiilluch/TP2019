package models;

import domain.Guardarropa;
import db.EntityManagerHelper;
import java.util.List;

public class GuardarropaModel extends Model{
	private static GuardarropaModel instance;
	
	public static GuardarropaModel getInstance() {
		if(instance == null) {
			instance = new GuardarropaModel();
		}
		
		return instance;
	}

	@Override
	public List<Guardarropa> buscarTodos() {
		return EntityManagerHelper.getEntityManager().createQuery("from Guardarropa").getResultList();
	}

	@Override
	public Guardarropa buscar(int id) {
		return EntityManagerHelper.getEntityManager().find(Guardarropa.class, id);
	}
	
	private List<Guardarropa> buscarGuardarropaPorUsuario(int idUsuario){
		String idUser = String.valueOf(idUsuario);
		return EntityManagerHelper.getEntityManager().createQuery("from Guardarropa where idUsuario= "+idUser).getResultList();
	}
	
	@Override
	public List<Guardarropa> busquedaCondicional(int id) {
	   return this.buscarGuardarropaPorUsuario(id);
	}

}
