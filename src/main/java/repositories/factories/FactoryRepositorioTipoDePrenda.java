package repositories.factories;

import models.TipoDePrendaModel;
import repositories.RepositorioTipoDePrenda;
import repositories.daos.DAOMySQL;

public class FactoryRepositorioTipoDePrenda
{
    private static RepositorioTipoDePrenda repo;

    public static RepositorioTipoDePrenda get(){
        if(repo == null)
        {
            repo = RepositorioTipoDePrenda.getInstance(new DAOMySQL(TipoDePrendaModel.getInstance()));
        }
        return repo;
    }
}
