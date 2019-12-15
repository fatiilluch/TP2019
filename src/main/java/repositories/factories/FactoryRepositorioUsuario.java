package repositories.factories;

import models.UsuarioModel;
import repositories.RepositorioUsuario;
import repositories.daos.DAOMySQL;

public class FactoryRepositorioUsuario
{
    private static RepositorioUsuario repo;

    public static RepositorioUsuario get(){
        if(repo == null)
        {
            repo = RepositorioUsuario.getInstance(new DAOMySQL(UsuarioModel.getInstance()));
        }
        return repo;
    }
}
