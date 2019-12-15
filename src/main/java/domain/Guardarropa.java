package domain;

import java.security.Guard;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import db.EntidadPersistente;
import domain.events.Protocolo;
import domain.repositorio.RepositorioMolde;


@Entity
@Table(name = "Guardarropa")
public class Guardarropa extends EntidadPersistente
{
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "idGuardarropa", referencedColumnName = "id")
	private List<Prenda> prendas;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "idGuardarropaRechazado", referencedColumnName = "id")
	private List<Atuendo> atuendosRechazados;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "idGuardarropaAceptado", referencedColumnName = "id")
	private List<Atuendo> atuendosAceptados;
	
	@Column(name = "nombre")
	private String nombreGuardarropa;
	
	@Transient
	private Molde molde;
	
		
	
	public Guardarropa() {
		super();
		this.inicializarPrenda();
		this.atuendosAceptados = new ArrayList<Atuendo>();
		this.atuendosRechazados = new ArrayList<Atuendo>();
		this.molde = new Molde();
	}

	public Guardarropa(String nombre) {
		this.inicializarPrenda();
		this.atuendosAceptados = new ArrayList<Atuendo>();
		this.atuendosRechazados = new ArrayList<Atuendo>();
		this.nombreGuardarropa = nombre;
		this.molde = new Molde();
	}
	
	public String getNombreGuardarropa() {
		return nombreGuardarropa;
	}

	public void setNombreGuardarropa(String nombreGuardarropa) {
		this.nombreGuardarropa = nombreGuardarropa;
	}
	private void inicializarPrenda() {
		this.prendas = new ArrayList<Prenda>();
	}

	public List<Prenda> getPrendas() {
		return prendas;
	}

	public void setPrendas(List<Prenda> prendas) {
		this.prendas = prendas;
	}

	public void agregarPrenda(Prenda nuevaPrenda) {
		this.prendas.add(nuevaPrenda);
	}

	public int cantidadDePrendas() {
		return this.prendas.size();
	}

	public List<Prenda> limitarPrendas(int limiteDePrendas){
		List<Prenda> otrasPrendas = new ArrayList<>();
	    if (prendas.size() < limiteDePrendas)
		{
		    otrasPrendas = prendas;
		}
	    else
        {
            otrasPrendas = this.prendas.stream().limit(limiteDePrendas).collect(Collectors.toList());
        }
		return otrasPrendas;
	}

	//filtra la lista de prendas por una categoria determinada
	public List<Prenda> filtrarPorCategoria(Categoria tipoCategoria) {
		return this.prendas.stream().filter(prenda -> prenda.sosDeCategoria(tipoCategoria)).collect(Collectors.toList());
	}

	public List<Prenda> filtrarPorProtocolo(Protocolo unProtocolo,int limiteDePrendas) {
		List<Prenda> prendasLimitadas = this.limitarPrendas(limiteDePrendas);
		return prendasLimitadas.stream().
			   filter(prenda -> prenda.estasAsociadoA(unProtocolo)).
			   collect(Collectors.toList());
	}
	//Obtengo un indice aleatorio de la lista que depende de la categoria
	public int indiceAleatorio(Categoria tipoCategoria) {
		Random aleatorio = new Random(System.currentTimeMillis());
		return aleatorio.nextInt(Math.abs(this.filtrarPorCategoria(tipoCategoria).size()));
	}
	
	//indice aleatorio seg�n el inivel de capa con una lista limitada
	public int indiceAleatorioSegunElNivel(List<Prenda> prendasLimitadas, int nivelDeCapa) {
		Random aleatorio = new Random(System.currentTimeMillis());
		return aleatorio.nextInt(Math.abs(this.filtrarPorNivelDeCapa(prendasLimitadas, nivelDeCapa).size()));
	}
	
	//Devuelve un lista de prendas segun un nivel de capa
	private List<Prenda> filtrarPorNivelDeCapa(List<Prenda> prendasLimitadas, int nivelDeCapa) {
		return prendasLimitadas.stream().
				filter(prenda -> prenda.sosDeNivel(nivelDeCapa)).
				collect(Collectors.toList());
	}
	
	public Atuendo elegirAtuendoSegunProtocolo(Protocolo unProtocolo, int nivelDeCalor,Sensibilidad sensibilidadUsuario) {
		Atuendo atuendo;
		Molde molde = this.molde.devolverMolde(nivelDeCalor);

		final List<Prenda> prendasParaAtuendo = rellenarMolde(molde);
		List<Prenda> listaFinal= new ArrayList<Prenda>();
		molde.getTiposDePrendas().forEach(tipoDePrenda -> getRandomIgualTipo(prendasParaAtuendo,tipoDePrenda,listaFinal));

		atuendo = new Atuendo(listaFinal,unProtocolo);
		
		return atuendo;
	}

	public void getRandomIgualTipo(List<Prenda> listaPrendas,TipoDePrenda tipo, List<Prenda> listaFinal){
		List<Prenda> prendasFiltradas = listaPrendas.stream().filter(prenda -> prenda.sosDeTipoDe(tipo)).collect(Collectors.toList());
		Random aleatorio = new Random(System.currentTimeMillis());
		int indiceAleatorio = aleatorio.nextInt(Math.abs(prendasFiltradas.size()));
		listaFinal.add(prendasFiltradas.get(indiceAleatorio));
	}


	private List<Prenda> rellenarMolde(Molde molde) {
		List<Prenda> prendasDisponibles = new ArrayList<Prenda>();
		molde.getTiposDePrendas().forEach(tipo -> {
			prendasDisponibles.add(this.prendaSegunTipo(tipo));
		});
		
		return prendasDisponibles;
	}
	
	private Prenda prendaSegunTipo(TipoDePrenda tipo) {
		int indice = this.indicePrendaAleatoriaSegun(tipo);
		return this.filtrarPorTipoDePrenda(tipo).get(indice);
	}
	
	private int indicePrendaAleatoriaSegun(TipoDePrenda tipo) {
		Random aleatorio = new Random(System.currentTimeMillis());
		return aleatorio.nextInt(Math.abs(this.filtrarPorTipoDePrenda(tipo).size()));
	}

	private List<Prenda> filtrarPorTipoDePrenda(TipoDePrenda tipo) {
		return this.getPrendas().stream().filter(prenda -> prenda.sosDeTipoDe(tipo)).collect(Collectors.toList());
	}

	private boolean tieneTipoDePrendaSegunMolde(Molde molde) {
		List<TipoDePrenda> tipos = molde.getTiposDePrendas();
		
		return tipos.containsAll(this.tiposDePrendaDelGuardarropa());	
	}

	private List<TipoDePrenda> tiposDePrendaDelGuardarropa() {
		List<TipoDePrenda> tiposDelGuardarropa = this.getPrendas().stream().distinct()
				.map(prenda -> prenda.getTipoDePrenda()).collect(Collectors.toList());
		return tiposDelGuardarropa;
	}

	public boolean tienePrenda(int idPrenda){
		return this.getPrendas().stream().map(prenda -> prenda.getId()).collect(Collectors.toList()).contains(idPrenda);
	}

	public List<Atuendo> getAtuendosRechazados(){
		return this.atuendosRechazados;
	}

	public void agregarAListaRechazado(Atuendo atuendo)
	{
		sacarDeAceptado(atuendo);
		this.atuendosRechazados.add(atuendo);
	}
	
	public void elimAtuendoRechazado(Atuendo unAtuendo){
		this.atuendosRechazados.remove(unAtuendo);
	}
	
	public List<Atuendo> getAtuendosAceptados()
	{
		return this.atuendosAceptados;
	}
	
	public void agregarAListaAceptado(Atuendo atuendo) 
	{
		sacarDeRechazado(atuendo);
		this.atuendosAceptados.add(atuendo);
	}

	public void elimAtuendorAceptado(Atuendo atuendo) 
	{
		this.atuendosAceptados.remove(atuendo);
	}

	public boolean noTieneRechazado(Atuendo atuendoAEvaluar){
		return !atuendosRechazados.stream().anyMatch(atuendo -> atuendo.equalsMio(atuendoAEvaluar)) ;
	}

	public void sacarDeRechazado(Atuendo atuendo){
		if(this.getAtuendosRechazados().stream().anyMatch(atuendoRechazado -> atuendoRechazado.equalsMio(atuendo))){
			elimAtuendoRechazado(this.getAtuendosRechazados().stream().filter(atuendoRechazado -> atuendoRechazado.equalsMio(atuendo)).collect(Collectors.toList()).get(0));
		}
	}

	public void sacarDeAceptado(Atuendo atuendo){
		if(this.getAtuendosAceptados().stream().anyMatch(atuendoAceptado -> atuendoAceptado.equalsMio(atuendo))){
			elimAtuendorAceptado(this.getAtuendosAceptados().stream().filter(atuendoAceptado -> atuendoAceptado.equalsMio(atuendo)).collect(Collectors.toList()).get(0));
		}
	}

}