package domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GuardarropaTest {

	Categoria superior = Categoria.Superior;
	Categoria inferior = Categoria.Inferior;
	Categoria calzado = Categoria.Calzado;
	Categoria accesorio = Categoria.Accesorio;
	
	@SuppressWarnings("unused")
	private TipoDePrenda remera;
	private Prenda remeraMangaCorta;
	private Prenda remeraEnV;
	private Guardarropa informal;
	
	@Before
    public void init(){
        this.remera = new TipoDePrenda("remera manga corta", superior);
        //this.remeraMangaCorta = new Prenda(remera,"Algodon","Roja","Negro");
        //this.remeraEnV = new Prenda(remera,"Hilo","Azul"); //tiene solamente color primario
        this.informal = new Guardarropa();
        informal.agregarPrenda(remeraMangaCorta);
        informal.agregarPrenda(remeraEnV);
    }
	
	@Test
	public void laCantidadDePrendasDelGuardarropaIngormalEsIgualA2() {
		Assert.assertEquals(2, informal.cantidadDePrendas());
	}

}
