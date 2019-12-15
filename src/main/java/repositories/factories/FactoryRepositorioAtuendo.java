package repositories.factories;

import models.AtuendoModel;
import repositories.RepositorioAtuendo;
import repositories.daos.DAOMySQL;

public class FactoryRepositorioAtuendo {
    private static RepositorioAtuendo repo;

    public static RepositorioAtuendo get() {
        if (repo == null) {
            repo = RepositorioAtuendo.getInstance(new DAOMySQL(AtuendoModel.getInstance()));
        }
        return repo;
    }
}