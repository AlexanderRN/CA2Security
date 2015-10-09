package dk.cphbusiness.rest;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import dk.cphbusiness.entity.Person;
import dk.cphbusiness.entity.Phone;
import dk.cphbusiness.exceptions.PersonNotFoundException;
import dk.cphbusiness.facade.PersonFacade;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.DELETE;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("persons")
public class ApiResource {


    Gson gson;
    @Context
    private UriInfo context;
    EntityManagerFactory emf;
    PersonFacade f = null;

    public ApiResource() {
        gson = new GsonBuilder().setPrettyPrinting().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create();
        emf = Persistence.createEntityManagerFactory("CA2-Security-TestPU");
        f = new PersonFacade(emf);
    }

    //--------------------------------------1-------------------------------------
    @GET
    @Path("complete")
    @Produces("application/json")
    public String getAllPersons() {
        List<Person> persons = f.getPersons();
        return gson.toJson(persons);
    }

    //-------------------------------------2--------------------------------------

    @GET
    @Path("complete/{id}")
    @Produces("application/json")
    public String getPerson(@PathParam("id") int id) throws PersonNotFoundException {
        return gson.toJson(f.getPerson(id));
    }

    //-------------------------------------3-------------------------------------

    @GET
    @Path("contactinfo")
    @Produces("application/json")
    public Response getPersonsContactInfo() {
        JsonArray out = new JsonArray();
        JsonObject jperson = new JsonObject();

        List<Person> persons = f.getPersons();

        for (Person person : persons) {
            jperson = makePerson1(person);

            out.add(jperson);
        }
        return Response.status(Response.Status.OK).entity(out.toString()).build();
    }

   //--------------------------------------4------------------------------------
    @GET
    @Path("contactinfo/{id}")
    @Produces("application/json")
    public Response getPersonContactInfoById(@PathParam("id") int id) throws PersonNotFoundException {
        JsonObject jperson = new JsonObject();

        Person person = f.getPerson(id);

        jperson = makePerson1(person);

        return Response.status(Response.Status.OK).entity(jperson.toString()).build();
    }
    //----------------------------------5---------------------------------------

    @POST
    @Consumes("application/json")
    public Response createUser(String person) {
        Person p = gson.fromJson(person, Person.class);
        f.createPerson(p);
        return Response.status(Response.Status.CREATED).type(MediaType.APPLICATION_JSON).entity(gson.toJson(p)).build();
    }
    
    @DELETE
    @Path("delete/{id}")
    @Consumes("application/json")
    public Response removeUser(int id) throws PersonNotFoundException {
        f.removePerson(id);
        return Response.status(Response.Status.GONE).build();
    }

    //------------------------Konventere til json(Hjælpe-FKT)---------------------
    private JsonObject makePerson(Person person) {
        JsonObject jperson = new JsonObject();
        jperson.addProperty("id", person.getId());
        jperson.addProperty("firstName", person.getFirstName());
        jperson.addProperty("lastName", person.getLastName());
        jperson.addProperty("email", person.getEmail());
        jperson.addProperty("street", person.getAddress().getStreet());
        jperson.addProperty("additionalinfo", person.getAddress().getAdditionalInfo());
        jperson.addProperty("zip", person.getAddress().getCityInfo().getZip());
        jperson.addProperty("city", person.getAddress().getCityInfo().getCity());

        JsonArray JpersTlf = new JsonArray();
        JsonObject pers;
        for (Phone phone : person.getPhones()) {
            pers = new JsonObject();
            pers.addProperty("number", phone.getPhone());
            pers.addProperty("description", phone.getDescription());
            JpersTlf.add(pers);
        }

        jperson.add("PersonTlf", JpersTlf);
        return jperson;

    }

    //-----------------------------Konventere til json--------------------------- 

    private JsonObject makePerson1(Person person) {
        JsonObject jperson = new JsonObject();
        jperson.addProperty("firstName", person.getFirstName());
        jperson.addProperty("email", person.getEmail());

        JsonArray JpersTlf = new JsonArray();
        JsonObject pers;
        for (Phone phone : person.getPhones()) {
            pers = new JsonObject();
            pers.addProperty("number", phone.getPhone());
            pers.addProperty("description", phone.getDescription());
            JpersTlf.add(pers);
        }

        jperson.add("PersonTlf", JpersTlf);
        return jperson;

    }

}
