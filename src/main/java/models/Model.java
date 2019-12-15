package models;

import db.EntityManagerHelper;

import java.util.List;

public abstract class Model {
    public void agregar(Object object){
        EntityManagerHelper.getEntityManager().getTransaction().begin();
        EntityManagerHelper.getEntityManager().persist(object);
        EntityManagerHelper.getEntityManager().getTransaction().commit();
    }

    public void modificar(Object object){
        EntityManagerHelper.getEntityManager().getTransaction().begin();
        EntityManagerHelper.getEntityManager().merge(object);
        EntityManagerHelper.getEntityManager().getTransaction().commit();
    }

    public void eliminar(Object object){
        EntityManagerHelper.getEntityManager().getTransaction().begin();
        EntityManagerHelper.getEntityManager().remove(object);
        EntityManagerHelper.getEntityManager().getTransaction().commit();
    }

    public abstract <T> List<T> buscarTodos();

    public abstract <T> T buscar(int id);

	public <T> List<T> busquedaCondicional(int id){
		return null;
	}

	public <T> T buscarGuardarropaPorPrenda(int idGuardarropa){return null;};
}
