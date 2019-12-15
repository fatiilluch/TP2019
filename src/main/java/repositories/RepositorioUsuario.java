package repositories;

import domain.Usuario;
import repositories.daos.DAO;
import java.util.List;
import java.util.stream.Collectors;

public class RepositorioUsuario extends Repositorio {
    private static RepositorioUsuario instance;

    public static RepositorioUsuario getInstance(DAO dao) {
        if(instance == null){
            instance = new RepositorioUsuario(dao);
        }
        return instance;
    }

    private RepositorioUsuario(DAO dao){
        this.setDao(dao);
    }

    public List<Usuario> buscarTodos(){
        return this.dao.buscarTodos();
    }

    public Usuario buscar(int id){
        return this.dao.buscar(id);
    }
    
    public Usuario buscarPorUsernameAndPass(String user, String pass) {
    	
    	Usuario usuario= this.buscarTodos().stream().
    			filter(u -> u.getUsername().equals(user) && u.getPassword().equals(pass)).findFirst().orElse(null);
    	return usuario;
    }
    
	public Usuario buscarPorUsername(String username) {
	    	
    	Usuario usuario= this.buscarTodos().stream().
    			filter(u -> u.getUsername().equals(username)).findFirst().orElse(null);
    	return usuario;
    }
}
