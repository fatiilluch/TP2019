package domain;

import domain.events.Protocolo;
import services.ElClima;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "Premium")
public class UsuarioPremium extends EstadoUsuario
{
	
	
	public UsuarioPremium() {}

	public void agregarPrenda(Guardarropa guardarropa, Prenda prenda){
		guardarropa.agregarPrenda(prenda);
	}
	
	public void cambiarEstado(Usuario usuario) {
		usuario.setEstado(new UsuarioNormal());
	}

	public Atuendo pedirAtuendoAGuardarropa(Guardarropa unGuardarropa,Protocolo protocolo, Usuario usuario) {
		return unGuardarropa.elegirAtuendoSegunProtocolo(protocolo,ElClima.getInstance().calcularPuntoDeCalor(0),usuario.getSensibilidad());
	}

	public Atuendo pedirAtuendoEnUnDia(Guardarropa unGuardarropa, Protocolo protocolo, int dia, Usuario usuario){
		return unGuardarropa.elegirAtuendoSegunProtocolo(protocolo,ElClima.getInstance().calcularPuntoDeCalor(dia),usuario.getSensibilidad());
	}

	public int getMaximasPrendas(Guardarropa guardarropa) {
		return guardarropa.cantidadDePrendas();
	}
}
