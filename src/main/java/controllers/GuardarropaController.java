package controllers;

import domain.Guardarropa;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import repositories.RepositorioGuardarropa;
import repositories.factories.FactoryRepositorioGuardarropa;

public class GuardarropaController extends Controller{
    private RepositorioGuardarropa repo;

    public GuardarropaController()
    {
        this.repo = FactoryRepositorioGuardarropa.get(); //va a chequear que repo usar
    }

    public ModelAndView mostrarGuardarropaUsuario(Request request,Response response){
        comprobarUsuarioLogeado(request,response);
        int id = idUsuario(request);

        List<Guardarropa> guardarropasDeUsuario = this.repo.buscarGuardarropaPorUsuario(id);
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("guardarropas", guardarropasDeUsuario);
        ModelAndView vista  = new ModelAndView(parametros, "guardarropa.hbs");
        return  vista;
    }

}
