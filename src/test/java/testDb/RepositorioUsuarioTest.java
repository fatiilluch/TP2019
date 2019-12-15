package testDb;

import models.UsuarioModel;
import repositories.RepositorioUsuario;
import repositories.daos.DAOMySQL;
import repositories.factories.FactoryRepositorioUsuario;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import domain.Usuario;

public class RepositorioUsuarioTest {

	RepositorioUsuario repoUser;
	DAOMySQL dao;
	
	@Before
	public void init() {
		dao = new DAOMySQL(UsuarioModel.getInstance());
		repoUser = RepositorioUsuario.getInstance(dao);
	}
	
	@Test
	public void obtenerUsuarioDeBDParaLoginTest() {
		
		Usuario usuario = this.repoUser.buscarPorUsernameAndPass("aroco", "123456");
//		Usuario usuario = this.repoUser.buscar(1);
		Assert.assertEquals("aroco", usuario.getUsername());
	}
	
	@Test
	public void obtenerListaUsuarioDeBDParaLoginTest() {
		
		List<Usuario> usuario = this.repoUser.buscarTodos();
//		Usuario usuario = this.repoUser.buscar(1);
		Assert.assertEquals("123456", usuario.get(0).getPassword());
	}
	@Test
	public void obtenerUsuarioFactoriDeBDParaLoginTest() {
		RepositorioUsuario repoUserF= FactoryRepositorioUsuario.get();
		Usuario usuario = repoUserF.buscarPorUsernameAndPass("jazul", "123456");
//		Usuario usuario = this.repoUser.buscar(1);
		Assert.assertEquals("jazul", usuario.getUsername());
	}
	
	
}
