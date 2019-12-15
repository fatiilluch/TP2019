package services;

import model.clima2.ResponsePronostico2;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitClimaService2 {
	@GET("/data/2.5/forecast?units=metric&APPID=fc6b5e05ab7d5c4d4f7425818f0f94f4")
	Call<ResponsePronostico2> getClima2(@Query(value = "id", encoded = true) String codCiudad,@Query("cnt") int dias);	
}
