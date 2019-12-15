package domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;


public class PrendaTest {

	private TipoDePrenda remera;
	Categoria superior = Categoria.Superior;
	
	private Prenda remeraMangaCorta;
	
	@Before
    public void init(){
        this.remera = new TipoDePrenda("remera manga corta", superior);
       // this.remeraMangaCorta = new Prenda(remera,"Algodon","Roja","Negro");
    }
	
	@Test
	public void verCategoriaPrendaSuperior() {
		
		Assert.assertEquals(superior, remeraMangaCorta.getCategoria());
	}

}
