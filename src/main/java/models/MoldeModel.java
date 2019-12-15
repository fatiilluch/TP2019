package models;

import domain.Molde;
import db.EntityManagerHelper;
import java.util.List;

public class MoldeModel extends Model{
    private static MoldeModel instance;

    public static MoldeModel getInstance() {
        if(instance == null) {
            instance = new MoldeModel();
        }

        return instance;
    }

    @Override
    public List<Molde> buscarTodos() {
        return EntityManagerHelper.getEntityManager().createQuery("from Molde").getResultList();
    }

    @Override
    public Molde buscar(int id) {
        return EntityManagerHelper.getEntityManager().find(Molde.class, id);}
}