package models;

import domain.Atuendo;
import db.EntityManagerHelper;
import java.util.List;

public class AtuendoModel extends Model {
        private static AtuendoModel instance;

        public static AtuendoModel getInstance() {
            if(instance == null){
                instance = new AtuendoModel();
            }
            return instance;
        }

        @Override
        public List<Atuendo> buscarTodos(){
            return EntityManagerHelper.getEntityManager().createQuery("from Atuendo").getResultList();
        }

        @Override
        public Atuendo buscar(int id){
            return EntityManagerHelper.getEntityManager().find(Atuendo.class, id);
        }

}
