package domain;

import domain.events.Protocolo;
import exception.GuardarropaConCapacidadLimitadaException;
import services.ElClima;
import services.Fichero;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "Normal")
public class UsuarioNormal extends EstadoUsuario
{
	private static int maximoPrendas;
	

	public String datoArchivo(String Dato) {
		Fichero.generoArchivo();
		return Fichero.obtenerParametros(Dato);
	}

	public UsuarioNormal() {}


	public void agregarPrenda(Guardarropa guardarropa, Prenda prenda)
	{
		try {

				maximoPrendas = Integer.parseInt(datoArchivo("maximoPrendas"));
				if (guardarropa.cantidadDePrendas() < maximoPrendas)
					guardarropa.agregarPrenda(prenda);
			} catch (GuardarropaConCapacidadLimitadaException exepcion) {
				System.out.println(exepcion.descripcion());
			}
	}

	public void cambiarEstado(Usuario usuario) {
		usuario.setEstado(new UsuarioPremium());
	}

	public Atuendo pedirAtuendoAGuardarropa(Guardarropa unGuardarropa, Protocolo protocolo, Usuario usuario)  {
		return unGuardarropa.elegirAtuendoSegunProtocolo(protocolo, ElClima.getInstance().calcularPuntoDeCalor(0), usuario.getSensibilidad());
	}

	public Atuendo pedirAtuendoEnUnDia(Guardarropa unGuardarropa, Protocolo protocolo, int dia, Usuario usuario) {
		return unGuardarropa.elegirAtuendoSegunProtocolo(protocolo, ElClima.getInstance().calcularPuntoDeCalor(dia), usuario.getSensibilidad());
	}

	public int getMaximasPrendas(Guardarropa guardarropa) {
		return maximoPrendas;
	}
}