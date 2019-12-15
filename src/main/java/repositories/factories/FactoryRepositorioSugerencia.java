package repositories.factories;

import models.SugerenciaModel;
import repositories.RepositorioSugerencia;
import repositories.daos.DAOMySQL;

public class FactoryRepositorioSugerencia
{
    private static RepositorioSugerencia repo;

    public static RepositorioSugerencia get(){
        if(repo == null)
        {
            repo = RepositorioSugerencia.getInstance(new DAOMySQL(SugerenciaModel.getInstance()));
        }
        return repo;
    }
}
