package domain;

import services.RepositorioPuntosDeCalor;

public class PruebaRepoPuntosDeCalor {

	public static void main(String[] args) {
		RepositorioPuntosDeCalor repo = RepositorioPuntosDeCalor.getInstance();
		
		int pun =repo.calcularMasProximo(37.5);
		
		System.out.println(pun);

	}

}
