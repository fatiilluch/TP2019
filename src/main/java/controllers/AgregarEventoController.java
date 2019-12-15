package controllers;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;
import domain.events.Evento;
import domain.events.Frecuencia;
import domain.events.Protocolo;
import domain.Usuario;
import domain.suggestions.GestorDeSugerencias;
import repositories.RepositorioEvento;
import repositories.factories.FactoryRepositorioEvento;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import java.text.ParseException;


public class AgregarEventoController extends Controller{

	private RepositorioEvento repo;
	
	public AgregarEventoController()
    {
        this.repo = FactoryRepositorioEvento.get();

    }

	public ModelAndView mostrarEventosDeUsuario(Request req, Response res){
		
		comprobarUsuarioLogeado(req,res);
		
		int id = idUsuario(req);
        List<Evento> eventosPorUsuario = this.repo.buscarEventoPorUsuario(id);
        Map<String, Object> parametros = new HashMap<>();

        parametros.put("eventos", eventosPorUsuario);
		ModelAndView vista  = new ModelAndView(parametros, "index.hbs");
        return  vista;

	}

	public ModelAndView agregarEvento(Request request, Response response){

		Usuario usuario = repoUsuario.buscar(idUsuario(request));

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");


		String nombre = request.queryParams("nombre");
		Protocolo protocolo = Protocolo.valueOf(request.queryParams("protocolo"));
		Frecuencia frecuencia = Frecuencia.valueOf(request.queryParams("frecuencia"));
		String fecha = request.queryParams("fecha");

		try {
			Date fechaVerdad = formatter.parse(fecha);
			Evento evento = new Evento(nombre,fechaVerdad,protocolo,frecuencia,usuario);
			GestorDeSugerencias.getInstance().sugerirEventoNuevo(evento);
			//System.out.println("Este es el id de la sugerencia que tiene el evento" + evento.getSugerencias().get(0).getId());
			this.repo.agregar(evento);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		response.redirect("/eventos");
		return new ModelAndView(null,"index.hbs");

	}
	
}
