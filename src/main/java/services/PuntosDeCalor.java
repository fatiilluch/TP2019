package services;

public class PuntosDeCalor {
	private double temperatura;
	private int puntosDeCalor;
	
	public PuntosDeCalor(double temperatura, int puntosDeCalor) {
		this.temperatura = temperatura;
		this.puntosDeCalor = puntosDeCalor;
	}
	
	public double diferenciaSegunTemperatura(double otraTemperatura) {
		double diferencia = this.temperatura - otraTemperatura;
		return Math.abs(diferencia);
	}

	public int getPuntosDeCalor() {
		return puntosDeCalor;
	}
	
	

}
