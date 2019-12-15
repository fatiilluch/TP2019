package services;


import model.clima1.ResponsePronostico;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitClimaService {

	@GET("/v1/forecast.json?key=26027d706aca4951a46151949190806")
    Call<ResponsePronostico> getClima(@Query(value = "q", encoded = true) String ciudad,@Query("days") int dias);

} 