package repositories;

import java.util.List;
import domain.Guardarropa;
import repositories.daos.DAO;

public class RepositorioGuardarropa extends Repositorio{
	private static RepositorioGuardarropa instance;

    public static RepositorioGuardarropa getInstance(DAO dao) {
        if(instance == null){
            instance = new RepositorioGuardarropa(dao);
        }
        return instance;
    }

    private RepositorioGuardarropa(DAO dao){
        this.setDao(dao);
    }

    public List<Guardarropa> buscarTodos(){
        return this.dao.buscarTodos();
    }

    public Guardarropa buscar(int id){
        return this.dao.buscar(id);
    }
    
    public List<Guardarropa> buscarGuardarropaPorUsuario(int idUsuario){
        return this.dao.busquedaCondicional(idUsuario);
    }
	
}
