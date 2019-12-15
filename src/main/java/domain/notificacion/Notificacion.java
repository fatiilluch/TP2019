package domain.notificacion;

import domain.Usuario;

public abstract class Notificacion {
	
	public TipoNotificacion tipoNotificacion;
	public String asunto;
	public String cuerpo;

	public abstract void enviarNotificacion(Usuario usuario,TipoNotificacion tipoNotificacion);

	public TipoNotificacion getTipoNotificacion() {
		return tipoNotificacion;
	}

	public void setTipoNotificacion(TipoNotificacion tipoNotificacion) {
		this.tipoNotificacion = tipoNotificacion;
		this.setCuerpoYAsunto();
	}
	
	public void setCuerpoYAsunto(){
		this.asunto = tipoNotificacion.asunto();
		this.cuerpo = tipoNotificacion.cuerpo();
	}
	
}
