package domain;

import javax.persistence.*;

import db.EntidadPersistente;
import domain.events.Protocolo;


@Entity
@Table(name = "EstadoUsuario")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="tipo")
public abstract class EstadoUsuario extends EntidadPersistente
{
	public void agregarPrenda(Guardarropa guardarropa, Prenda prenda){}
	public void cambiarEstado(Usuario usuario){}
    public abstract Atuendo pedirAtuendoAGuardarropa(Guardarropa unGuardarropa,Protocolo protocolo, Usuario usuario);
    public abstract Atuendo pedirAtuendoEnUnDia(Guardarropa unGuardarropa, Protocolo protocolo, int dia, Usuario usuario);
    public abstract int getMaximasPrendas(Guardarropa guardarropa);
}