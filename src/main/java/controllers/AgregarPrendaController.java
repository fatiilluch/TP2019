package controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.Color;
import domain.events.Protocolo;
import domain.Prenda;
import domain.Tela;
import domain.TipoDePrenda;
import domain.Guardarropa;
import domain.Imagen;

import repositories.RepositorioColores;
import repositories.RepositorioGuardarropa;
import repositories.RepositorioPrenda;
import repositories.RepositorioTipoDePrenda;
import repositories.factories.FactoryRepositorioGuardarropa;
import repositories.factories.FactoryRepositorioPrenda;
import repositories.factories.FactoryRepositorioTipoDePrenda;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class AgregarPrendaController extends Controller{
    private RepositorioPrenda repo;
    private RepositorioTipoDePrenda repositorioTipoDePrenda;
    private RepositorioGuardarropa repositorioGuardarropa;
    private RepositorioColores repoColores;
	private Prenda prenda ;

    public AgregarPrendaController()
    {
        this.repo = FactoryRepositorioPrenda.get();
        this.repositorioTipoDePrenda = FactoryRepositorioTipoDePrenda.get();
        this.repoColores = RepositorioColores.getInstance();
        this.prenda = new Prenda();
        this.repositorioGuardarropa = FactoryRepositorioGuardarropa.get();
    }

    public static ModelAndView show(Request req,Response res){
        return new ModelAndView(null, "agregarPrenda.hbs");
    }

    public ModelAndView agregarPrenda(Request request, Response response){


        int idTipo = new Integer (request.queryParams("tipoDePrenda"));
        Tela tipoTela = Tela.valueOf(request.queryParams("tela"));
        Color colorPrimario = Color.valueOf(request.queryParams("color"));
        Color colorSecundario = Color.valueOf(request.queryParams("colorSecundario"));
        Protocolo protocolo = Protocolo.valueOf(request.queryParams("protocolo"));

        Guardarropa guardarropa = this.repositorioGuardarropa.buscar(new Integer (request.queryParams("guardarropa")));
        Prenda prenda = new Prenda(this.repositorioTipoDePrenda.buscar(idTipo),tipoTela,colorPrimario,colorSecundario,protocolo,null);
        guardarropa.agregarPrenda(prenda);

        this.repositorioGuardarropa.modificar(guardarropa);

        response.redirect("/main");
        return new ModelAndView(null,"main.hbs"); //Acá en si no llega nunca, es solo formalismo :v

    }

    public ModelAndView mostrarTodosTiposPrendas(Request request, Response response)
    {
        comprobarUsuarioLogeado(request,response);
        comprobarGuardarropaPerteneceUsuario(request,response);

        List<TipoDePrenda> tipoDePrenda = this.repositorioTipoDePrenda.buscarTodos();
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("TipoDePrenda", tipoDePrenda);
        ModelAndView vista  = new ModelAndView(parametros, "agregarPrenda.hbs");
        return  vista;
    }
    
    public ModelAndView mostrarTodosTiposTelas(Request request, Response response) {
    	comprobarUsuarioLogeado(request,response);
        comprobarGuardarropaPerteneceUsuario(request,response);

    	int tipoPrenda = new Integer (request.queryParams("tipoDePrenda"));

        List<Tela> telasDeTipoPrenda = this.repositorioTipoDePrenda.buscarTelasPorTipoDePrenda(tipoPrenda);
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("telasAdmitidas", telasDeTipoPrenda);
    	ModelAndView vista = new ModelAndView(parametros, "agregarPrenda2.hbs") ;
    	return  vista;
    }

}
