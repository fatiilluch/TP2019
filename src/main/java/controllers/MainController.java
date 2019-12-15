package controllers;

import com.twilio.rest.api.v2010.account.usage.record.ThisMonth;
import domain.Usuario;
import repositories.RepositorioUsuario;
import repositories.factories.FactoryRepositorioUsuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class MainController extends Controller{
	public static RepositorioUsuario repo;
	
	public MainController() {
		this.repo = FactoryRepositorioUsuario.get();
	}
	
	public ModelAndView show(Request req, Response res){
        comprobarUsuarioLogeado(req,res);
		return new ModelAndView(null, "main.hbs");
	}
	
	public ModelAndView seleccion_guardarropa(Request req, Response res){

		String id = req.params("actualUsuario");
		req.session().attribute("id", id);

		return new ModelAndView(null,"guardarropa.hbs");
	}
	
	public ModelAndView seleccion_eventos(Request req, Response res){
		
		String username = req.params("actualUsuario");
		
		Usuario user = repo.buscarPorUsername(username);
		int id = user.getId();
		
		req.session().attribute("id", id);
		
		res.redirect("/eventos");
        
		return null;
	}
	
	public ModelAndView seleccion_sugerencias(Request req, Response res){
		
		String username = req.params("actualUsuario");
		
		Usuario user = repo.buscarPorUsername(username);
		int id = user.getId();
		
		req.session().attribute("id", id);
		
		res.redirect("/sugerencias");
        
		return null;
	}

}
