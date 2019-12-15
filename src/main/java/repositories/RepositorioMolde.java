package repositories;

import domain.Molde;
import domain.TipoDePrenda;
import repositories.daos.DAO;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class RepositorioMolde extends Repositorio {
    private static RepositorioMolde instance;

    public static RepositorioMolde getInstance(DAO dao) {
        if(instance == null){
            instance = new RepositorioMolde(dao);
        }
        return instance;
    }

    private RepositorioMolde(DAO dao){
        this.setDao(dao);
    }

    public List<Molde> buscarTodos(){
        return this.dao.buscarTodos();
    }

    public Molde buscar(int id){
        return this.dao.buscar(id);
    }
    ///////
    public List<Molde> devolverMoldesMenoresIguales(int nivelDeAbrigo){
		return this.buscarTodos().stream().
				filter(molde -> molde.esMenorIgualA(nivelDeAbrigo)).
				collect(Collectors.toList());
	}
    
	public Molde devolverMoldeCandidato(int nivelDeAbrigo) {
		return this.devolverMoldesMenoresIguales(nivelDeAbrigo).stream().
				sorted(Comparator.comparing(Molde::getNivelDeAbrigo)).
				collect(Collectors.toList()).get(0);			
	}
    
    public Molde generoMoldeSegun(int nivelCalor) {
		Molde molde = new Molde();
		molde.setTiposDePrenda(devolverMoldeCandidato(nivelCalor).getTiposDePrendas());

		if(molde.estaComprendido(nivelCalor)) {
			cubrirNivelDeAbrigo(molde, nivelCalor);
		}
		
		this.agregar(molde);
		return molde;
	}
	
	private void cubrirNivelDeAbrigo(Molde molde,int nivelCalor) {
		
		int bandera = 0;
		
		while(bandera == 0) {
			agregarUnaCapa(molde);
			
			if(molde.elNivelDeAbrigoEsIgualA(nivelCalor) || molde.getNivelDeAbrigo() > nivelCalor) {
				bandera = 1;
			}
		}
	}
	
	private void agregarUnaCapa(Molde molde) {
		int ultimaCapa = molde.obtenerCapaMaxima();
		
		TipoDePrenda tipo = buscarTipoDePrendaConCapa(molde, ultimaCapa);
		molde.agregarTipoDePrenda(tipo);
	}
	
	private TipoDePrenda buscarTipoDePrendaConCapa(Molde molde, int ultimaCapa){
		TipoDePrenda tipo = molde.tipoDePrendaSuperiorConCapaMax();
		
		// podemos verlo como teorema de transitividad (a,b) && (b,c) => (a,c) porque se descarta los tipos del mismo nivel
		TipoDePrenda tipoNuevo = tipo.tipoCombinableConCapa(ultimaCapa+1);
		return tipoNuevo;
	}
}
