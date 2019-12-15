package domain.suggestions;

import domain.*;
import domain.events.*;
import domain.notificacion.EventoProximo;
import domain.notificacion.Notificacion;
import domain.notificacion.TipoNotificacion;
import services.ElClima;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

public class GestorDeSugerencias {
	private List<Sugerencia> sugerencias;
	private List<Notificacion> mediosDeNotificacion;

	private static GestorDeSugerencias instance = null;

	public static GestorDeSugerencias getInstance(){
		if(instance == null)
			instance = new GestorDeSugerencias();
		return instance;
	}

	public GestorDeSugerencias() {
		this.sugerencias = new ArrayList<Sugerencia>();
		cronCreacionSugerencias();
	}
	
	
	public void setMediosDeNotificacion(Notificacion mediosDeNotificacion) {
		this.mediosDeNotificacion.add(mediosDeNotificacion);
	}
	
	public void EnvioDeNotificaciones(Usuario usuario, TipoNotificacion tipoNotificacion){
		this.mediosDeNotificacion.forEach(medio -> medio.enviarNotificacion(usuario, tipoNotificacion));
	}
	
	protected List<Sugerencia> sugerenciasParaProxEventos(){
		return GestorDeEventos.getInstance().eventosTotalesSugeribles().stream().
				map(evento -> evento.crearSugerencia()).collect(Collectors.toList());
	}
	
	public void setSugerencias() {
		this.sugerencias.addAll(this.sugerenciasParaProxEventos());
	}

	public void sugerirEventoNuevo(Evento evento){
		System.out.println("ESTOY EN EL METODO SUGERIR EVENTO NUEVO");
		if(evento.esSugerible()) {
			Sugerencia sugerencia = evento.crearSugerenciaSinNotificacion();
			System.out.println("ESTE ES EL ID de la SUGERENCIA CREADA" + sugerencia.getId());
			setSugerencia(sugerencia);
		}
	}

	public void setSugerencia(Sugerencia sugerencia){
		this.sugerencias.add(sugerencia);
	}
	
	public void clearSugerencias() {
		this.sugerencias.removeIf(sugerencia ->sugerencia.getEvento().finalizoEvento());
	}
	
	public void mostrarAtuendos(Sugerencia unaSugerencia) {
		for(int pos = 0 ; pos<unaSugerencia.getAtuendos().size(); pos++) {
			unaSugerencia.getAtuendo(pos);
		}
	}

	//Obtiene la primer sugerencia del evento
	public Sugerencia getSugerenciaDe(Evento evento) {
		return this.sugerencias.stream().
				filter(sugerencia -> sugerencia.getEvento().equals(evento)).collect(Collectors.toList()).get(0);
	}

	public void evaluarAlertas(){
		this.sugerencias.stream().filter(Sugerencia::eventoTerminaHoy).forEach(Sugerencia::evaluarAletas);
	}

	@SuppressWarnings("deprecation")
	private void cronCreacionSugerencias(){
		Timer timer = new Timer();
		Date horarioDeEjecucion = new Date();
		horarioDeEjecucion.setHours(2);
		horarioDeEjecucion.setMinutes(0);
		timer.schedule(obtenerSugerencias,horarioDeEjecucion,86400000);
	}

	TimerTask obtenerSugerencias = new TimerTask() {
	    @Override
        public void run()
        {
            evaluarAlertas();
        	setSugerencias();

        }
	};


}
