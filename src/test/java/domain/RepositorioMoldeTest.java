package domain;


import java.util.ArrayList;
import java.util.List;

import org.junit.*;
import domain.Categoria;
import domain.TipoDePrenda;
import domain.repositorio.*;


public class RepositorioMoldeTest {
	Categoria superior = Categoria.Superior;
	Categoria inferior = Categoria.Inferior;
	Categoria calzado = Categoria.Calzado;
	Categoria accesorio = Categoria.Accesorio;
	
	private RepositorioMolde repoMolde  = RepositorioMolde.getInstance();
	private Molde unMolde = new Molde();
	private Molde otroMolde = new Molde();
	private Molde moldeInferior = new Molde();
	int ptsDeCalorObjetivo;
	Categoria zonaSensible;
	
	@Before
	public void init() {
	
		/* *  Tipos Existentes en el RepoTipoDePrendas
		TipoDePrenda t1 = new TipoDePrenda("remera", Categoria.Superior,1, 1, telaRemeras, combinacionesRemera);
		TipoDePrenda t2 = new TipoDePrenda("sueter", Categoria.Superior,2,2,telaSueter,combinacionesSueter);
		TipoDePrenda t3 = new TipoDePrenda("campera", Categoria.Superior,3,3,telaCamperas,combinacionesCampera);
		TipoDePrenda t4 = new TipoDePrenda("pantalon", Categoria.Inferior,1,2,telaPantalon,combinacionesPantalon);
		TipoDePrenda t5 = new TipoDePrenda("short", Categoria.Inferior,1,1,telaShort,combinacionesShort);
		TipoDePrenda t6 = new TipoDePrenda("zapatilla", Categoria.Calzado,1,1,telaZapatilla,combinacionesZapatilla);
		TipoDePrenda t7 = new TipoDePrenda("zapato", Categoria.Calzado, 1,1,telaZapato,combinacionesZapato);
		
		 * */
		
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
		
		
		unMolde.agregarTipoDePrenda(t1);
		unMolde.agregarTipoDePrenda(t2);
		unMolde.agregarTipoDePrenda(t3);
		unMolde.agregarTipoDePrenda(t4);
		unMolde.agregarTipoDePrenda(t5);
		
		otroMolde.agregarTipoDePrenda(t1);
		otroMolde.agregarTipoDePrenda(t4);
		otroMolde.agregarTipoDePrenda(t7);

		moldeInferior.agregarTipoDePrenda(t1);
		moldeInferior.agregarTipoDePrenda(t7);
		
	
		repoMolde.agregarMolde(otroMolde);
	
		//molde1 sensibilidad=superior ptsDeCalor = 2 ,,molde2 sensibilidad=superior ptsDeCalor = 7
		//moldeInferior sensibilidad =  inferior  ptsDeCalor = 4
	}

	@Test
	public void existeMoldeCandidatoPara7ptsDeCalor() {
		Assert.assertEquals(4,repoMolde.getMoldeCandidato(4,superior).getNivelDeAbrigo());
	}
	
	@Test
	public void existeMoldeCandidatoPara2ptsDeCalor() {
		repoMolde.agregarMolde(unMolde);
		Assert.assertEquals(2,repoMolde.getMoldeCandidato(2,superior).getNivelDeAbrigo());
	}
	
	@Test
	public void generaMoldeACon4PTSDeCalor() {
		// pruebo que genere un molde que cubra 4 pts de calor con sensibilidad superior
		repoMolde.agregarMolde(unMolde);
		Assert.assertEquals(4,repoMolde.getMoldeCandidato(4,superior).getNivelDeAbrigo());	
	}
	
	@Test
	public void generarMoldeCon5pts() {
		repoMolde.agregarMolde(unMolde);
		Molde moldeCandidato = repoMolde.getMoldeCandidato(5,superior);
		Assert.assertTrue(moldeCandidato.abriga(5, superior));
		
	}

	@Test
	public void generarMoldeConSensibilidadInferior() {
		// cambia la distribucion de calor de molde1 de superior a inferior
		repoMolde.agregarMolde(unMolde);
		Assert.assertEquals(4,repoMolde.getMoldeCandidato(4,inferior).getNivelDeAbrigo());
	}
	@Test
	public void generarMoldeConSensibilidadSuperiorAPartirDeMoldeInferior() {
		repoMolde.agregarMolde(moldeInferior);
		Assert.assertEquals(5,repoMolde.getMoldeCandidato(5,superior).getNivelDeAbrigo());
		
	}
}
