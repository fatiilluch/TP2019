package models;

import domain.Prenda;
import domain.Tela;
import domain.TipoDePrenda;
import db.EntityManagerHelper;
import java.util.List;

public class TipoDePrendaModel extends Model{
    private static TipoDePrendaModel instance;

    public static TipoDePrendaModel getInstance() {
        if(instance == null) {
            instance = new TipoDePrendaModel();
        }

        return instance;
    }

    @Override
    public List<TipoDePrenda> buscarTodos() {
        return EntityManagerHelper.getEntityManager().createQuery("from TipoDePrenda").getResultList();
    }

    @Override
    public TipoDePrenda buscar(int id) {
        return EntityManagerHelper.getEntityManager().find(TipoDePrenda.class, id);}
    
    
    
    private List<Tela> buscarTelasPorTipoDePrenda(int idTipoPrenda){
		String idTipoPrendas = String.valueOf(idTipoPrenda);
		TipoDePrenda tipoDePrenda = TipoDePrendaModel.getInstance().buscar(idTipoPrenda);
		
		//TipoDePrenda tipoDePrenda = EntityManagerHelper.getEntityManager().createQuery("from TipoDePrenda where id= "+idTipoPrendas).getResultList();
		return tipoDePrenda.getTelasAdmitidas();
	}

	@Override
	public List<Tela> busquedaCondicional(int id) {
		return this.buscarTelasPorTipoDePrenda(id);
	}
    
}