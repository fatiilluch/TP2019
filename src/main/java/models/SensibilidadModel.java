package models;

import domain.Sensibilidad;
import db.EntityManagerHelper;
import java.util.List;

public class SensibilidadModel extends Model{
    private static SensibilidadModel instance;

    public static SensibilidadModel getInstance() {
        if(instance == null) {
            instance = new SensibilidadModel();
        }

        return instance;
    }

    @Override
    public List<Sensibilidad> buscarTodos() {
        return EntityManagerHelper.getEntityManager().createQuery("from sensibilidad").getResultList();
    }

    @Override
    public Sensibilidad buscar(int id) {
        return EntityManagerHelper.getEntityManager().find(Sensibilidad.class, id);}
}

