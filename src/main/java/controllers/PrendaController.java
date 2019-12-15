package controllers;

import domain.*;
import repositories.RepositorioPrenda;
import repositories.factories.FactoryRepositorioPrenda;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrendaController extends Controller{
        private RepositorioPrenda repo;

        public PrendaController()
        {
            super();
            this.repo = FactoryRepositorioPrenda.get();

        }

        public ModelAndView mostrarPrendasDeGuardarropa(Request request, Response response)
        {
           comprobarUsuarioLogeado(request,response);
           comprobarGuardarropaPerteneceUsuario(request,response);

            int id = new Integer (request.queryParams("guardarropa"));

            List<Prenda> prendas = this.repo.buscarPrendaPorGuardarropa(id);//aca debería agarrar el id del guardarropa que quiere
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("prendas",prendas);
            ModelAndView vista  = new ModelAndView(parametros, "prendas.hbs");
            return  vista;
        }
}
