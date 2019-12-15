package domain;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import domain.events.Protocolo;
import domain.repositorio.RepositorioMolde;

public class PrendasSuperponiblesTest {
    Categoria superior = Categoria.Superior;
    Categoria inferior = Categoria.Inferior;
    Categoria calzado = Categoria.Calzado;
    Categoria accesorio = Categoria.Accesorio;
    
    Molde molde1;
    RepositorioMolde repoMolde = RepositorioMolde.getInstance();

    private TipoDePrenda camisa;
    private TipoDePrenda remera;
    private TipoDePrenda campera;
    private TipoDePrenda pantalon;
    private TipoDePrenda zapatos;
    private TipoDePrenda reloj;

    private Protocolo informal = Protocolo.Informal;

    private Prenda camisaMangaLarga;
    private Prenda remeraEnV;
    private Prenda camperaGrande;
    private Prenda pantalonDeTraje;
    private Prenda zapatosFormales;
    private Prenda relojSwatch;
    private Imagen avatar1;
    private Imagen avatar2;
    private Imagen avatar3;
    private Imagen avatar4;
    private Imagen avatar5;
    private Imagen avatar6;

    private Guardarropa formal1;

    private Usuario maria;
    private EstadoUsuario premium;

    @Before
    public void init()
    {
    	this.premium = new UsuarioPremium();
    	molde1 = new Molde();
    	
    	Sensibilidad sensibilidad = new Sensibilidad(0.0, 0.0);
        this.maria = new Usuario("Meri", "123", sensibilidad, "Maria", "Grillo");
        maria.setEstado(premium);
        this.formal1 = new Guardarropa();
        this.avatar1 = new Imagen("imagenes\\prenda.jpg");
        this.avatar2 = new Imagen("imagenes\\img2.png");
        this.avatar3 = new Imagen("imagenes\\img3.png");
        this.avatar4 = new Imagen("imagenes\\img4.png");
        this.avatar5 = new Imagen("imagenes\\img5.png");
        this.avatar6 = new Imagen("imagenes\\img6.png");

        this.camisa = new TipoDePrenda("camisa", superior, 1);
        this.remera = new TipoDePrenda("remera", superior, 1);
        this.campera = new TipoDePrenda("campera", superior, 2);
        this.pantalon = new TipoDePrenda("pantalon", inferior,0);
        this.zapatos = new TipoDePrenda("zapatos", calzado,0);
        this.reloj = new TipoDePrenda("reloj", accesorio,0);
        
        molde1.agregarTipoDePrenda(remera);
        molde1.agregarTipoDePrenda(pantalon);
        
        repoMolde.agregarMolde(molde1);

        this.camisaMangaLarga = new Prenda(camisa, Tela.ALGODON, Color.ROSA,  Protocolo.Informal,avatar2);
        this.camperaGrande = new Prenda(campera, Tela.SEDA, Color.NEGRO, Protocolo.Informal,avatar1);
        this.remeraEnV = new Prenda(remera, Tela.LANA,Color.BLANCO,Protocolo.Informal,avatar3);
        this.pantalonDeTraje = new Prenda(pantalon, Tela.LANA, Color.VERDE, Protocolo.Informal,avatar4);
        this.zapatosFormales = new Prenda(zapatos, Tela.CHAROL, Color.AZUL, Protocolo.Informal,avatar5);
        this.relojSwatch = new Prenda(reloj, Tela.METAL, Color.NEGRO, Protocolo.Informal,avatar6);

        formal1.agregarPrenda(camisaMangaLarga);
        formal1.agregarPrenda(camperaGrande);
        formal1.agregarPrenda(remeraEnV);
        formal1.agregarPrenda(pantalonDeTraje);
        formal1.agregarPrenda(zapatosFormales);
        formal1.agregarPrenda(relojSwatch);
    }

    @Test
    public void TestPrendasSuperponibles()
    {
        maria.agregarGuardarropa(formal1);
//        Assert.assertEquals(1,maria.pedirAtuendoAGuardarropa(formal1, informal));
    }
    
    @Test
    public void filtrarPorCategoriaSegunTest()
    {
    	int cantidasDePrendas = formal1.filtrarPorCategoria(superior).size();
    	Assert.assertEquals(3,cantidasDePrendas);
    }
    
    @Test
    public void indiceAleatorioTest()
    {
    	int indice = formal1.indiceAleatorio(superior);
    	Assert.assertTrue(indice >= 0);
    }
    
    @Test
    public void indiceAleatorioSegunElNivelTest() {
        List<Prenda> prendasLimitadas = formal1.getPrendas();
        int indice = formal1.indiceAleatorioSegunElNivel(prendasLimitadas, 0);
        System.out.println(indice);
        Assert.assertTrue(indice >= 0);
    }
}
