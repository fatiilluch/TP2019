package domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import db.EntidadPersistente;
import domain.Categoria;

@Entity
@Table(name = "sensibilidad")
public class Sensibilidad extends EntidadPersistente{
	
	@Column(name = "zonaSuperior")
	private double zonaSuperior;
	
	@Column(name = "zonaInferior")
	private double zonaInferior;
	
	public Sensibilidad(double zonaSup , double zonaInf) {
		this.zonaSuperior = zonaSup;
		this.zonaInferior = zonaInf;
	}
	
	public Sensibilidad() {}
	
	public void aumentarSensibilidad(Categoria zona , double val) {
		switch(zona){
			case Superior:
				zonaSuperior +=val;
				break;
			default :
				zonaInferior +=val;
		}
	}
	
	public void disminuirSensibilidad(Categoria zona , double val) {
		switch(zona){
			case Superior:
				zonaSuperior -=val;
				break;
			default :
				zonaInferior -=val;
		}
	}
	public double superior() {
		return zonaSuperior;
	}
	public double inferior() {
		return zonaInferior;
	}
	
	public boolean esFriolento(){
		return zonaInferior>0.5 && zonaSuperior>0.5;
	}
	
	public boolean esCaluroso(){
		return zonaInferior<0.5 && zonaSuperior<0.5;
	}
	
	public Categoria zonaMasSensible() {
		if(this.zonaInferior > this.zonaSuperior) {
			return Categoria.Inferior;
		}
		return Categoria.Superior;
	}
	
}
