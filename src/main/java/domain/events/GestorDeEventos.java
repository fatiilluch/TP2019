package domain.events;

import java.util.*;
import java.util.stream.Collectors;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import domain.Usuario;

public class GestorDeEventos {
	
	private List<Evento> eventos;
	private List<Evento> eventosTerminados;
	private static GestorDeEventos instance = null;

	public static GestorDeEventos getInstance(){
		if(instance == null)
			instance = new GestorDeEventos();
		return instance;
	}

	public GestorDeEventos() {
		this.eventos = new ArrayList<Evento>();
		this.eventosTerminados = new ArrayList<Evento>();
		cronActualizacion();
	}
	
	public void crearEvento(String nombre,String fecha,Protocolo tipoProtocolo,Frecuencia frecuencia,Usuario usuario) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		//creo una instancia a un evento con un nombre ,fechaEvento y el tipo de Protocolo de vestimenta
		Evento evento = new Evento(nombre,dateFormat.parse(fecha),tipoProtocolo,frecuencia,usuario);
		this.setEvento(evento);
	}

	public int diasFaltantesParaEvento(int indexEvento) {
		return this.eventos.get(indexEvento).diasFaltantes();
	}
	
	public void setEvento(Evento unEvento) {
		eventos.add(unEvento);
	}

	public void setEventoTerminado(Evento unEvento){
		eventosTerminados.add(unEvento);
	}

	public List<Evento> getEventos(Usuario usuario){
		return this.eventos.stream().filter(evento -> evento.getUsuario().equals(usuario)).collect(Collectors.toList());
	}

	public Evento getEventoPorNombre(String nombre,Usuario usuario){
		return getEventos(usuario).stream().filter(evento-> evento.getNombre().equals(nombre)).collect(Collectors.toList()).stream().findFirst().orElse(null);
	}

	public List<Evento> eventosProximos(Usuario usuario){
		return getEventos(usuario).stream().filter(Evento::esProximo).collect(Collectors.toList());
	}

	public List<Evento> eventosParaSugerir(Usuario usuario){
		return getEventos(usuario).stream().filter(Evento::esSugerible).collect(Collectors.toList());
	}

	public List<Evento> eventosTotalesSugeribles(){
		return this.eventos.stream().filter(Evento::esSugerible).collect(Collectors.toList());
	}

	@SuppressWarnings("deprecation")
	private void cronActualizacion(){
		// Está puesto para que lo haga 20 minutos antes de buscar las sugerencias
		Timer timer = new Timer();
		Date horarioDeEjecucion = new Date();
		horarioDeEjecucion.setHours(1);
		horarioDeEjecucion.setMinutes(40);
		timer.schedule(actualizacionEventos,horarioDeEjecucion,86400000);
	}

	public void actualizarEventos() {
		this.eventos.forEach(Evento::actualizarEvento);
		this.eventos.removeIf(Evento::eventoSigueFinalizado);
	}

	TimerTask actualizacionEventos = new TimerTask() {
		@Override
		public void run()
		{
			actualizarEventos();
		}
	};
}

