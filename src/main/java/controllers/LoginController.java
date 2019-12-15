package controllers;



import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import domain.Usuario;
import repositories.RepositorioUsuario;
import repositories.factories.FactoryRepositorioUsuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.utils.ViewUtil;

public class LoginController extends Controller {
	private static RepositorioUsuario repo;

	public LoginController() {
		this.repo = FactoryRepositorioUsuario.get();
	}

	public static ModelAndView show(Request req, Response res){
	    return new ModelAndView(null, "login.hbs");
	}
	
	public static ModelAndView login(Request req, Response res) {
		Map<String, Object> model = new HashMap<>();

		System.out.print(UsuarioController.autentificacion(req.queryParams("username"), req.queryParams("password")));

        if (!UsuarioController.autentificacion(req.queryParams("username"), req.queryParams("password"))) {
            model.put("autentificacionFallida", true);
            return ViewUtil.responderError(req, model, "login.hbs");
        }

		RepositorioUsuario repo = FactoryRepositorioUsuario.get();
		Usuario usuario = repo.buscarPorUsernameAndPass(req.queryParams("username"), req.queryParams("password"));
        int idUsuario = usuario.getId();

        model.put("autentificacionCorrecta", true);
		req.session().attribute("actualUsuario", idUsuario);
		res.redirect("/main");

		return null;
	}
}
