package repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import domain.Color;

public class RepositorioColores {
	private static RepositorioColores instance;
	public List<Color> colores= new ArrayList<Color>();
	
	public static RepositorioColores getInstance() {
        if(instance == null){
            instance = new RepositorioColores();
        }
        return instance;
    }
	
	private RepositorioColores() {
		this.init();
	}
	
	private void init() {
		
		colores.add(Color.AMARILLO);
		colores.add(Color.AZUL);
		colores.add(Color.BLANCO);
		colores.add(Color.BORDO);
		colores.add(Color.CELESTE);
		colores.add(Color.GRIS);
		colores.add(Color.NEGRO);
		colores.add(Color.ROJO);
		colores.add(Color.ROSA);
		colores.add(Color.VERDE);	
	}
	
	public List<String> coloresString(){
		List<String> coloresString= new ArrayList<String>();
		
		coloresString = this.colores.stream().
				map(c->c.toString()).collect(Collectors.toList());
		
		return coloresString;
	}
	
	public List<Color> getColores() {
		return this.colores;
	}
}
