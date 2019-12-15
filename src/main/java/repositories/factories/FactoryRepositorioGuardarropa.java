package repositories.factories;

import config.Config;
import models.GuardarropaModel;
import models.UsuarioModel;
import repositories.RepositorioGuardarropa;
import repositories.daos.DAOMemoria;
import repositories.daos.DAOMySQL;
//import repositories.testMemoData.DataGuardarropa;

public class FactoryRepositorioGuardarropa {
    private static RepositorioGuardarropa repo;

    public static RepositorioGuardarropa get(){
        if(repo == null){
            boolean useDataBase = Config.useDataBase;
            if(useDataBase){
                repo = RepositorioGuardarropa.getInstance(new DAOMySQL(GuardarropaModel.getInstance()));
            }
            else{
                repo = RepositorioGuardarropa.getInstance(new DAOMySQL(GuardarropaModel.getInstance()));
            }
        }
        return repo;
    }
}
