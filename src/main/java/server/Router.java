package server;

import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import spark.utils.BooleanHelper;
import spark.utils.HandlebarsTemplateEngineBuilder;
import controllers.*;

public class Router {
    private static HandlebarsTemplateEngine engine;

    private static void initEngine() {
        Router.engine = HandlebarsTemplateEngineBuilder
                .create()
                .withDefaultHelpers()
                .withHelper("isTrue", BooleanHelper.isTrue)
                .build();
    }

    public static void init() {
        Router.initEngine();
        Spark.staticFileLocation("/public");
        Router.configure();
    }

    private static void configure() //GET, POST, DELETE, PUT
    {
        GuardarropaController guardarropaController = new GuardarropaController();
        PrendaController prendaController = new PrendaController();
        SugerenciasController sugerenciaController= new SugerenciasController();
        AgregarPrendaController agregarPrendaController = new AgregarPrendaController();
        MainController mainController = new MainController();
        AgregarEventoController agregarEventoController = new AgregarEventoController();

        //Spark.get("/saludo", (req,res) -> "Hola :D");
        Spark.get("/login", LoginController::show,engine);
        Spark.post("/login", LoginController::login, engine);

        Spark.get("/main", mainController::show, engine);
        Spark.post("/main", mainController::seleccion_guardarropa, engine);
        Spark.post("/main", mainController::seleccion_eventos, engine);
        Spark.post("/main", mainController::seleccion_sugerencias, engine);

        Spark.get("/guardarropa", guardarropaController::mostrarGuardarropaUsuario, Router.engine);
        Spark.get("/prendas", prendaController::mostrarPrendasDeGuardarropa, Router.engine);
        Spark.get("/agregarPrenda", agregarPrendaController::mostrarTodosTiposPrendas, Router.engine);
        Spark.get("/agregarPrenda2", agregarPrendaController::mostrarTodosTiposTelas, Router.engine);
        Spark.get("/agregar",agregarPrendaController::agregarPrenda);

        Spark.get("/sugerencias", sugerenciaController::show,Router.engine);
        Spark.get("/atuendosAceptados",sugerenciaController::atuendosAceptados,Router.engine);
        Spark.get("/agregarEvento", agregarEventoController::agregarEvento, engine);
        Spark.get("/eventos", agregarEventoController::mostrarEventosDeUsuario, Router.engine);

        Spark.get("/pedidoDeSugerencia", sugerenciaController::pedirSugerencia, Router.engine);
        Spark.get("/persistirSugerenciaAceptada",sugerenciaController::persistirSugerenciaAceptada, Router.engine);
        Spark.get("/persistirSugerenciaRechazada",sugerenciaController::persistirSugerenciaRechazada, Router.engine);
        Spark.get("/verSugerencia",sugerenciaController::parseoVerSugerencia,engine);
        Spark.get("/modificarSugerenciaAceptada",sugerenciaController::modificarSugerenciaAceptada, Router.engine);
        Spark.get("/modificarSugerenciaRechazada",sugerenciaController::modificarSugerenciaRechazada, Router.engine);
        Spark.get("/modificarSensibilidadUsuario",sugerenciaController::modificarSensibilidadUsuario,Router.engine);

    }
}
