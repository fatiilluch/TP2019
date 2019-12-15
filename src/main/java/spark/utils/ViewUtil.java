package spark.utils;

import java.util.Map;

import spark.ModelAndView;
import spark.Request;

public class ViewUtil {
	
	public static ModelAndView responderError(Request request, Map<String, Object> model, String templatePath) {
        // la idea es que al no poder logiarse en el template te diga que el usuario es incorrecto obtenido de la request
        return new ModelAndView(model, templatePath);
    }

}
