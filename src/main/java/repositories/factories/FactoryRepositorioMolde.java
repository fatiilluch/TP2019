package repositories.factories;

import models.MoldeModel;
import repositories.RepositorioMolde;
import repositories.daos.DAOMySQL;

public class FactoryRepositorioMolde {
	private static RepositorioMolde repo;
	
	public static RepositorioMolde get() {
		if(repo == null) {
			repo = RepositorioMolde.getInstance(new DAOMySQL(MoldeModel.getInstance()));
		}
		return repo;
	}

}
