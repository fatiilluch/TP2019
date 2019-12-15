package model.clima2;

import java.util.ArrayList;

public class ResponsePronostico2 {
	
	public ArrayList<List> list;
	
	public double getDia(int elDia){
		return list.get(elDia).getDay();
	}
	
}
