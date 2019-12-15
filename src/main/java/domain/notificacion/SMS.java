package domain.notificacion;

import  com.twilio.Twilio ; 
import  com.twilio.rest.api.v2010.account.Message ; 
import  com.twilio.type.PhoneNumber ;

import domain.Usuario; 

public class SMS extends Notificacion{
	
	// Find your Account Sid and Token at twilio.com/user/account
	static final String accountSid = "AC0a7d0196daf05d21b3b2eb7677c929e7";
	static final String authToken = "fbbf7dc2700c75704ae3979d780e1888";
	
	private static SMS instance = null;

	public static SMS getInstance(){
		if(instance == null)
			instance = new SMS();
		return instance;
	}

	@Override
	public void enviarNotificacion(Usuario usuario,TipoNotificacion tipoNotificacion) {
		
		this.setTipoNotificacion(tipoNotificacion);
		
		Twilio.init(accountSid, authToken);

		Message message = Message.creator(
			new PhoneNumber(usuario.getTelefono()),  // To number
		    new PhoneNumber("+12055512601"),  // From number NO TOCAR
		    asunto + " - " + cuerpo).create();

		System.out.println(message.getSid());
		  
		
	}
	
}
