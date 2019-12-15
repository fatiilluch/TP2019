package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.*;

import db.EntidadPersistente;
import domain.repositorio.RepositorioMolde;
import repositories.factories.FactoryRepositorioMolde;

@Entity
@Table(name = "Molde")
public class Molde extends EntidadPersistente{
	
	
	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "idMolde", referencedColumnName = "id")
	private List<TipoDePrenda> tipoDePrendas;
	
	@Transient
	private repositories.RepositorioMolde repoMolde;
	
	
	public Molde() {
		this.tipoDePrendas = new ArrayList<TipoDePrenda>();
		this.repoMolde = FactoryRepositorioMolde.get();
	}
	
	public List<TipoDePrenda> getTiposDePrendas(){
		return this.tipoDePrendas;
	}

	public void setTiposDePrenda(List<TipoDePrenda> prendas){ this.tipoDePrendas.addAll(prendas);}
	
	public boolean esZonaMasAbrigada(Categoria  zona) {
		return this.zonaMasAbrigada().equals(zona);
	}
	public int obtenerCapaMaxima() {
		return (int) this.getTiposDePrendas().stream().
				mapToInt(tipo -> tipo.getNivelDeCapa()).max().getAsInt();
	}
	
	public TipoDePrenda tipoDePrendaSuperiorConCapaMax() {
		TipoDePrenda tipo = new TipoDePrenda();
		int nivelDeCapa = obtenerCapaMaxima();
		
		tipo = getTiposDePrendaSegun(Categoria.Superior).stream().
				filter(t -> t.esIgualNivelDeCapaA(nivelDeCapa)).
				collect(Collectors.toList()).get(0);
		
		return tipo;
	}
	
	
	public Boolean esMenorIgualA(int nivelDeAbrigo) {
		return this.getNivelDeAbrigo() <= nivelDeAbrigo;
	}
	
	public Boolean estaComprendido(int nivelDeAbrigo) {
		return this.getNivelDeAbrigo() < (nivelDeAbrigo-5);
	}
	
	public List<TipoDePrenda> getTiposDePrendaSegun(Categoria categoria) {
		return this.getTiposDePrendas().stream().
				filter(tipo -> tipo.sosDeCategoria(categoria))
				.collect(Collectors.toList());
	}
	
	public TipoDePrenda getTipoDePrendaSegun(Categoria categoria) {
		return this.getTiposDePrendas().stream().
				filter(tipo -> tipo.sosDeCategoria(categoria))
				.collect(Collectors.toList()).get(0);
	}
	
	public TipoDePrenda getCapaSuperior(int nivelCapaSuperior) {
		return this.getTiposDePrendas().stream()
				.filter(tipo -> tipo.getNivelDeCapa() == nivelCapaSuperior)
				.collect(Collectors.toList()).get(0);
	}
	
	public void eliminarTipoDePrenda(TipoDePrenda tipoBuscado) {
		this.tipoDePrendas.removeIf(tipo -> tipo.equals(tipoBuscado));
	}
	public int getNivelDeAbrigo() {
		
		return this.getTiposDePrendas().stream().
				mapToInt(tipo->tipo.getNivelDeAbrigo()).sum();
	}
	
	public Boolean elNivelDeAbrigoEsIgualA(int nivelDeAbrigo) {
		return this.getNivelDeAbrigo() == nivelDeAbrigo;
	}
	
	public void agregarTipoDePrenda(TipoDePrenda tipo) {
		this.tipoDePrendas.add(tipo);
		
	}
	public boolean tieneTipoDePrenda(TipoDePrenda tipoObj) {
		return this.tipoDePrendas.stream().anyMatch(tipo -> tipo.esIgualNivelDeCapaA(tipoObj.getNivelDeCapa()));
	}

	public int getNivelDeAbrigoSegun(Categoria categoria) {
		return this.getTiposDePrendas().stream().filter(tipo -> tipo.sosDeCategoria(categoria)).mapToInt(tipo->tipo.getNivelDeAbrigo()).sum();
	}
	public Categoria zonaMasAbrigada() {
		if(this.getNivelDeAbrigoSegun(Categoria.Superior) < this.getNivelDeAbrigoSegun(Categoria.Inferior)) {
			return Categoria.Inferior;
		}else {
			return Categoria.Superior;
		}
	}
	
	
	public boolean abriga(int nivelDeAbrigo , Categoria zonaSensible) {
		return this.elNivelDeAbrigoEsIgualA(nivelDeAbrigo) && this.esZonaMasAbrigada(zonaSensible);
	}
	
	public Molde devolverMolde(int nivelCalor) {
		return this.repoMolde.generoMoldeSegun(nivelCalor);
	}
	

}
