package domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import domain.events.Evento;
import domain.events.Frecuencia;
import domain.events.Protocolo;
import domain.notificacion.AlertaMeteorologica;
import domain.notificacion.EventoProximo;
import domain.notificacion.Mail;
import domain.notificacion.Notificacion;
import domain.notificacion.SMS;

public class TestNotificaciones {
	
	public static void main(String[] args) {
		Sensibilidad sensibilidadPepe = new Sensibilidad(2.0,3.2);
		Usuario pepe = new Usuario("Pepe", "123", sensibilidadPepe, "Pepe", "Grillo");
		List<Notificacion> notificaciones = new ArrayList<Notificacion>();
		
		Evento fiesta = new Evento("Fiesta", new Date() ,Protocolo.Formal,Frecuencia.UNICO,pepe);
	
		pepe.setMail("dds.grupo1@gmail.com");
		pepe.setTelefono("+541165696294");

		
		notificaciones.add(Mail.getInstance());
		notificaciones.add(SMS.getInstance());
		
		notificaciones.forEach(notificacion -> notificacion.enviarNotificacion(pepe,new EventoProximo(fiesta.getNombre())));
		notificaciones.forEach(notificacion -> notificacion.enviarNotificacion(pepe,new AlertaMeteorologica(fiesta.getNombre())));
	  
	}

}
