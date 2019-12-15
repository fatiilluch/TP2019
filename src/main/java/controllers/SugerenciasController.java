package controllers;

import domain.*;
import domain.events.Protocolo;
import domain.suggestions.Sugerencia;
import domain.events.Evento;
import repositories.*;
import repositories.factories.*;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class SugerenciasController extends Controller {
	private RepositorioSugerencia repo;
	private RepositorioEvento repositorioEvento;
	private RepositorioGuardarropa repositorioGuardarropa;
	private RepositorioPrenda repositorioPrenda;
	private RepositorioAtuendo repositorioAtuendo;

	public SugerenciasController() {
		this.repo = FactoryRepositorioSugerencia.get();
		this.repositorioEvento = FactoryRepositorioEvento.get();
		this.repositorioGuardarropa = FactoryRepositorioGuardarropa.get();
		this.repositorioPrenda = FactoryRepositorioPrenda.get();
		this.repositorioAtuendo = FactoryRepositorioAtuendo.get();
	}

	public ModelAndView show(Request req, Response res) {
		comprobarUsuarioLogeado(req, res);
		return new ModelAndView(null, "sugerencias.hbs");
	}

	public ModelAndView atuendosAceptados(Request req, Response res) {
		comprobarUsuarioLogeado(req, res);
		List<Guardarropa> guardarropas = this.repositorioGuardarropa.buscarGuardarropaPorUsuario(idUsuario(req));
		List<Atuendo> atuendosAceptados = new ArrayList<Atuendo>();
		guardarropas.forEach(guardarropa -> atuendosAceptados.addAll(guardarropa.getAtuendosAceptados()));

		Map<String, Object> parametros = new HashMap<>();
		parametros.put("atuendosAceptados", atuendosAceptados);
		return new ModelAndView(parametros, "atuendosAceptados.hbs");
	}

	public ModelAndView sugerenciasUsuario(Request req, Response res)
	{
		comprobarUsuarioLogeado(req, res);
		int id = idUsuario(req);
		List<Evento> eventoDelUsuario = this.repositorioEvento.buscarEventoPorUsuario(id);
		List<Sugerencia> sugerenciaList = new ArrayList<Sugerencia>();
		eventoDelUsuario.forEach(evento -> sugerenciaList.addAll(this.repo.buscarSugerenciasEvento(evento.getId())));

		Map<String, Object> parametros = new HashMap<>();
		parametros.put("sugerencias", sugerenciaList);
		ModelAndView vista = new ModelAndView(parametros, "pedidoDeSugerencia.hbs");
		return vista;
	}

	public ModelAndView pedirSugerencia(Request req,Response res){
		comprobarUsuarioLogeado(req, res);
		Usuario usuario = repoUsuario.buscar(idUsuario(req));
		Atuendo atuendo = usuario.pedirAtuendoGuardarropaRandom();
			if(atuendo != null) {
				Map<String, Object> parametros = new HashMap<>();
				parametros.put("atuendo",atuendo);
				ModelAndView vista = new ModelAndView(parametros,"pedidoDeSugerencia.hbs");

				return vista;
			}
			else{res.redirect("/main?aviso=1"); return null;}
	}

	public ModelAndView persistirSugerenciaAceptada(Request request,Response response){
		comprobarUsuarioLogeado(request, response);
		Atuendo atuendo = crearAtuendo(request);
		int idGuardarropas = this.repositorioPrenda.buscarGuardarropaPorPrenda(atuendo.getPrendas().get(0).getId());
		Guardarropa guardarropaAPersistir = this.repositorioGuardarropa.buscar(idGuardarropas);
		guardarropaAPersistir.agregarAListaAceptado(atuendo);
		atuendo.setGuardarropa(guardarropaAPersistir);
		this.repositorioGuardarropa.modificar(guardarropaAPersistir);
		response.redirect("/main");
		return new ModelAndView(null,"main.hbs");
	}

	public ModelAndView persistirSugerenciaRechazada(Request request,Response response){
		comprobarUsuarioLogeado(request, response);
		Atuendo atuendo = crearAtuendo(request);
		int idGuardarropas = this.repositorioPrenda.buscarGuardarropaPorPrenda(atuendo.getPrendas().get(0).getId());
		Guardarropa guardarropaAPersistir = this.repositorioGuardarropa.buscar(idGuardarropas);
		guardarropaAPersistir.agregarAListaRechazado(atuendo);
		atuendo.setGuardarropa(guardarropaAPersistir);
		this.repositorioGuardarropa.modificar(guardarropaAPersistir);
		response.redirect("/pedidoDeSugerencia");
		return new ModelAndView(null,"main.hbs");
	}

	public Atuendo crearAtuendo(Request request){
		List<Prenda> prendas = new ArrayList<Prenda>();

		Prenda prendaSup1= repositorioPrenda.buscar(new Integer (request.queryParams("sup1")));
		prendas.add(prendaSup1);

		if(request.queryParams("sup2").isEmpty() != true){
			Prenda prendaSup2= repositorioPrenda.buscar(new Integer (request.queryParams("sup2")));
			prendas.add(prendaSup2);
		}
		Prenda prendaInf= repositorioPrenda.buscar(new Integer (request.queryParams("inf")));
		prendas.add(prendaInf);
		Prenda prendaCalzado= repositorioPrenda.buscar(new Integer (request.queryParams("cal")));
		prendas.add(prendaCalzado);

		//Habría que comprobar que las prendas son del mismo usuario, y que estan en el mismo guardarropas

		return new Atuendo(prendas,Protocolo.Informal);
	}

	public ModelAndView parseoVerSugerencia(Request request, Response response){
		comprobarUsuarioLogeado(request, response);
		Evento eventoAVer = this.repositorioEvento.buscarTodos().stream()
							.filter(evento -> evento.getUsuario().getId() == idUsuario(request)&&  evento.getNombre().equals(request.queryParams("nombre")))
							.collect(Collectors.toList()).get(0);
		List<Sugerencia> sugerenciasEvento = this.repo.buscarSugerenciasEvento(eventoAVer.getId());
		Sugerencia sugerenciaAVer = null;

		if(sugerenciasEvento.size()!=0) {
			sugerenciaAVer = sugerenciasEvento.get(sugerenciasEvento.size() - 1);
		}else{ response.redirect("/main?aviso=2"); }

		Map<String, Object> parametros = new HashMap<>();
		parametros.put("sugerencia",sugerenciaAVer);

			if(eventoEsPasado(request)){
				ModelAndView vista = new ModelAndView(parametros,"calificarSugerencia.hbs");
				return vista;

			}
			else{
				ModelAndView vista = new ModelAndView(parametros,"muestraSugerencia.hbs");
				return vista;
			}
	}

	public ModelAndView modificarSugerenciaAceptada(Request request,Response response){
		comprobarUsuarioLogeado(request, response);
		Sugerencia sugerencia = this.repo.buscar(new Integer(request.queryParams("idaceptada")));
		Atuendo atuendo = sugerencia.getPrimerAtuendoNoRechazado();
		int idGuardarropas = this.repositorioPrenda.buscarGuardarropaPorPrenda(atuendo.getPrendas().get(0).getId());
		Guardarropa guardarropaAPersistir = this.repositorioGuardarropa.buscar(idGuardarropas);
		guardarropaAPersistir.agregarAListaAceptado(atuendo);
		//atuendo.setGuardarropa(guardarropaAPersistir);
		this.repositorioGuardarropa.modificar(guardarropaAPersistir);
		response.redirect("/main");
		return new ModelAndView(null,"main.hbs");
	}

	public ModelAndView modificarSugerenciaRechazada(Request request,Response response){
		comprobarUsuarioLogeado(request, response);
		Sugerencia sugerencia = this.repo.buscar(new Integer (request.queryParams("idrechazada")));
		Atuendo atuendo = sugerencia.getPrimerAtuendo();
		int idGuardarropas = this.repositorioPrenda.buscarGuardarropaPorPrenda(atuendo.getPrendas().get(0).getId());
		Guardarropa guardarropaAPersistir = this.repositorioGuardarropa.buscar(idGuardarropas);
		//atuendo.setGuardarropa(guardarropaAPersistir);

		Usuario usuario = repoUsuario.buscar(idUsuario(request));

		if(usuario.pedirAtuendoGuardarropaRandom() != null){
			guardarropaAPersistir.agregarAListaRechazado(atuendo);
			Atuendo atuendoNuevo = usuario.pedirAtuendoGuardarropaRandom();
			sugerencia.setAtuendo(atuendoNuevo);
			atuendoNuevo.setGuardarropa(guardarropaAPersistir);
			this.repositorioGuardarropa.modificar(guardarropaAPersistir);
			response.redirect("/eventos");
			return new ModelAndView(null,"main.hbs");
		}
		else{
			response.redirect("/main?aviso=1");
			return null;
		}
	}


	public boolean eventoEsPasado(Request request){
		long fecha = new Long(request.queryParams("fecha"));
		long ahora = Instant.now().toEpochMilli()-86400000;
		return ahora > fecha;
	}


	public ModelAndView modificarSensibilidadUsuario(Request request, Response response){
        comprobarUsuarioLogeado(request, response);

        Usuario usuario = repoUsuario.buscar(idUsuario(request));

        String idButtom = (request.queryParams("sensibilidad"));


        switch (idButtom){
            case "frioSup":
                usuario.setAumentarSensibilidad(Categoria.Superior,0.5);
                break;
            case "frioInf":
                usuario.setAumentarSensibilidad(Categoria.Inferior,0.5);
                break;
            case "calorSup":
                usuario.setDisminuirSensibilidad(Categoria.Superior,0.5);
                break;
            case "calorInf":
                usuario.setDisminuirSensibilidad(Categoria.Inferior,0.5);
                break;
            case "ok":
                break;

        }
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");

        System.out.println(usuario.getSensibilidad().superior());
        System.out.println(usuario.getSensibilidad().inferior());

        this.repoUsuario.modificar(usuario);

        response.redirect("/main");

        return new ModelAndView(null,"main.hbs");
    }

}