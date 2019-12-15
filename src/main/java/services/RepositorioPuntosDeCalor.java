package services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

public class RepositorioPuntosDeCalor {

	private List<PuntosDeCalor> puntosDeCalor;
	private static RepositorioPuntosDeCalor instance = null;

	 
	public static RepositorioPuntosDeCalor getInstance(){
        if(instance == null)
            instance = new RepositorioPuntosDeCalor();
        return instance;
    }
	
	
	
	public RepositorioPuntosDeCalor() {
		this.puntosDeCalor  = new ArrayList<PuntosDeCalor>();
		this.init();
	}



	public void init() {
		PuntosDeCalor p1 = new PuntosDeCalor(40, 4);
		PuntosDeCalor p2 = new PuntosDeCalor(35, 6);
		PuntosDeCalor p3 = new PuntosDeCalor(30, 8);
		PuntosDeCalor p4 = new PuntosDeCalor(25, 10);
		PuntosDeCalor p5 = new PuntosDeCalor(20, 12);
		PuntosDeCalor p6 = new PuntosDeCalor(15, 14);
		
		this.add(p1,p2,p3,p4,p5,p6);
		
	}
	
	 public void add(PuntosDeCalor ... puntos){
	        Collections.addAll(this.puntosDeCalor, puntos);
	 }
	 
	 public int calcularMasProximo(double temperatura) {
		double puntoMin =this.puntosDeCalor.stream().
		 mapToDouble(punto -> punto.diferenciaSegunTemperatura(temperatura)).min().orElseThrow(NoSuchElementException::new);
		
		return this.puntosDeCalor.stream().
				filter(punto->punto.diferenciaSegunTemperatura(temperatura) == puntoMin).
				collect(Collectors.toList()).get(0).getPuntosDeCalor();

	 }
	
	
}
