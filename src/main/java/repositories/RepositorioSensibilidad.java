package repositories;

import domain.Sensibilidad;
import repositories.daos.DAO;
import java.util.List;

public class RepositorioSensibilidad extends Repositorio {
    private static RepositorioSensibilidad instance;

    public static RepositorioSensibilidad getInstance(DAO dao) {
        if(instance == null){
            instance = new RepositorioSensibilidad(dao);
        }
        return instance;
    }

    private RepositorioSensibilidad(DAO dao){
        this.setDao(dao);
    }

    public List<Sensibilidad> buscarTodos(){
        return this.dao.buscarTodos();
    }

    public Sensibilidad buscar(int id){
        return this.dao.buscar(id);
    }
}
