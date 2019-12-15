package repositories.daos;

import models.Model;

import java.util.List;

public class DAOMySQL implements DAO {
    private Model model;

    public DAOMySQL(Model model){
        this.model = model;
    }

    @Override
    public <T> List<T> buscarTodos() {
        return this.model.buscarTodos();
    }

    @Override
    public <T> T buscar(int id) {
        return this.model.buscar(id);
    }

    @Override
    public void agregar(Object unObjeto) {
        this.model.agregar(unObjeto);
    }

    @Override
    public void modificar(Object unObjeto) {
        this.model.modificar(unObjeto);
    }

    @Override
    public void eliminar(Object unObjeto) {
        this.model.eliminar(unObjeto);
    }
    
    //es una query con un where de una FK, el modeloque lo usa lo define
    @Override
    public <T> List<T> busquedaCondicional(int id) {
    	return this.model.busquedaCondicional(id);
    }

    @Override
    public <T> T busquedaGuardarropaPorPrenda(int idPrenda){ return this.model.buscarGuardarropaPorPrenda(idPrenda);}

    public Model getModel() {
    	return model;
    }
}
