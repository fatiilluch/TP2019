package spark.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import domain.Prenda;
import spark.ResponseTransformer;

public class JsonTransformer implements ResponseTransformer{
	
	private Gson gson = new Gson();

    @Override
    public String render(Object model) {
        return gson.toJson(model);
    }

}
