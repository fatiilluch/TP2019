package repositories;

import java.util.List;
import domain.Prenda;
import repositories.daos.DAO;

public class RepositorioPrenda extends Repositorio{
	private static RepositorioPrenda instance;
	
	public static RepositorioPrenda getInstance(DAO dao) {
		if(instance == null) {
			instance = new RepositorioPrenda(dao);
		}
		
		return instance;
	}
	
	private RepositorioPrenda(DAO dao){
        this.setDao(dao);
    }

    public List<Prenda> buscarTodos(){
        return this.dao.buscarTodos();
    }

    public Prenda buscar(int id){
        return this.dao.buscar(id);
    }

    public List<Prenda> buscarPrendaPorGuardarropa(int idGuardarropa){
        return this.dao.busquedaCondicional(idGuardarropa);
    }

    public int buscarGuardarropaPorPrenda(int idPrenda){
	    return this.dao.busquedaGuardarropaPorPrenda(idPrenda);
    }
}
