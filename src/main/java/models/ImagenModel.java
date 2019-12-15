package models;

import domain.Imagen;
import db.EntityManagerHelper;
import java.util.List;

public class ImagenModel extends Model {
    private static ImagenModel instance;

    public static ImagenModel getInstance() {
        if(instance == null){
            instance = new ImagenModel();
        }
        return instance;
    }

    @Override
    public List<Imagen> buscarTodos(){
        return EntityManagerHelper.getEntityManager().createQuery("from imagen").getResultList();
    }

    @Override
    public Imagen buscar(int id){
        return EntityManagerHelper.getEntityManager().find(Imagen.class, id);
    }

}