package repositories;

import domain.GuardarropaCompartido;
import repositories.daos.DAO;
import java.util.List;

public class RepositorioGuardarropaCompartido extends Repositorio {
    private static RepositorioGuardarropaCompartido instance;

    public static RepositorioGuardarropaCompartido getInstance(DAO dao) {
        if(instance == null){
            instance = new RepositorioGuardarropaCompartido(dao);
        }
        return instance;
    }

    private RepositorioGuardarropaCompartido(DAO dao){
        this.setDao(dao);
    }

    public List<GuardarropaCompartido> buscarTodos(){
        return this.dao.buscarTodos();
    }

    public GuardarropaCompartido buscar(int id){
        return this.dao.buscar(id);
    }
}
