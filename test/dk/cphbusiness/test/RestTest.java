package dk.cphbusiness.test;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.junit.Test;
import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import com.jayway.restassured.parsing.Parser;
import dk.cphbusiness.entity.Person;
import org.junit.BeforeClass;

public class RestTest {
    Gson gson;

    public RestTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        baseURI = "http://localhost:8080";
        defaultParser = Parser.JSON;
        basePath = "/ExamPrep/api";

    }

    @Test
    public void getPerson() {
        when()
                .get("api/person/complete/1")
                .then()
                .statusCode(200)
                .body("firstName", equalTo("Bo"));

    }

    @Test
    public void getAll() {
        when()
                .get("api/persons/complete")
                .then()
                .statusCode(200)
                .body("lastName", equalTo("[boss,børgesen,zuri]"));
    }

    @Test
    public void deletePerson() {

        given()
                .when()
                .delete("api/persons/delete/1")
                .then()
                .statusCode(200);
    }

    @Test
    public void getPersonByPhone() {
        given()
                .when()
                .get("api/persons/phone/1234567")
                .then()
                .statusCode(200)
                .body("firstName", equalTo("bo"));

    }

    @Test
    public void getPersonByZip() {
        given()
                .when()
                .get("api/persons/zip/3000")
                .then()
                .statusCode(200)
                .body("firstName", equalTo("børge"));

    }

    @Test
    public void getPersonByHobby() {
        given()
                .when()
                .get("api/persons//1234567")
                .then()
                .statusCode(200)
                .body("firstName", equalTo("junes"));

    }

    public void createNewPerson() {
        JsonObject json = new JsonObject();
        Person person = new Person();
        json.addProperty("id", 99);
        json.addProperty("firstName", "knud");
        json.addProperty("lastName", "hansen");

        person = gson.fromJson(json, Person.class);

        given()
                .body(person)
                .when()
                .post("/persons")
                .then()
                .statusCode(200);

    }
}
