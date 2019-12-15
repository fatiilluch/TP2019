package domain;

import domain.events.*;
import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.util.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class EventoTest {

	private Usuario pepe;
	private Sensibilidad sensibilidadPepe;
	private String fecha, anioNuevo, navidadVieja;
	private long diasFaltantesAnioNuevo;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private Protocolo deportivo = Protocolo.Informal;

	@Before
    public void init(){
		this.sensibilidadPepe = new Sensibilidad(2.0,3.2);
		Usuario pepe = new Usuario("Pepe", "123", sensibilidadPepe, "Pepe", "Grillo");
        this.fecha = "2019-06-15";
		this.anioNuevo = "2020-01-01";
		this.navidadVieja = "2018-12-25";

        try {
			calcularDiasFaltantes();
			pepe.setEvento("Partido", fecha,deportivo,Frecuencia.UNICO);
			pepe.setEvento("Anio Nuevo",anioNuevo,deportivo,Frecuencia.ANUAL);
			pepe.setEvento("Navidad",navidadVieja,deportivo,Frecuencia.ANUAL);
        } catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void EventoNuevoVerFecha() {
		Assert.assertEquals(fecha,pepe.getEventoPorNombre("Partido").getFechaEvento());
	}

	@Test
	public void EventoNuevoVerNombre(){
		Assert.assertEquals("Partido",pepe.getEventos().stream().findFirst().get().getNombre());
	}

	@Test
	public void DiasFaltantesAnioNuevo(){
		Assert.assertEquals(diasFaltantesAnioNuevo,pepe.getEventoPorNombre("Anio Nuevo").diasFaltantes());
	}

	@Test
	public void EliminacionEventosEnActualizacion(){
			pepe.actualizacionEventosManual();
			Assert.assertEquals(2,pepe.getEventos().size());
	}

	@Test
	public void ActualizacionFechaSegunFrecuencia(){
			pepe.actualizacionEventosManual();
			Assert.assertEquals("2019-12-25",pepe.getEventoPorNombre("Navidad").getFechaEvento());
	}

	private void calcularDiasFaltantes() throws ParseException{
		diasFaltantesAnioNuevo = (dateFormat.parse(anioNuevo).getTime() - new Date().getTime()) / 86400000;
	}


}
