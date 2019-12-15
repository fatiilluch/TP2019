package services;

import java.util.ArrayList;

import model.clima1.Forecast;
import model.clima1.ResponsePronostico;
import model.clima2.List;
import model.clima2.ResponsePronostico2;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ElClima {
	
	public Call<ResponsePronostico> call1;
	public Call<ResponsePronostico2> call2;
	public Retrofit retrofit;
	public int cantidadDeDias = 4; //Dias que quiero traer de la api

	private static ElClima instance = null;

	public static ElClima getInstance(){
		if(instance == null)
			instance = new ElClima();
		return instance;
	}
	
	//Cargo los datos del archivo config
	
	public String datoArchivo(String Dato){
		Fichero.generoArchivo();
		return Fichero.obtenerParametros(Dato);
	}
		
	public Retrofit getRetrofit(String baseURL){
		return new Retrofit.Builder()
				.baseUrl(this.datoArchivo(baseURL))
				.addConverterFactory(GsonConverterFactory.create())
				.build();
	}
	
	public void getCall1(String codigoBA){
		retrofit = this.getRetrofit("baseURL");
		RetrofitClimaService service = retrofit.create(RetrofitClimaService.class); 
	    call1 =  service.getClima((this.datoArchivo(codigoBA)),cantidadDeDias);
	}
	
	public void getCall2(String codigoBA){
		retrofit = this.getRetrofit("baseURL2");
		RetrofitClimaService2 service = retrofit.create(RetrofitClimaService2.class); 
	    call2 =  service.getClima2((this.datoArchivo(codigoBA)),cantidadDeDias);
	}
	
	public double getTemperatura(int unDia){
       try{
    	   //Traigo los datos de la primera API
    	   this.getCall1("ciudadID");
    	   Response<ResponsePronostico> response = call1.execute(); 
    	   //System.out.println(response.toString());
    	   //Chequeo que la api no tire un error HTTP 400, sino llamo a la otra API
    	   if(response.code() < 400){
    		   Forecast pronosticoDia = response.body().forecast;
    	   	   return pronosticoDia.getDia(unDia);
    	   	   
    	   }else{
    	   		this.getCall2("codigoBA");
    	   		Response<ResponsePronostico2> response2 = call2.execute();
    	   		//System.out.println(response2.toString());
    	   		
    	   			if(response2.code() < 400){
    	   					ArrayList<List> pronosticoDia2 = response2.body().list;
    	   					return pronosticoDia2.get(unDia).main.allData();
    	   			}else{
    	   				System.out.println("Ninguna API funciona en este momento");
    	   				return 0;
    	   			}
    	   	}
       }
       catch (Exception ex){
    	   System.out.println("No se pudo conectar con ninguna API");
           System.out.print(ex.getMessage());
           return 0;
       }
       
	}
	
	public int calcularPuntoDeCalor(int unDia) {
		RepositorioPuntosDeCalor repo = RepositorioPuntosDeCalor.getInstance();
		
		return repo.calcularMasProximo(this.getTemperatura(unDia));
	}
	
	
	
	//si la diferencia de temperatura de hoy y la del evento creado es mayor a dos
	//considero una alerta meteorologica
	public Boolean alertaMeteorogolica(Double temperaturaEventoCreado){
		if (Math.abs(this.getTemperatura(0) - temperaturaEventoCreado) > 2)
		return true;
		return false;
	}

}
