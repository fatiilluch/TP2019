package models;

import domain.events.Evento;
import db.EntityManagerHelper;
import java.util.List;

public class EventoModel extends Model{
    private static EventoModel instance;

    public static EventoModel getInstance() {
        if(instance == null) {
            instance = new EventoModel();
        }

        return instance;
    }

    @Override
    public List<Evento> buscarTodos() {
        return EntityManagerHelper.getEntityManager().createQuery("from Evento").getResultList();
    }

    @Override
    public Evento buscar(int id) {
        return EntityManagerHelper.getEntityManager().find(Evento.class, id);}


    private List<Evento> buscarEventoPorUsuario(int idUsuario){
    	String idUser = String.valueOf(idUsuario);
    	return EntityManagerHelper.getEntityManager().createQuery("from Evento where idUsuario= "+idUser).getResultList();
    }
    
    @Override
	public List<Evento> busquedaCondicional(int id) {
	   return this.buscarEventoPorUsuario(id);
	}

}