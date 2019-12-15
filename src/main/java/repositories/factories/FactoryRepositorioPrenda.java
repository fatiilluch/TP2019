package repositories.factories;

import models.PrendaModel;
import repositories.RepositorioPrenda;
import repositories.daos.DAOMySQL;

public class FactoryRepositorioPrenda
{
    private static RepositorioPrenda repo;

    public static  RepositorioPrenda get()
    {
        if (repo == null)
        {
            repo = RepositorioPrenda.getInstance(new DAOMySQL((PrendaModel.getInstance())));
        }
        return repo;
    }
}