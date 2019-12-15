package domain;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;


public class UsuarioTest {
	
	private Usuario pepe;	
	private Guardarropa deportivo;
	private Guardarropa formal;
	
	private Usuario lalo;
	
	private Sensibilidad sensibilidadPepe ,sensibilidadLalo;
	@Before
    public void init(){
		this.sensibilidadPepe = new Sensibilidad(2.0,3.2);
		Usuario pepe = new Usuario("Pepe", "123", sensibilidadPepe, "Pepe", "Grillo");
        this.deportivo = new Guardarropa();
        this.formal = new Guardarropa();
        pepe.agregarGuardarropa(deportivo);
        this.sensibilidadLalo = new Sensibilidad(2.0,3.2);
		Usuario lalo = new Usuario("Lalo", "123", sensibilidadPepe, "Lalin", "Lalon");
    }
	
	@Test
	public void nombrePepe(){
		Assert.assertEquals("Pepe", pepe.getUsername());
	}
	
	@Test
	public void agregarGuardarropaAPepe(){
		pepe.agregarGuardarropa(formal);
		Assert.assertEquals(2, pepe.cuantosGuardarropas());
	}
	
	@Test
	public void compartirGuardarropaALalo()
	{
		ArrayList<Usuario> usuarios = new ArrayList<>();
		usuarios.add(lalo);
		pepe.agregarGuardarropa(formal);
		pepe.comparteGuardarropas(usuarios, formal);
		List<Usuario> listUser = new ArrayList<Usuario>();
		
		listUser.add(lalo);
		pepe.agregarGuardarropa(formal);
		pepe.comparteGuardarropas(listUser, formal);
		Assert.assertTrue(lalo.estaEnLista(formal));
	}
}
