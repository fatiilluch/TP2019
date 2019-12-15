package repositories;

import domain.suggestions.Sugerencia;
import repositories.daos.DAO;
import java.util.List;

public class RepositorioSugerencia extends Repositorio {
    private static RepositorioSugerencia instance;

    public static RepositorioSugerencia getInstance(DAO dao) {
        if(instance == null){
            instance = new RepositorioSugerencia(dao);
        }
        return instance;
    }

    private RepositorioSugerencia(DAO dao){
        this.setDao(dao);
    }

    public List<Sugerencia> buscarTodos(){
        return this.dao.buscarTodos();
    }

    public Sugerencia buscar(int id){
        return this.dao.buscar(id);
    }

    public List<Sugerencia> buscarSugerenciasEvento(int idEvento){ return this.dao.busquedaCondicional(idEvento);}

    //public List<Sugerencia> buscarSugerenciaPorUsuario(int idUsuario) {return this.dao.busquedaCondicional(idUsuario);}
}
