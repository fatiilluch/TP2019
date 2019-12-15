package domain;

import domain.*;
import domain.events.Protocolo;
import domain.repositorio.RepositorioMolde;
import exception.TipoDePrendaSinCombinableSegunNivelCapa;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ConstruccionMoldeTest {

	RepositorioMolde repo;
	Molde molde;
	List<Guardarropa> guardarropas1 = new ArrayList<>();
	Guardarropa guardarropaAroco ;

	
	
	@Before
	public void init() {
		repo = RepositorioMolde.getInstance();
		molde = new Molde();
		
		guardarropaAroco = new Guardarropa("Informal Aroco");
		
		//Telas
        List<Tela> telaRemeras = new ArrayList<Tela>(); 
    	List<Tela> telaSueter = new ArrayList<Tela>(); 
    	List<Tela> telaCamperas = new ArrayList<Tela>(); 
    	List<Tela> telaPantalonYPollera= new ArrayList<Tela>(); 
    	List<Tela> telaCalzayBuzo = new ArrayList<Tela>(); 
    	List<Tela> telaMusculosa = new ArrayList<Tela>();
    	List<Tela> telaZapatilla = new ArrayList<Tela>(); 
    	List<Tela> telaZapatoYSandalias = new ArrayList<Tela>(); 
    	
    	//Tipos de prendas
    	List<TipoDePrenda> combinacionesRemera = new ArrayList<TipoDePrenda>(); 
    	List<TipoDePrenda> combinacionesSueter = new ArrayList<TipoDePrenda>(); 
    	List<TipoDePrenda> combinacionesCampera = new ArrayList<TipoDePrenda>(); 
    	List<TipoDePrenda> combinacionesPantalonLargo = new ArrayList<TipoDePrenda>(); 
    	List<TipoDePrenda> combinacionesPantalonCorto = new ArrayList<TipoDePrenda>(); 
    	List<TipoDePrenda> combinacionesBuzo = new ArrayList<TipoDePrenda>(); 
    	List<TipoDePrenda> combinacionesMusculosa = new ArrayList<TipoDePrenda>(); 
    	List<TipoDePrenda> combinacionesZapatilla = new ArrayList<TipoDePrenda>(); 
    	List<TipoDePrenda> combinacionesZapato = new ArrayList<TipoDePrenda>(); 
    	List<TipoDePrenda> combinacionesSandalia = new ArrayList<TipoDePrenda>();
    	
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
 		
 		telaPantalonYPollera.add(Tela.ALGODON);
 		telaPantalonYPollera.add(Tela.POLIESTER);
 		telaPantalonYPollera.add(Tela.JEAN);
 		telaPantalonYPollera.add(Tela.NYLON);
 		telaPantalonYPollera.add(Tela.SEDA);
 		
 		telaCalzayBuzo.add(Tela.ALGODON);
 		telaCalzayBuzo.add(Tela.POLIESTER);
 		telaCalzayBuzo.add(Tela.LYCRA);
 		telaCalzayBuzo.add(Tela.NYLON);

 		telaMusculosa.add(Tela.ALGODON);
 		telaMusculosa.add(Tela.LYCRA);
 		
 		telaZapatilla.add(Tela.CUERO);
 		telaZapatilla.add(Tela.NYLON);
 		
 		telaZapatoYSandalias.add(Tela.CUERO);
		
		
 		
 		TipoDePrenda t1 = new TipoDePrenda("remera cuello redongo manga corta", Categoria.Superior,1, 5, telaRemeras, combinacionesRemera);
 		TipoDePrenda t2 = new TipoDePrenda("remera cuello redongo manga larga", Categoria.Superior,1, 8, telaRemeras, combinacionesRemera);
 		TipoDePrenda t3 = new TipoDePrenda("remera escote en v manga corta", Categoria.Superior,1, 5, telaRemeras, combinacionesRemera);
 		TipoDePrenda t4 = new TipoDePrenda("remera escote en v manga larga", Categoria.Superior,1, 8, telaRemeras, combinacionesRemera);
 		TipoDePrenda t5 = new TipoDePrenda("sueter", Categoria.Superior,2,15,telaSueter,combinacionesSueter);
 		TipoDePrenda t6 = new TipoDePrenda("campera", Categoria.Superior,3,13,telaCamperas,combinacionesCampera);
 		TipoDePrenda t7 = new TipoDePrenda("pantalon largo", Categoria.Inferior,1,8,telaPantalonYPollera,combinacionesPantalonLargo);
 		TipoDePrenda t8 = new TipoDePrenda("pantalon corto", Categoria.Inferior,1,3,telaPantalonYPollera,combinacionesPantalonCorto);
 		TipoDePrenda t9 = new TipoDePrenda("bermuda", Categoria.Inferior,1,3,telaPantalonYPollera,combinacionesPantalonCorto);
 		TipoDePrenda t10 = new TipoDePrenda("pollera", Categoria.Inferior,1,3,telaPantalonYPollera,combinacionesPantalonCorto);
 		TipoDePrenda t11 = new TipoDePrenda("calza", Categoria.Inferior,1,5,telaCalzayBuzo,combinacionesPantalonCorto);
 		TipoDePrenda t12 = new TipoDePrenda("buzo", Categoria.Superior,2,13,telaCalzayBuzo,combinacionesBuzo);
 		TipoDePrenda t13 = new TipoDePrenda("musculosa", Categoria.Superior,1,3,telaMusculosa,combinacionesMusculosa);
 		TipoDePrenda t14 = new TipoDePrenda("zapatilla", Categoria.Calzado,1,0,telaZapatilla,combinacionesZapatilla);
 		TipoDePrenda t15 = new TipoDePrenda("zapato", Categoria.Calzado, 1,0,telaZapatoYSandalias,combinacionesZapato);
 		TipoDePrenda t16 = new TipoDePrenda("sandalias", Categoria.Calzado,1,0,telaZapatoYSandalias,combinacionesSandalia);
 		
 		
 		
 		combinacionesZapatilla.add(t1);
 		combinacionesZapatilla.add(t2);
 		combinacionesZapatilla.add(t3);
 		combinacionesZapatilla.add(t4);
 		combinacionesZapatilla.add(t5);
 		combinacionesZapatilla.add(t6);
 		combinacionesZapatilla.add(t7);
 		combinacionesZapatilla.add(t8);
 		combinacionesZapatilla.add(t9);
 		combinacionesZapatilla.add(t10);
 		combinacionesZapatilla.add(t11);
 		combinacionesZapatilla.add(t12);
 		combinacionesZapatilla.add(t13);
 		combinacionesZapatilla.add(t14);
 		combinacionesZapatilla.add(t15);
 		combinacionesZapatilla.add(t16);
 		
 		combinacionesZapato.add(t7);
 		combinacionesZapato.add(t10);
 		
 		combinacionesSandalia.add(t7);
 		combinacionesSandalia.add(t8);
 		combinacionesSandalia.add(t10);
 		
 		
 		combinacionesMusculosa.add(t12);
 		combinacionesMusculosa.add(t6);
 		combinacionesMusculosa.add(t8);
 		combinacionesMusculosa.add(t11);
 		combinacionesMusculosa.add(t10);
 		
 		combinacionesBuzo.add(t1);
 		combinacionesBuzo.add(t2);
 		combinacionesBuzo.add(t3);
 		combinacionesBuzo.add(t4);
 		combinacionesBuzo.add(t13);
 		combinacionesBuzo.add(t7);
 		combinacionesBuzo.add(t8);
 		combinacionesBuzo.add(t9);
 		combinacionesBuzo.add(t10);
 		
 		combinacionesPantalonCorto.add(t1);
 		combinacionesPantalonCorto.add(t2);
 		combinacionesPantalonCorto.add(t3);
 		combinacionesPantalonCorto.add(t4);
 		combinacionesPantalonCorto.add(t6);
 		combinacionesPantalonCorto.add(t13);
 		
 		combinacionesPantalonLargo.add(t1);
 		combinacionesPantalonLargo.add(t2);
 		combinacionesPantalonLargo.add(t3);
 		combinacionesPantalonLargo.add(t4);
 		combinacionesPantalonLargo.add(t5);
 		combinacionesPantalonLargo.add(t6);
 		combinacionesPantalonLargo.add(t13);
 		
 		
 		combinacionesCampera.add(t1);
 		combinacionesCampera.add(t2);
 		combinacionesCampera.add(t3);
 		combinacionesCampera.add(t4);
 		combinacionesCampera.add(t7);
 		combinacionesCampera.add(t8);
 		combinacionesCampera.add(t9);
 		combinacionesCampera.add(t10);
 		combinacionesCampera.add(t11);
 		
 		combinacionesRemera.add(t5);
 		combinacionesRemera.add(t6);
 		combinacionesRemera.add(t7);
 		combinacionesRemera.add(t8);
 		combinacionesRemera.add(t10);
 		combinacionesRemera.add(t9);
 		combinacionesRemera.add(t11);
 		combinacionesRemera.add(t12);
 		
 		combinacionesSueter.add(t1);
 		combinacionesSueter.add(t2);
 		combinacionesSueter.add(t3);
 		combinacionesSueter.add(t4);
 		combinacionesSueter.add(t6);
 		combinacionesSueter.add(t7);
 		
 		
 		
 		Prenda remeraCuelloRedondoMangaCorta = new Prenda(t1, Tela.ALGODON, Color.NEGRO, Protocolo.Informal, null);
    	Prenda remeraEscoteVMangaCorta = new Prenda(t3, Tela.LYCRA, Color.BLANCO, Protocolo.Informal, null);
    	Prenda musculosa = new Prenda(t13, Tela.LYCRA, Color.AMARILLO, Protocolo.Informal, null);
    	Prenda campera = new Prenda(t6, Tela.CUERO, Color.BLANCO, Protocolo.Informal, null);
    	Prenda sueter = new Prenda(t5, Tela.POLIESTER, Color.BLANCO, Protocolo.Informal, null);
    	Prenda bermuda = new Prenda(t9, Tela.JEAN, Color.CELESTE, Protocolo.Informal, null);
    	Prenda pantalonLargo = new Prenda(t7, Tela.NYLON, Color.GRIS, Protocolo.Informal, null);
    	Prenda zapatillas = new Prenda(t14, Tela.NYLON, Color.BORDO, Protocolo.Informal, null);
    	Prenda zapatos = new Prenda(t15, Tela.CUERO, Color.NEGRO, Protocolo.Informal, null);
    	
    	Prenda remeraCuelloRedondoML = new Prenda(t2, Tela.LYCRA, Color.AMARILLO, Protocolo.Informal, null);
    	Prenda remeraEscoteVML = new Prenda(t4, Tela.ALGODON, Color.BLANCO, Protocolo.Informal, null);
    	Prenda musculosa2 = new Prenda(t13, Tela.LYCRA, Color.VERDE, Protocolo.Informal, null);
    	Prenda sueter2 = new Prenda(t5, Tela.POLIESTER, Color.GRIS, Protocolo.Informal, null);
    	Prenda pollera = new Prenda(t10, Tela.SEDA, Color.NEGRO, Protocolo.Informal, null);
    	Prenda calza = new Prenda(t11, Tela.NYLON, Color.NEGRO, Protocolo.Informal, null);
    	Prenda buzo = new Prenda(t12, Tela.ALGODON, Color.BLANCO, Protocolo.Informal, null);
    	Prenda sandalias = new Prenda(t16, Tela.CUERO, Color.NEGRO, Protocolo.Informal, null);
    	Prenda zapatos2 = new Prenda(t15, Tela.CUERO, Color.NEGRO, Protocolo.Informal, null);

    	List<Prenda> prendasAroco = new ArrayList<>();
    	prendasAroco.add(remeraCuelloRedondoMangaCorta);
    	prendasAroco.add(remeraEscoteVMangaCorta);
    	prendasAroco.add(musculosa);
    	prendasAroco.add(campera);
    	prendasAroco.add(sueter);
    	prendasAroco.add(bermuda);
    	prendasAroco.add(pantalonLargo);
    	prendasAroco.add(zapatillas);
    	prendasAroco.add(zapatos);
    	
    	
        guardarropas1.add(guardarropaAroco);
        guardarropaAroco.setPrendas(prendasAroco); 
 		
 		
		molde.agregarTipoDePrenda(t1);
		molde.agregarTipoDePrenda(t9);
		molde.agregarTipoDePrenda(t14);
		
		repo.agregarMolde(molde);
		
	}
	
	@Test
	public void listaDeMoldeConNiveldeAbrigoIguala8() {
		List<Molde> molde1 = repo.getMoldes().stream().filter(m -> m.elNivelDeAbrigoEsIgualA(8)).collect(Collectors.toList());
		molde1.forEach(m ->{
			System.out.print(m.getNivelDeAbrigo());
		});
		Assert.assertEquals(molde1.size(), 1);
	}
	
	@Test
	public void moldeCandidatoParaNivelDeCalor8VerPuntos() {
		Molde m1 = repo.devolverMoldeCandidato(8);
		
		Assert.assertEquals(m1.getNivelDeAbrigo(), 8);
	}
	
	@Test
	public void generarMoldeDeNivelDeCalor23() {
		Molde molde1 = repo.generoMoldeSegun(23);
		
		Assert.assertEquals(molde1.getTiposDePrendas().size(), 4);
		Assert.assertEquals(molde1.getNivelDeAbrigo(), 23);
	}
	
	@Test
	public void listaDeMoldeConNiveldeAbrigoIguala23() {
		List<Molde> molde1 = repo.getMoldes().stream().filter(m -> m.elNivelDeAbrigoEsIgualA(23)).collect(Collectors.toList());
		
		Assert.assertEquals(molde1.size(), 1);
	}
	
	@Test(expected=TipoDePrendaSinCombinableSegunNivelCapa.class)
	public void generarMoldeDeNivelDeCalor40() {
		repo.generoMoldeSegun(40);
	
	}
	
	@Test
	public void generoAtuendoParaNivelDeCalor8() {
		Atuendo atuendo = this.guardarropaAroco.elegirAtuendoSegunProtocolo(Protocolo.Informal, 8, new Sensibilidad());
		
		Assert.assertEquals(atuendo.getPrendas().size(), 3);
	}
	
	@Test
	public void guardarropaTienePrendas() {
		
		
		Assert.assertEquals(guardarropaAroco.getPrendas().size(), 9);
	}
	
	

}
