package models;

import domain.GuardarropaCompartido;
import db.EntityManagerHelper;
import java.util.List;

public class GuardarropaCompartidoModel extends Model{
    private static GuardarropaCompartidoModel instance;

    public static GuardarropaCompartidoModel getInstance() {
        if(instance == null) {
            instance = new GuardarropaCompartidoModel();
        }

        return instance;
    }

    @Override
    public List<GuardarropaCompartido> buscarTodos() {
        return EntityManagerHelper.getEntityManager().createQuery("from GuardarropaCompartido").getResultList();
    }

    @Override
    public GuardarropaCompartido buscar(int id) {
        return EntityManagerHelper.getEntityManager().find(GuardarropaCompartido.class, id);}
}

