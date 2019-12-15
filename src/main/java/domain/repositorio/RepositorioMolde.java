package domain.repositorio;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import domain.Molde;
import domain.TipoDePrenda;
import exception.TipoDePrendaSinCombinableSegunNivelCapa;
import domain.Categoria;



public class RepositorioMolde {

	private List<Molde> moldes;
	private static RepositorioMolde instance;
	private RepositorioTipoDePrenda repoTipoDePrendas;
	
	public static RepositorioMolde getInstance(){
        if(instance == null)
            instance = new RepositorioMolde();
        return instance;
    }

	public RepositorioMolde() {
		this.moldes = new ArrayList<Molde>();
		
	}
	
	
	public List<Molde> getMoldes() {
		return moldes;
	}

	public void agregarMolde(Molde molde) {
		moldes.add(molde);
	}
	
	public void agregarTiposDePrendasA(Molde unMolde , List<TipoDePrenda> tipos ) {
		for(int index = 0 ; index < tipos.size() ; index++) {
			unMolde.agregarTipoDePrenda(tipos.get(index));
		}
	}
	public List<Molde> devolverMoldesMenoresIguales(int nivelDeAbrigo){
		return this.moldes.stream().
				filter(molde -> molde.esMenorIgualA(nivelDeAbrigo)).
				collect(Collectors.toList());
	}
	public Molde devolverMoldeCandidato(int nivelDeAbrigo) {
		return this.devolverMoldesMenoresIguales(nivelDeAbrigo).stream().
				sorted(Comparator.comparing(Molde::getNivelDeAbrigo)).
				collect(Collectors.toList()).get(0);			
	}
	
	//metodos para encontrar el molde Candidato tomando en cuenta la sensibilidad del usuario//
	public List<Molde> getMoldeSegun(int nivelDeCalor , Categoria zonaSensible){
		
		return this.moldes.stream()
			.filter(molde -> molde.abriga(nivelDeCalor, zonaSensible))
			.collect(Collectors.toList());
	}
	///// agregar metodo y reemplazarlo por el anterior///////////////////////////////
	public Molde getMoldeCandidato(int nivelDeCalor, Categoria zonaSensible){
		List<Molde> moldesDisponibles  =  this.getMoldeSegun(nivelDeCalor, zonaSensible);
		
		if(moldesDisponibles.isEmpty()) {
				return this.generarMoldeCon(nivelDeCalor,zonaSensible);
		}		
		return this.obtenerMoldeRandom(moldesDisponibles);
	}
	////////////////////////////////////////////////////////////////////////////////

	public Molde generarMoldeCon(int nivelDeAbrigo , Categoria zonaSensible) {
		
		Molde moldeCandidato = this.encontrarPosibleMoldeBaseSegun(nivelDeAbrigo);
		this.chequeoDistribucionDeCalor(nivelDeAbrigo, zonaSensible, moldeCandidato);
		this.chequeoNivelDeAbrigoMolde(nivelDeAbrigo,moldeCandidato);
		this.agregarMolde(moldeCandidato);
	
		return moldeCandidato;
	}
	
	public Molde encontrarPosibleMoldeBaseSegun(int nivelDeAbrigo) {
		return this.devolverMoldesMenoresIguales(nivelDeAbrigo).stream().
				sorted(Comparator.comparing(Molde::getNivelDeAbrigo)).
				collect(Collectors.toList()).get(0);			
	}
	
	public boolean capaMaximaSuperada(int capa) {
		return this.repoTipoDePrendas.getCapaMaxima() < capa;
	}
	public int getCapaMaxima() {
		return this.repoTipoDePrendas.getCapaMaxima();
	}
	private boolean esCombinable(TipoDePrenda tipoObjetivo , Molde molde) {
		return molde.getTiposDePrendas().stream().allMatch(tipo -> tipo.combinaCon(tipoObjetivo));
	}
	private TipoDePrenda obtenerTipoDePrendaSegunNivelCapa( Categoria categoria ,int nivelDeCapa, Molde moldeAGenerar) {
		List<TipoDePrenda> tipos = this.repoTipoDePrendas.getTiposDePrendas().stream().
				filter(tipo -> tipo.esIgualNivelDeCapaA(nivelDeCapa) && tipo.sosDeCategoria(categoria) && this.esCombinable(tipo,moldeAGenerar)).
				collect(Collectors.toList());
		return this.obtenerTipoRandom(tipos);
	}
	
	private TipoDePrenda obtenerTipoDePrendaSegun(Categoria categoria, int nivelDeAbrigo, Molde moldeAGenerar) {
		List<TipoDePrenda> tipos = this.repoTipoDePrendas.getTiposDePrendas().stream().
				filter(tipo -> tipo.sosDeCategoria(categoria) && tipo.nivelDeAbrigoIgualA(nivelDeAbrigo) && this.esCombinable(tipo,moldeAGenerar)).
				collect(Collectors.toList());
		return this.obtenerTipoRandom(tipos);
	}

	private int indiceRandom(int size) {
		return (int) (Math.random() * size);
	}
	
	private Molde obtenerMoldeRandom(List<Molde> moldes) {
		return moldes.get(this.indiceRandom(moldes.size()));
	}
	private TipoDePrenda obtenerTipoRandom(List<TipoDePrenda> tipos) {
		return tipos.get(this.indiceRandom(tipos.size()));
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//metodos de chequeo de sensibilidad para generar el molde adecuado//////////////////////////////////////////
	
	public void chequeoDistribucionDeCalor(int nivelDeAbrigo , Categoria zonaSensible , Molde moldeCandidato) {
		int capa = moldeCandidato.obtenerCapaMaxima();
		
		TipoDePrenda tipo;
		while(!moldeCandidato.esZonaMasAbrigada(zonaSensible)) {
			// crea un nuevo molde con una capa mas
			int nivelCalor = moldeCandidato.getNivelDeAbrigoSegun(zonaSensible);
			TipoDePrenda tipoAEliminar;
			
			switch(zonaSensible) {
				case Superior:
					//agraga una capa mas en la zona superior para que cumpla la sensibilidad superior, teniendo en cuenta si se supera el limite de capas existente en el repositorio de Prendas				
					
					if(this.capaMaximaSuperada(capa+1)) {
						
						tipoAEliminar = moldeCandidato.getCapaSuperior(capa);
						tipo = this.obtenerTipoDePrendaSegun(zonaSensible, tipoAEliminar.getNivelDeAbrigo() + 1, moldeCandidato);
						moldeCandidato.eliminarTipoDePrenda(tipoAEliminar);
					}else {	
						tipo = this.obtenerTipoDePrendaSegunNivelCapa(Categoria.Superior,capa +1, moldeCandidato);
					}
					moldeCandidato.agregarTipoDePrenda(tipo);
					break;
				case Inferior:
					// en este caso reemplaza el tipo de prenda inferior por uno con mas pts de calor para que cumpla la sensibilidad inferior
					tipoAEliminar = moldeCandidato.getTipoDePrendaSegun(Categoria.Inferior);
					tipo = this.obtenerTipoDePrendaSegun(zonaSensible,nivelCalor+1, moldeCandidato);
					moldeCandidato.eliminarTipoDePrenda(tipoAEliminar);
					moldeCandidato.agregarTipoDePrenda(tipo);
					
					// generar excepcion  
					break;
				default:
					break;
			}
			
		}
	}
	
	//
	private void chequeoNivelDeAbrigoMolde(int nivelDeAbrigo , Molde moldeCandidato) {
	
		if(!moldeCandidato.elNivelDeAbrigoEsIgualA(nivelDeAbrigo)) {
			//modifica el molde candidato agregando nuevos tipos de prendas para alcanzar el nivel de abrigo esperado
			int ptsDeCalorFaltantes = nivelDeAbrigo - moldeCandidato.getNivelDeAbrigo();
			int capaMax =moldeCandidato.obtenerCapaMaxima();
			if(moldeCandidato.esZonaMasAbrigada(Categoria.Inferior) || this.capaMaximaSuperada(capaMax+1)) {
				//busco agregar accesorios o en su defecto cambiar el calzado para llegar al cubrir el nivel de pts de calor deseado
				// no cambio la prenda inferior ya que si lo hago cambio la sensibilidad del molde
				this.agregaAccesorioOCazadoA(moldeCandidato, ptsDeCalorFaltantes);
			}else {
				// se agrega una capa mas de ropa de prenda superior
				this.agregaCapaAMolde(moldeCandidato, ptsDeCalorFaltantes);
			}
		}
	}
	private void agregaAccesorioOCazadoA(Molde unMolde,int ptsDeCalorObjetivo) {
		
		TipoDePrenda tipo;
		
		tipo = this.obtenerTipoDePrendaSegun(Categoria.Accesorio, ptsDeCalorObjetivo, unMolde);
		unMolde.agregarTipoDePrenda(tipo);
		ptsDeCalorObjetivo -= tipo.getNivelDeAbrigo();
		
		if(ptsDeCalorObjetivo > 0 ) {
			tipo = this.obtenerTipoDePrendaSegun(Categoria.Calzado, ptsDeCalorObjetivo, unMolde);
			unMolde.eliminarTipoDePrenda(unMolde.getTipoDePrendaSegun(Categoria.Calzado));
			unMolde.agregarTipoDePrenda(tipo);
		}
		
	}
	private void agregaCapaAMolde(Molde unMolde,int ptsDeCalorObjetivo) {
		int capaMaxima = this.getCapaMaxima();
		TipoDePrenda tipo;
		
		while (ptsDeCalorObjetivo > 0 ) {
			tipo = this.obtenerTipoDePrendaSegunNivelCapa(Categoria.Superior,capaMaxima , unMolde);
		
			if(tipo.getNivelDeAbrigo() <= ptsDeCalorObjetivo && !unMolde.tieneTipoDePrenda(tipo)) {
				unMolde.agregarTipoDePrenda(tipo);
				ptsDeCalorObjetivo -= tipo.getNivelDeAbrigo();
			}
			capaMaxima--;
		}
		
	}
	
	/////////consideraciones
	////primero busco molde que cubra los puntos de calor considero las capas
	public Molde generoMoldeSegun(int nivelCalor) {
		Molde molde = new Molde();
		molde = devolverMoldeCandidato(nivelCalor);
		
		if(molde.estaComprendido(nivelCalor)) {
			cubrirNivelDeAbrigo(molde, nivelCalor);
		}
		
		return molde;
	}
	
	private void cubrirNivelDeAbrigo(Molde molde,int nivelCalor) {
		
		int bandera = 0;
		
		while(bandera == 0) {
			agregarUnaCapa(molde);
			
			if(molde.elNivelDeAbrigoEsIgualA(nivelCalor) || molde.getNivelDeAbrigo() > nivelCalor) {
				bandera = 1;
			}
		}
	}
	
	private void agregarUnaCapa(Molde molde) {
		int ultimaCapa = molde.obtenerCapaMaxima();
		
		TipoDePrenda tipo = buscarTipoDePrendaConCapa(molde, ultimaCapa);
		molde.agregarTipoDePrenda(tipo);
	}
	
	private TipoDePrenda buscarTipoDePrendaConCapa(Molde molde, int ultimaCapa){
		TipoDePrenda tipo = molde.tipoDePrendaSuperiorConCapaMax();
		
		// podemos verlo como teorema de transitividad (a,b) && (b,c) => (a,c) porque se descarta los tipos del mismo nivel
		TipoDePrenda tipoNuevo = tipo.tipoCombinableConCapa(ultimaCapa+1);
		return tipoNuevo;
	}
	
	///segundo al molde que cubra los puntos de calor le agrego la sensibilidad del usuario
	
	
}
