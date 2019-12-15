package domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import db.EntidadPersistente;
import domain.events.Protocolo;
import exception.GuardarropaConCapacidadLimitadaException;
import org.apache.commons.lang3.ObjectUtils;


@Entity
@Table(name = "prenda")
public class Prenda extends EntidadPersistente{
	
	@ManyToOne
	@JoinColumn(name = "idTipoPrenda", referencedColumnName = "id")
	private TipoDePrenda tipoPrenda;
	
	@Enumerated(value = EnumType.STRING)
	@Column(name = "tipoTela")
	private Tela tipoTela;

	@Enumerated(value = EnumType.STRING)
	@Column(name = "color")
	@ElementCollection(targetClass=Color.class)
	private List <Color> colores;

	@Enumerated(value = EnumType.STRING)
	@Column(name = "tipoProtocolo")
	private Protocolo tipoProtocolo;
	
	@OneToOne(cascade= CascadeType.ALL, fetch = FetchType.LAZY)
	private Imagen avatar;

	//Con la opcion de que no tenga color secundario
	public Prenda(TipoDePrenda tipoPrenda, Tela tipoTela, Color colorPrimario, Protocolo unProtocolo, Imagen avatar) {
		this.tipoPrenda = tipoPrenda;
		if(!prendaPermiteTela(tipoTela,tipoPrenda)){
			System.out.println("ERROR, NO PUEDO CREAR UNA PRENDA CON ESA COMBINACION TELA Y TIPO");
		}
		this.tipoTela = tipoTela;
		this.colores = new ArrayList <Color> ();
		colores.add(colorPrimario);
		this.tipoProtocolo = unProtocolo;
		if(avatar != null) agregarAvatar(avatar);
	}

	public Prenda(TipoDePrenda tipoPrenda, Tela tipoTela, Color colorPrimario, Color colorSecundario, Protocolo unProtocolo, Imagen avatar) {
			this.tipoPrenda = tipoPrenda;
			if(!prendaPermiteTela(tipoTela,tipoPrenda)){
				System.out.println("ERROR, NO PUEDO CREAR UNA PRENDA CON ESA COMBINACION TELA Y TIPO");
			}
			this.tipoTela = tipoTela;
			this.colores = new ArrayList <Color> ();
			colores.add(colorPrimario);
			colores.add(colorSecundario);
			this.tipoProtocolo = unProtocolo;
//			if(avatar != null) agregarAvatar(avatar);
			if(avatar != null) avatar.cargarImagen();
	}

	public Prenda() {}

	//1.a
	public TipoDePrenda getTipoDePrenda() {
		return this.tipoPrenda;
	}
	

	public void setTipoPrenda(TipoDePrenda tipoPrenda) {
		this.tipoPrenda = tipoPrenda;
	}

	public void setTipoTela(Tela tipoTela) {
		this.tipoTela = tipoTela;
	}

	public void setColores(List<Color> colores) {
		this.colores = colores;
	}

	public void setTipoProtocolo(Protocolo tipoProtocolo) {
		this.tipoProtocolo = tipoProtocolo;
	}

	//1.b
	public Categoria getCategoria() {
		return tipoPrenda.getCategoria();
	}

	//1.c
	public Tela getTipoTela() {
		return tipoTela;
	}

	public Color getColorPrincipal(){return colores.get(0);}

	public Color getColorSecundario(){
		if(colores.size()>1){ return colores.get(1);} else return null;
	}

	public String getImagenParaMostrar(){
		if (avatar ==null) {return this.tipoPrenda.getImagenPredeterminada();}
		else return getAvatarDireccion();
	}

	public String getAvatarDireccion(){
		if(avatar != null){
			System.out.println(avatar.getPathGenerado());
			return avatar.getPathGenerado();
		}
		else return null;
	}

	public Protocolo getProtocolo() {
		return this.tipoProtocolo;
	}

	public int getPuntosDeCalor() {
		return this.tipoPrenda.getNivelDeAbrigo();
	}

	public int getNivelDeCapaTipoDePrenda(){
		return this.tipoPrenda.getNivelDeCapa();
	}
	
	private void agregarAvatar(Imagen avatar) {
		avatar.cargarImagen();
	}

	public Boolean sosDeCategoria(Categoria categoria) {
		return this.getCategoria().equals(categoria);
	}
	
	public Boolean estasAsociadoA(Protocolo protocolo) {
		return this.getProtocolo().equals(protocolo);
	}
	
	public Boolean sosDeNivel(int nivel) {
		return this.getNivelDeCapaTipoDePrenda() == nivel;
	}

	public boolean sosDeTipoDe(TipoDePrenda tipo) {
		return this.getTipoDePrenda().equals(tipo);
	}
	
	public boolean prendaPermiteTela(Tela tipoTela,TipoDePrenda tipoPrenda){
		return tipoPrenda.permiteTela(tipoTela);
	}
}