package domain;

import java.util.ArrayList;
import java.util.List;

import domain.suggestions.AceptarSugerencia;
import domain.suggestions.CalificarSugerencia;
import domain.suggestions.Confortable;
import domain.suggestions.Friolenta;
import domain.suggestions.RechazarSugerencia;
import domain.Atuendo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import domain.events.Protocolo;

public class PruebasSugerenciaTest 
{
	Categoria superior = Categoria.Superior;
	Categoria inferior = Categoria.Inferior;
	Categoria calzado = Categoria.Calzado;
	Categoria accesorio = Categoria.Accesorio;

	private Usuario pepe;
	private EstadoUsuario premium;
	private TipoDePrenda remera, pantalon, anteojos, sandalias, campera, medias;
	private Prenda remeraMangaCorta, pantalonCorto, sandaliasRomanas, anteojosDeSol, camperaDeLana, mediasGruesas;
	
	private Guardarropa informal;
	
	private Atuendo atuendo;
	
	private Protocolo joda = Protocolo.Informal;
	private Imagen avatar1;
	private Imagen avatar2;
	private Imagen avatar3;
	private Imagen avatar4;
	private Imagen avatar5;
	private Imagen avatar6;
	
	private Friolenta friolenta;
	private Confortable confortable;
	@Before
    public void init()
	{	
		friolenta = new Friolenta();
		confortable = new Confortable();
		Sensibilidad sensibilidadPepe = new Sensibilidad(1.0,1.2);
		this.premium = new UsuarioPremium();
		Usuario pepe = new Usuario("Pepe", "123", sensibilidadPepe, "Pepe", "Grillo");
		
		this.pepe.setEstado(premium);
		
		this.avatar1 = new Imagen("imagenes\\prenda.jpg");
        this.avatar2 = new Imagen("imagenes\\img2.png");
        this.avatar3 = new Imagen("imagenes\\img3.png");
        this.avatar4 = new Imagen("imagenes\\img4.png");
        this.avatar5 = new Imagen("imagenes\\img5.png");
        this.avatar6 = new Imagen("imagenes\\img6.png");
		
		this.remera = new TipoDePrenda("remera manga corta", superior);
        this.pantalon = new TipoDePrenda("pantalon", inferior);
        this.sandalias = new TipoDePrenda("sandalias", calzado);
        this.anteojos = new TipoDePrenda("anteojos de sol", accesorio);
        this.campera = new TipoDePrenda("campera", superior);
        this.medias = new TipoDePrenda("media", calzado);
        this.remeraMangaCorta = new Prenda(remera, Tela.ALGODON, Color.BLANCO, joda, avatar1);
        this.pantalonCorto = new Prenda(pantalon, Tela.ALGODON, Color.ROSA, joda, avatar2);
        this.sandaliasRomanas = new Prenda(sandalias, Tela.CHAROL, Color.NEGRO, joda, avatar3);
        this.anteojosDeSol = new Prenda(anteojos, Tela.GOMA, Color.NEGRO, joda, avatar4);
        this.camperaDeLana = new Prenda(campera, Tela.LANA, Color.ROJO, joda, avatar5);
        this.mediasGruesas = new Prenda(medias, Tela.TELA, Color.VERDE, joda, avatar6);
      
        this.informal = new Guardarropa();
        informal.agregarPrenda(remeraMangaCorta);
        informal.agregarPrenda(pantalonCorto);
        informal.agregarPrenda(sandaliasRomanas);
        informal.agregarPrenda(anteojosDeSol);
        informal.agregarPrenda(mediasGruesas);
        informal.agregarPrenda(camperaDeLana);
        
		List<Prenda> prendas= new ArrayList<Prenda>();
		
		//atuendo = this.pepe.pedirAtuendoAGuardarropa(informal, joda);
		prendas.add(camperaDeLana); 
		prendas.add(remeraMangaCorta);
		prendas.add(pantalonCorto);
		prendas.add(mediasGruesas);
		prendas.add(sandaliasRomanas);
		prendas.add(anteojosDeSol);
		
		atuendo = new Atuendo(prendas, Protocolo.Informal);
	}
	
	@Test
	public void aceptarSugerenciaAutomatica() 
	{
		this.pepe.agregarGuardarropa(informal);
		
		Atuendo otroAtuendo = this.pepe.pedirAtuendoAGuardarropa(informal, joda);
		
		this.pepe.setGestor(new GestorDeAcciones());
		
		AceptarSugerencia aceptar = new AceptarSugerencia();
		
		aceptar.setAtuendo(otroAtuendo);
		aceptar.setGuardarropa(informal);
		
	

		this.pepe.getGestor().ejecutar(aceptar);
		
		//Assert.assertTrue(informal.getAtuendosAceptados().contains(otroAtuendo));
		Assert.assertEquals(informal.getAtuendosAceptados().size(), 1);
	}
	
	@Test
	public void rechazarSugerenciaAutomatica() 
	{
		this.pepe.agregarGuardarropa(informal);
		
		Atuendo otroAtuendo = this.pepe.pedirAtuendoAGuardarropa(informal, joda);
		
		this.pepe.setGestor(new GestorDeAcciones());
		
		RechazarSugerencia rechazar = new RechazarSugerencia();
		
		rechazar.setAtuendo(otroAtuendo);
		rechazar.setGuardarropa(informal);
		
		this.pepe.getGestor().ejecutar(rechazar);
		Assert.assertEquals(informal.getAtuendosRechazados().size(), 1);
		
	}
	
	
	@Test
	public void calificarSugerenciaAutomatica() {
	
		CalificarSugerencia calificar= new CalificarSugerencia(pepe.getSensibilidad(),friolenta,confortable);
		calificar.setAtuendo(atuendo);
		this.pepe.setGestor(new GestorDeAcciones());
		
		this.pepe.getGestor().ejecutar(calificar);
		
		Assert.assertEquals(pepe.getSensibilidad().inferior(), 1.2, 0.01);
		
	}
	@Test
	public void deshacerSugerenciaAutomatica() 
	{
		java.util.List<Atuendo> atuendosRech;
		
		this.pepe.agregarGuardarropa(informal);
		
		Atuendo otroAtuendo = this.pepe.pedirAtuendoAGuardarropa(informal, joda);
		
		this.pepe.setGestor(new GestorDeAcciones());
		
		RechazarSugerencia rechazar = new RechazarSugerencia();
		
		rechazar.setAtuendo(otroAtuendo);
		rechazar.setGuardarropa(informal);
		
		this.pepe.getGestor().ejecutar(rechazar);
		atuendosRech = this.informal.getAtuendosRechazados();
		
		System.out.print(atuendosRech.toString());
		
		this.pepe.getGestor().deshacer();
		
		Assert.assertTrue(!informal.getAtuendosRechazados().contains(otroAtuendo));
	}

	
	/*@Test
	public void aceptarSugerencia() 
	{
		this.pepe.setGestor(new GestorDeAcciones());
		
		AceptarSugerencia aceptar = new AceptarSugerencia();
		
		aceptar.setAtuendo(atuendo);
		aceptar.setGuardarropa(informal);
		
		this.pepe.getGestor().ejecutar(aceptar);
		Assert.assertTrue(informal.getAtuendosAceptados().contains(atuendo));
	}
	
	@Test
	public void rechazarSugerencia()
	{
		this.pepe.setGestor(new GestorDeAcciones());
		
		RechazarSugerencia rechazar = new RechazarSugerencia();
		
		rechazar.setAtuendo(atuendo);
		rechazar.setGuardarropa(informal);
		
		this.pepe.getGestor().ejecutar(rechazar);
		Assert.assertTrue(informal.getAtuendosRechazados().contains(atuendo));
	}
	
	@Test
	public void deshacerSugerencia()
	{
		List<Atuendo> atuendosRech;
		
		this.pepe.setGestor(new GestorDeAcciones());
		
		RechazarSugerencia rechazar = new RechazarSugerencia();
		
		rechazar.setAtuendo(atuendo);
		rechazar.setGuardarropa(informal);
		
		this.pepe.getGestor().ejecutar(rechazar);
		atuendosRech = this.informal.getAtuendosRechazados();
		
		System.out.print(atuendosRech.toString());
		
		this.pepe.getGestor().deshacer();
		
		Assert.assertTrue(!informal.getAtuendosRechazados().contains(atuendo));
	}*/
}