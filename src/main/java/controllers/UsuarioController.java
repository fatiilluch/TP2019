package controllers;

import domain.Usuario;
import repositories.RepositorioUsuario;
import repositories.daos.DAOMySQL;
import repositories.factories.FactoryRepositorioUsuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsuarioController
{
	private RepositorioUsuario repo;

	public UsuarioController()
	{
		this.repo = FactoryRepositorioUsuario.get(); //va a chequear que repo usar
	}

	public String saludo(Request request, Response response)
	{
		String nombre = request.params("nombre");
		String saludo = "Hola " + nombre;
		return saludo;
	}

	public ModelAndView mostrarTodos(Request request, Response response)
	{
		List<Usuario> usuarios = this.repo.buscarTodos();
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("usuarios", usuarios);
		ModelAndView vista  = new ModelAndView(parametros, "usuarios.hbs");
		return  vista;
	}

	public ModelAndView mostrar (Request request, Response response)
	{
		int id = new Integer(request.params("id"));
		Usuario usuarioBuscado = this.repo.buscar(id);
		Map<String, Object> parametro = new HashMap<>();
		parametro.put("usuario", usuarioBuscado);
		ModelAndView vista = new ModelAndView(parametro, "usuario.hbs");
		return vista;
		
		
	}

	public Response modificar(Request request, Response response) {
		int id = new Integer(request.params("id"));
		Usuario usuarioBuscado = this.repo.buscar(id);

		String nombre = request.queryParams("nombre");
		usuarioBuscado.setUsername(nombre);

		// aca se busca por nombre (por ej), y va modificando lo que se requiera

		//String apellido = request.queryParams("apellido");
		//usuarioBuscado.setApellido(apellido);

		/*String telefono = request.queryParams("telefono");
		if (!telefono.isEmpty()) {
			int telefonoEntero = new Integer(telefono);
			usuarioBuscado.setTelefono(telefonoEntero);
		}*/


		//hacer lo mismo para todos los demas campos
		//no esta mal que el controller tenga tipos -- tiene que verificar que todo este ok

		this.repo.modificar(usuarioBuscado); //recien aca se modifican los datos tanto en la base como en memoria
		response.redirect("/usuarios"); //quiero hacer una redireccion
		return response;
	}
	
	////////////////////////////////////////////////////
	
	public static boolean autentificacion(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {
            return false;
        }
        
        RepositorioUsuario repo = FactoryRepositorioUsuario.get();
        Usuario user = repo.buscarPorUsernameAndPass(username, password);
        
        
        if (user == null) {
            return false;
        }
        
        return true;
    }
}


/* DESAFIO:
Eliminar hacerlo por ajax
Nuevo usuario -> usar el mismo template de usuario pero teniendo alguna consideraciones porque hay q emoezar a preguntar ciertas cosas
sentencia if es clave en handlebars  -> #if

 */

//$.ajax({
//type spark.delete
//url "usuario/"+id,
//success:function(result)
//		{
//			location.reload(true);
//		}
//	})