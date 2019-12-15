package domain.notificacion;

public class EventoProximo implements TipoNotificacion{

	private String nombreEvento;
	
	public EventoProximo(String nombreEvento){
		this.nombreEvento = nombreEvento;
	}
	
	@Override
	public String asunto() {
		return "Evento proximo: " + this.nombreEvento;
	}

	@Override
	public String cuerpo() {
		
		return "La sugerencia para su evento se encuentra lista, ingrese a la app -Link- (proximamente)";
	}

}
