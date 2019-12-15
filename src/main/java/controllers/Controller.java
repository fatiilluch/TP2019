package controllers;

import domain.Usuario;
import repositories.RepositorioUsuario;
import repositories.factories.FactoryRepositorioUsuario;
import spark.Request;
import spark.Response;

public class Controller {

    protected RepositorioUsuario repoUsuario;

    public Controller()
    {
        this.repoUsuario = FactoryRepositorioUsuario.get();
    }
    public int idUsuario(Request request){ return request.session().attribute("actualUsuario"); }

    public void comprobarUsuarioLogeado(Request request, Response response){
        if(request.session().attribute("actualUsuario") == null){
            response.redirect("/login");
        }
    }

    public void comprobarGuardarropaPerteneceUsuario(Request request, Response response){

        Usuario usuario = repoUsuario.buscar(idUsuario(request));
        int idGuardarropa = new Integer (request.queryParams("guardarropa"));


        if(usuario.getGuardarropas().stream().noneMatch(guardarropa -> guardarropa.getId() == idGuardarropa)){
            response.redirect("/guardarropa");
        }
    }
}
