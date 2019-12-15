package domain;

import java.util.Date;

import services.ElClima;

public class ClimaTest {

	
	public static void main(String[] args){
		
		java.util.Date fecha = new Date();
		ElClima unClima = ElClima.getInstance();
		
		System.out.println("Temperatura del: " + fecha);
		System.out.println(unClima.getTemperatura(0));
		System.out.println("Temperatura de mañana" );
		System.out.println(unClima.getTemperatura(1));
		System.out.println("Temperatura de pasado mañana");
		System.out.println(unClima.getTemperatura(2));
		System.out.println("Temperatura de pasado pasado mañana");
		System.out.println(unClima.getTemperatura(3));

		
	}
	
}
