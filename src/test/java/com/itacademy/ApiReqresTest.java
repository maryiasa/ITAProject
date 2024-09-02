package com.itacademy;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

@Log4j2
public class ApiReqresTest {

    private static final Logger log = LogManager.getLogger(ApiReqresTest.class);

    @BeforeMethod
    public void setUp() {
        RestAssured.baseURI = "https://reqres.in";
    }


    @Test
    public void getUsers() {
        given().log().uri()
                .queryParam("page", "2")
                .when().get("api/users")
                .then().log().all().statusCode(200)
                .assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("json/getUsersJsonSchema.json"));
    }


    @Test
    public void getSingleUser() {
        given().log().uri()
                .queryParam("id", "9")
                .when().get("api/users")
                .then().log().all().statusCode(200)
                .body("data.id", equalTo(9))
                .body("data.email", equalTo("tobias.funke@reqres.in"))
                .assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("json/getSingleUserJsonSchema.json"));
    }

    @Test
    public void getSingleUserNotFound() {
        given().log().uri()
                .pathParam("path", 23)
                .when().get("api/users/{path}")
                .then().log().all().statusCode(404)
                .body("isEmpty()", Matchers.is(true));
    }

    @Test
    public void postCreateUser() {
        File file = new File("src/test/resources/json/user.json");
        given().log().uri().contentType(ContentType.JSON).body(file)
                .when().post("api/users")
                .then().log().all().statusCode(201)
                .assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("json/postCreateUserJsonSchema.json"));
    }

    @Test
    public void putUpdateUser() {
        Faker faker = new Faker();
        Map<String, String> userBody = new HashMap<>();
        userBody.put("name", faker.name().firstName());
        userBody.put("job", faker.job().title());
        given().log().uri().contentType(ContentType.JSON).body(userBody)
                .pathParam("path", "2")
                .when().put("api/users/{path}")
                .then().log().all().statusCode(200)
                .assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("json/putUpdateUserJsonSchema.json"));

    }

    @Test
    public void combineGetAndPutUser() {
        Response response = given().log().uri().queryParam("id", "9").when().get("api/users").then().log().all()
                .assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("json/getSingleUserJsonSchema.json"))
                .extract().response();

        assertEquals(response.getStatusCode(), 200);
        assertEquals(response.getHeader("Connection"), "keep-alive");
        log.info("ID: " + (response.jsonPath().getInt("data.id")));
        log.info("EMAIL: " + (response.jsonPath().getString("data.email")));
        log.info("FIRST NAME: " + (response.jsonPath().getString("data.first_name")));
        log.info("LAST NAME: " + (response.jsonPath().getString("data.last_name")));
        String first_name = response.jsonPath().getString("data.first_name");

        Faker faker = new Faker();
        Map<String, String> userBody = new HashMap<>();
        userBody.put("name", faker.name().firstName());
        userBody.put("job", faker.job().title());
        String responseName =
        given().log().uri().contentType(ContentType.JSON).body(userBody)
                .pathParam("path", "2")
                .queryParam("id", "9")
                .when().put("api/users/{path}").jsonPath().getString("name");
        assertEquals(first_name, responseName);

    }

}
