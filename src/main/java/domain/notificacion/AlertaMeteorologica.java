package domain.notificacion;

public class AlertaMeteorologica implements TipoNotificacion{

	private String nombreEvento;
	
	public AlertaMeteorologica(String nombreEvento){
		this.nombreEvento = nombreEvento;
	}
	
	@Override
	public String asunto() {
		return "Alerta meteorologica para su evento: " + this.nombreEvento;
	}

	@Override
	public String cuerpo() {
		return "Hay una nueva alerta meteorologica para el dia de su evento,"
				+ "generamos un nuevo atuendo, ingrese a la app -Link- (proximamente)";
	}

}
