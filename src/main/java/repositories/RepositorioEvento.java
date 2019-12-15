package repositories;

import domain.Guardarropa;
import domain.events.Evento;
import repositories.daos.DAO;
import java.util.List;

public class RepositorioEvento extends Repositorio {
    private static repositories.RepositorioEvento instance;

    public static repositories.RepositorioEvento getInstance(DAO dao) {
        if(instance == null){
            instance = new repositories.RepositorioEvento(dao);
        }
        return instance;
    }

    private RepositorioEvento(DAO dao){
        this.setDao(dao);
    }

    public List<Evento> buscarTodos(){
        return this.dao.buscarTodos();
    }

    public Evento buscar(int id){
        return this.dao.buscar(id);
    }
    
    public List<Evento> buscarEventoPorUsuario(int idUsuario){
        return this.dao.busquedaCondicional(idUsuario);
    }
    
}
