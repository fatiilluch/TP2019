package repositories;

import domain.Imagen;
import repositories.daos.DAO;
import java.util.List;

public class RepositorioImagen extends Repositorio {
    private static RepositorioImagen instance;

    public static RepositorioImagen getInstance(DAO dao) {
        if(instance == null){
            instance = new RepositorioImagen(dao);
        }
        return instance;
    }

    private RepositorioImagen(DAO dao){
        this.setDao(dao);
    }

    public List<Imagen> buscarTodos(){
        return this.dao.buscarTodos();
    }

    public Imagen buscar(int id){
        return this.dao.buscar(id);
    }
}
