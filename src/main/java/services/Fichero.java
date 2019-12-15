package services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Fichero {
	
	private static  Properties propiedades = new Properties();
	private static  File ruta = new File("src\\main\\java\\archivo.properties");
	
	
	/*Cargamos el archivo desde la ruta especificada*/
	public void cargoArchivo(){
		try {
			propiedades.load(new FileInputStream(ruta));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*Obtenemos los parametros definidos en el archivo*/
	public static String obtenerParametros(String unParametro){
		return propiedades.getProperty(unParametro);
	}
	
	
	//Utilizar este metodo cada vez que quiera usar los datos del archivo config
	public static void generoArchivo(){
		Fichero fichero = new Fichero();
		fichero.cargoArchivo();
	}



}