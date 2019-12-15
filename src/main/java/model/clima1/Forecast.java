package model.clima1;

import java.util.ArrayList;

public class Forecast {
	
	public ArrayList<ForecastDay> forecastday;
	
	public double getDia(int elDia){
		return forecastday.get(elDia).getDay();
	}
}
