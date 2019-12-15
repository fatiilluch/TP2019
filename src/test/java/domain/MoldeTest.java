package domain;

import org.junit.*;

import java.util.ArrayList;
import java.util.List;
import domain.Categoria;
import domain.TipoDePrenda;
import domain.repositorio.*;

public class MoldeTest {
	Categoria superior = Categoria.Superior;
	Categoria inferior = Categoria.Inferior;
	Categoria calzado = Categoria.Calzado;
	Categoria accesorio = Categoria.Accesorio;
	
	private RepositorioMolde repoMolde  = RepositorioMolde.getInstance();
	private Molde unMolde = new Molde();
	private Molde otroMolde = new Molde();
	private TipoDePrenda pantalon;
	private TipoDePrenda sweater;
	private TipoDePrenda campera;
	
	@Before
	public void init() {
	
		List<Tela> telaRemeras = new ArrayList<Tela>(); 
		List<Tela> telaSueter = new ArrayList<Tela>(); 
		List<Tela> telaCamperas = new ArrayList<Tela>(); 
		List<Tela> telaPantalon= new ArrayList<Tela>(); 
		List<Tela> telaShort = new ArrayList<Tela>(); 
		List<Tela> telaZapatilla = new ArrayList<Tela>(); 
		List<Tela> telaZapato = new ArrayList<Tela>(); 
		
		List<TipoDePrenda> combinacionesRemera = new ArrayList<TipoDePrenda>(); 
		List<TipoDePrenda> combinacionesSueter = new ArrayList<TipoDePrenda>(); 
		List<TipoDePrenda> combinacionesCampera = new ArrayList<TipoDePrenda>(); 
		List<TipoDePrenda> combinacionesPantalon = new ArrayList<TipoDePrenda>(); 
		List<TipoDePrenda> combinacionesShort = new ArrayList<TipoDePrenda>(); 
		List<TipoDePrenda> combinacionesZapatilla = new ArrayList<TipoDePrenda>(); 
		List<TipoDePrenda> combinacionesZapato = new ArrayList<TipoDePrenda>(); 
		
		telaRemeras.add(Tela.ALGODON);
		telaRemeras.add(Tela.SEDA);
		telaRemeras.add(Tela.POLIESTER);
		telaRemeras.add(Tela.LYCRA);
		
		telaSueter.add(Tela.ALGODON);
		telaSueter.add(Tela.POLIESTER);
		telaSueter.add(Tela.SEDA);
		
		telaCamperas.add(Tela.ALGODON);
		telaCamperas.add(Tela.SEDA);
		telaCamperas.add(Tela.POLIESTER);
		telaCamperas.add(Tela.NYLON);
		telaCamperas.add(Tela.CUERO);
		
		telaPantalon.add(Tela.ALGODON);
		telaPantalon.add(Tela.POLIESTER);
		telaPantalon.add(Tela.JEAN);
		telaPantalon.add(Tela.NYLON);
		telaPantalon.add(Tela.SEDA);
		
		telaShort.add(Tela.ALGODON);
		telaShort.add(Tela.POLIESTER);
		telaShort.add(Tela.JEAN);
		telaShort.add(Tela.NYLON);
		telaShort.add(Tela.SEDA);
		
		telaZapatilla.add(Tela.CUERO);
		telaZapatilla.add(Tela.NYLON);
		
		telaZapato.add(Tela.CUERO);
		
		TipoDePrenda t1 = new TipoDePrenda("remera", Categoria.Superior,1, 1, telaRemeras, combinacionesRemera);
		TipoDePrenda t2 = new TipoDePrenda("sueter", Categoria.Superior,2,2,telaSueter,combinacionesSueter);
		TipoDePrenda t3 = new TipoDePrenda("campera", Categoria.Superior,3,3,telaCamperas,combinacionesCampera);
		TipoDePrenda t4 = new TipoDePrenda("pantalon", Categoria.Inferior,1,2,telaPantalon,combinacionesPantalon);
		TipoDePrenda t5 = new TipoDePrenda("short", Categoria.Inferior,1,1,telaShort,combinacionesShort);
		TipoDePrenda t6 = new TipoDePrenda("zapatilla", Categoria.Calzado,1,1,telaZapatilla,combinacionesZapatilla);
		TipoDePrenda t7 = new TipoDePrenda("zapato", Categoria.Calzado, 1,1,telaZapato,combinacionesZapato);
		
		sweater = t2;
		campera = t3;
		pantalon = t4;
		
		unMolde.agregarTipoDePrenda(t1);
		unMolde.agregarTipoDePrenda(t3);
		unMolde.agregarTipoDePrenda(t4);
		unMolde.agregarTipoDePrenda(t6);
		
		
		otroMolde.agregarTipoDePrenda(t2);
		otroMolde.agregarTipoDePrenda(t4);
		otroMolde.agregarTipoDePrenda(t5);
		otroMolde.agregarTipoDePrenda(t6);
		
	}
	
	@Test
	public void moldeIniciadoCon4Prendas() {
		Assert.assertTrue(unMolde.getTiposDePrendas().size() == 4);
	}
	
	@Test
	public void laZonaMasSensibleMoldeEsSuperior() {
		//se agrega una capa mas al molde
		unMolde.agregarTipoDePrenda(sweater);
		Assert.assertTrue(unMolde.esZonaMasAbrigada(superior));
	}
	@Test
	public void laZonaMasSensibleMoldeEsInferior() {
		unMolde.eliminarTipoDePrenda(sweater);
		unMolde.eliminarTipoDePrenda(campera);
		unMolde.agregarTipoDePrenda(pantalon);
		Assert.assertTrue(unMolde.esZonaMasAbrigada(inferior));
	}
	
	@Test
	public void elNivelDeAbrigoEs6() {
		Assert.assertEquals(6, otroMolde.getNivelDeAbrigo());
	}
	@Test
	public void moldeAbriga2PuntosDeCalor() {
		Assert.assertTrue(otroMolde.abriga(2,superior));
	}
	@Test
	public void moldeAbriga5PtsDeCalor() {
		TipoDePrenda tipoBuscado = unMolde.getTipoDePrendaSegun(inferior);
		unMolde.eliminarTipoDePrenda(tipoBuscado);
		otroMolde.agregarTipoDePrenda(pantalon);
		Assert.assertTrue(otroMolde.abriga(5,inferior));
	}
}
