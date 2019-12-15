package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.*;

import db.EntidadPersistente;
import domain.events.Protocolo;


@Entity
@Table(name = "Atuendo")
public class Atuendo extends EntidadPersistente
{
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "idAtuendo", referencedColumnName = "id")
    private List<Prenda> prendas;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "idGuardarropa", referencedColumnName = "id")
    private Guardarropa guardarropaAsociado;
    
	@Column(name = "protocolo")
	@Enumerated(value = EnumType.STRING)
    private Protocolo protocoloAtuendo;
    
	@Transient
    private boolean rechazado;

	public Atuendo(){}

    
    public Atuendo(List<Prenda> prendas, Protocolo unProtocolo)
    {
        this.prendas = prendas;
        this.protocoloAtuendo = unProtocolo;
        this.rechazado = false;
        
    }
  
    
    public Protocolo getProtocoloAtuendo() {
        return protocoloAtuendo;
    }
    public void setProtocolo(Protocolo unProtocolo) {
        this.protocoloAtuendo = unProtocolo;
    }
    
    public void aceptarAtuendo() {
    	this.rechazado = false;
    }
    
    public void rechazarAtuendo() {
    	this.rechazado = true;
    }
    
    public boolean estaRechazado() {
    	return this.rechazado;
    }
  
    public void setGuardarropa(Guardarropa guardarropa) {
        this.guardarropaAsociado = guardarropa;
    }

    public Guardarropa getGuardarropaAsociado() {
        return this.guardarropaAsociado;
    }

    public double puntos(List<Prenda> prendas)
    {
        return prendas.stream().mapToDouble(prenda -> prenda.getPuntosDeCalor()).sum(); 
    }

    public List<Prenda> getPrendas(){return this.prendas;}
    
    public List<Prenda> getPrendasSegun(Categoria categoria){
    	return this.prendas.stream().filter(prenda -> prenda.sosDeCategoria(categoria)).
    			collect(Collectors.toList());
    }
    
    public int puntosTotalesSegun(Categoria categoria) {
    	return this.getPrendasSegun(categoria).stream().mapToInt(prenda -> prenda.getPuntosDeCalor()).sum();
    }

    public List<Prenda> getParteSuperior() {
        return this.getPrendasSegun(Categoria.Superior);
    }

    public Prenda getParteSuperior1() {
		return this.getPrendasSegun(Categoria.Superior).get(0);
	}

    public Prenda getParteSuperior2() {
        if (this.getPrendasSegun(Categoria.Superior).size() > 1) {
            return this.getPrendasSegun(Categoria.Superior).get(1);
        }
        else return null;
    }

    /*
    public Prenda getParteSuperior() {
        return this.getPrendasSegun(Categoria.Superior);
    }
    */
    public Prenda getParteInferior() { return this.getPrendasSegun(Categoria.Inferior).get(0);}

	public Prenda getCalzado(){return this.getPrendasSegun(Categoria.Calzado).get(0);}

	public boolean equalsMio(Atuendo atuendo){
        List<Integer> idRechazados= new ArrayList<>();
        List<Integer> idCreado = new ArrayList<>();
        this.prendas.forEach(prenda -> System.out.println("ESTO ES UNA PRENDA DEL ATUENDO RECHAZADO" + prenda.getId()));
        atuendo.getPrendas().forEach(prenda -> System.out.println("ESTO ES UNA PRENDA DEL ATUENDO QUE CREO" + prenda.getId()));

        this.prendas.forEach(prenda -> idRechazados.add(prenda.getId()));
        atuendo.getPrendas().forEach(prenda -> idCreado.add(prenda.getId()));

        System.out.println("LISTA DE RECHAZADOS" + idRechazados);
        System.out .println("LISTA DE CREADOS" + idCreado);
        System.out.println("IGUALDAD?" + idRechazados.equals(idCreado));
        return idRechazados.equals(idCreado);
    }

    public boolean rechazadoPorGuardarropa(){
        if(this.guardarropaAsociado.getAtuendosRechazados() ==null){return false;}
        return !this.guardarropaAsociado.getAtuendosRechazados().stream().anyMatch(atuendoRechazado -> atuendoRechazado.equalsMio(this));
    }
}