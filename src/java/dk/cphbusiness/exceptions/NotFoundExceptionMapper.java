package dk.cphbusiness.exceptions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import javax.servlet.ServletContext;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {

static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    @Context
    ServletContext context;


    @Override
    public Response toResponse(NotFoundException e) {
        JsonObject json = new JsonObject();
        if (Boolean.valueOf(context.getInitParameter("notFound"))) {
            json.addProperty("Code", 404);
        }
        json.addProperty("Message", "Url does not exist");
        return Response.status(Response.Status.NOT_FOUND).entity(json.toString()).build();
    }
}
