package domain.repositorio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import domain.Categoria;
import domain.Tela;
import domain.TipoDePrenda;


public class RepositorioTipoDePrenda {
	private List<TipoDePrenda> tiposDePrendas;
	private static RepositorioTipoDePrenda instance = null;
	
	public static RepositorioTipoDePrenda getIntance() {
		if(instance == null)
			instance = new RepositorioTipoDePrenda();
		
		return instance;
	}

	public RepositorioTipoDePrenda() {
		this.tiposDePrendas = new ArrayList<TipoDePrenda>();
		this.init();
	}
	
	public void agregarTipoDePrenda(TipoDePrenda tipoDePrenda) {
		this.tiposDePrendas.add(tipoDePrenda);
	}
	
	private void init() {
		
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

		combinacionesRemera.add(t2);
		combinacionesRemera.add(t3);
		combinacionesRemera.add(t4);
		
		combinacionesSueter.add(t1);
		combinacionesSueter.add(t3);
		combinacionesSueter.add(t4);
		
		combinacionesCampera.add(t1);
		combinacionesCampera.add(t3);
		combinacionesCampera.add(t4);
		combinacionesCampera.add(t5);
		
		combinacionesPantalon.add(t1);
		combinacionesPantalon.add(t2);
		combinacionesPantalon.add(t3);
		
		combinacionesShort.add(t1);
		combinacionesShort.add(t3);
		
		combinacionesZapatilla.add(t1);
		combinacionesZapatilla.add(t2);
		combinacionesZapatilla.add(t3);
		combinacionesZapatilla.add(t4);
		combinacionesZapatilla.add(t5);
		combinacionesZapatilla.add(t6);
		combinacionesZapatilla.add(t7);
		
		combinacionesZapato.add(t4);
		
		this.add(t1,t2,t3,t4,t5,t6,t7);
	}
	
	public void add(TipoDePrenda ... tipoDePrendas ) {
		Collections.addAll(this.tiposDePrendas, tipoDePrendas);
	}

	public List<TipoDePrenda> getTiposDePrendas() {
		return tiposDePrendas;
	}
	
	public int getCapaMaxima() {
		return (int)this.tiposDePrendas.stream().mapToDouble(tipo->tipo.getNivelDeCapa()).max().getAsDouble();
	}
	
}
