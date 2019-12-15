package domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UsuarioTiposTest {
    Categoria superior = Categoria.Superior;
    Categoria inferior = Categoria.Inferior;
    Categoria calzado = Categoria.Calzado;
    Categoria accesorio = Categoria.Accesorio;

    private TipoDePrenda camisa;
    private TipoDePrenda pantalonFormal;
    private TipoDePrenda zapatos;
    private TipoDePrenda sweater;
    private TipoDePrenda remera;
    private TipoDePrenda campera;
    private TipoDePrenda reloj;

    private Guardarropa formal = new Guardarropa();

    private Prenda camisaMangaLarga = new Prenda(); private Prenda camisaMangaLarga1 = new Prenda(); private Prenda camisaMangaLarga2 = new Prenda();
    private Prenda pantalonDeTraje = new Prenda(); private Prenda pantalonDeTraje1 = new Prenda(); private Prenda zapatosFormales = new Prenda(); private Prenda zapatosFormales1 = new Prenda();
    private Prenda sweaterEnV = new Prenda(); private Prenda sweaterEnV1 = new Prenda(); private Prenda pantalonDeTraje2 = new Prenda(); private Prenda zapatosFormales2 = new Prenda(); private Prenda sweaterEnV2 = new Prenda(); private Prenda camisaMangaLarga3 = new Prenda(); private Prenda pantalonDeTraje3 = new Prenda(); private Prenda zapatosFormales3 = new Prenda(); private Prenda sweaterEnV3 = new Prenda(); private Prenda camisaMangaLarga4 = new Prenda(); private Prenda pantalonDeTraje4 = new Prenda(); private Prenda zapatosFormales4 = new Prenda(); private Prenda sweaterEnV4 = new Prenda(); private Prenda prendaVeintiuno = new Prenda();

    private Usuario pepe;
    private Sensibilidad sensibilidadPepe;

    @Before
    public void init()
    {
    	this.sensibilidadPepe = new Sensibilidad(2.0,3.2);
        Usuario pepe = new Usuario("Pepe", "123", sensibilidadPepe, "Pepe", "Grillo");
        pepe.setEstado(new UsuarioNormal());
        pepe.agregarGuardarropa(formal);

        this.camisa = new TipoDePrenda("camisa", superior);
        this.pantalonFormal = new TipoDePrenda("pantalon de traje", inferior);
        this.zapatos = new TipoDePrenda("zapatos", calzado);
        this.sweater = new TipoDePrenda("sweater", superior);


        pepe.agregarPrenda(formal, camisaMangaLarga);
        pepe.agregarPrenda(formal, camisaMangaLarga1);
        pepe.agregarPrenda(formal, camisaMangaLarga2);
        pepe.agregarPrenda(formal, camisaMangaLarga3);
        pepe.agregarPrenda(formal, camisaMangaLarga4);
        pepe.agregarPrenda(formal, pantalonDeTraje);
        pepe.agregarPrenda(formal, pantalonDeTraje1);
        pepe.agregarPrenda(formal, pantalonDeTraje2);
        pepe.agregarPrenda(formal, pantalonDeTraje3);
        pepe.agregarPrenda(formal, pantalonDeTraje4);
        pepe.agregarPrenda(formal, zapatosFormales);
        pepe.agregarPrenda(formal, zapatosFormales1);
        pepe.agregarPrenda(formal, zapatosFormales2);
        pepe.agregarPrenda(formal, zapatosFormales3);
        pepe.agregarPrenda(formal, zapatosFormales4);
        pepe.agregarPrenda(formal, sweaterEnV);
        pepe.agregarPrenda(formal, sweaterEnV1);
        pepe.agregarPrenda(formal, sweaterEnV2);
        pepe.agregarPrenda(formal, sweaterEnV3);
        pepe.agregarPrenda(formal, sweaterEnV4);
        pepe.agregarPrenda(formal, camisaMangaLarga);
        pepe.agregarPrenda(formal, camisaMangaLarga1);
        pepe.agregarPrenda(formal, camisaMangaLarga2);
        pepe.agregarPrenda(formal, camisaMangaLarga3);
        pepe.agregarPrenda(formal, camisaMangaLarga4);
        pepe.agregarPrenda(formal, pantalonDeTraje);
        pepe.agregarPrenda(formal, pantalonDeTraje1);
        pepe.agregarPrenda(formal, pantalonDeTraje2);
        pepe.agregarPrenda(formal, pantalonDeTraje3);
        pepe.agregarPrenda(formal, pantalonDeTraje4);
        pepe.agregarPrenda(formal, zapatosFormales);
        pepe.agregarPrenda(formal, zapatosFormales1);
        pepe.agregarPrenda(formal, zapatosFormales2);
        pepe.agregarPrenda(formal, zapatosFormales3);
        pepe.agregarPrenda(formal, zapatosFormales4);
        pepe.agregarPrenda(formal, sweaterEnV);
        pepe.agregarPrenda(formal, sweaterEnV1);
        pepe.agregarPrenda(formal, sweaterEnV2);
        pepe.agregarPrenda(formal, sweaterEnV3);
        pepe.agregarPrenda(formal, sweaterEnV4);
    }

    @Test
    public void TestUsuarioNormal(){
        pepe.agregarPrenda(formal, prendaVeintiuno);
        System.out.println("formal tiene "+pepe.cuantasPrendasTiene(formal) +" prendas");
        Assert.assertEquals(40,pepe.cuantasPrendasTiene(formal));
    }

    @Test
    public void TestUsuarioPremium(){
        pepe.cambiarEstado();
        pepe.agregarPrenda(formal,prendaVeintiuno);
        System.out.println("formal tiene "+pepe.cuantasPrendasTiene(formal) +" prendas");
        Assert.assertEquals(41,pepe.cuantasPrendasTiene(formal));
    }

}


