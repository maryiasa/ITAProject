package com.itacademy;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import io.restassured.filter.log.LogDetail;
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

    /*@Test
    public void getUsers() {
        log.info("getUsers BEGIN");
        Response response = given().log().uri()
                .queryParam("page", "2")
                .when()
                .get("api/users")
                .then().log().all()
                .assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("json/getUsersJsonSchema.json"))
                .extract().response();
        assertEquals(response.getStatusCode(), 200);
        if (response.getStatusCode() == 200) {
            log.info("getUsers END: " + response.getStatusLine());
        } else log.error("getUsers ERROR:\n " + response.getStatusLine() + "\n" + response.getHeaders() + "\n" + response.asString());

    }
*/
    @Test
    public void getUsers() {
        log.info("getUsers BEGIN");
        given().config(RestAssured.config().logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL)))
                .log().uri()
                .queryParam("page", "2")
                .when().get("api/users")
                .then().log().status()
                .statusCode(200)
                .assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("json/getUsersJsonSchema.json"));
        log.info("getUsers END");
    }

    @Test
    public void getSingleUser() {
        log.info("getSingleUser BEGIN");
        given().config(RestAssured.config().logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL)))
                .log().uri()
                .queryParam("id", "9")
                .when().get("api/users")
                .then().log().status()
                .statusCode(200)
                .body("data.id", equalTo(9))
                .body("data.email", equalTo("tobias.funke@reqres.in"))
                .assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("json/getSingleUserJsonSchema.json"));
        log.info("getSingleUser END");
    }

    @Test
    public void getSingleUserNotFound() {
        log.info("getSingleUserNotFound BEGIN");
        given().config(RestAssured.config().logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL)))
                .log().uri()
                .pathParam("path", 23)
                .when().get("api/users/{path}")
                .then().log().status()
                .statusCode(404)
                .body("isEmpty()", Matchers.is(true));
        log.info("getSingleUserNotFound END");
    }

    @Test
    public void getListOfResources() {
        log.info("getListOfResources BEGIN");
        given().config(RestAssured.config().logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL)))
                .log().uri()
                .when().get("api/unknown")
                .then().log().status().statusCode(200)
                .assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("json/getListOfResourcesJsonSchema.json"));
        log.info("getListOfResources END");
    }

    @Test
    public void getSingleResource() {
        log.info("getSingleResource BEGIN");
        given().config(RestAssured.config().logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL)))
                .log().uri()
                .pathParam("path", 2)
                .when().get("api/unknown/{path}")
                .then().log().status().statusCode(200)
                .assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("json/getSingleResourceJsonSchema.json"));
        log.info("getSingleResource END");

    }

    @Test
    public void getSingleResourceIsNotFound() {
        log.info("getSingleResourceIsNotFound BEGIN");
        given().config(RestAssured.config().logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL)))
                .log().uri()
                .pathParam("path", 23)
                .when().get("api/unknown/{path}")
                .then().log().status().statusCode(404)
                .body("isEmpty()", Matchers.is(true));
        log.info("getSingleResourceIsNotFound END");
    }

    @Test
    public void postCreateUser() {
        log.info("postCreateUser BEGIN");
        File file = new File("src/test/resources/json/user.json");
        given().config(RestAssured.config().logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL)))
                .log().uri()
                .contentType(ContentType.JSON).body(file)
                .when().post("api/users")
                .then().log().status().statusCode(201)
                .assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("json/postCreateUserJsonSchema.json"));
        log.info("postCreateUser END");
    }

    @Test
    public void putUpdateUser() {
        log.info("putUpdateUser BEGIN");
        Faker faker = new Faker();
        Map<String, String> userBody = new HashMap<>();
        userBody.put("name", faker.name().firstName());
        userBody.put("job", faker.job().title());
        given().config(RestAssured.config().logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL)))
                .log().uri()
                .contentType(ContentType.JSON).body(userBody)
                .pathParam("path", "2")
                .when().put("api/users/{path}")
                .then().log().status().statusCode(200)
                .assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("json/putUpdateUserJsonSchema.json"));
        log.info("putUpdateUser END");

    }

    @Test
    public void patchUpdateUser() {
        log.info("patchUpdateUser BEGIN");
        Faker faker = new Faker();
        Map<String, String> userBody = new HashMap<>();
        userBody.put("name", faker.name().firstName());
        given().config(RestAssured.config().logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL)))
                .log().uri()
                .contentType(ContentType.JSON).body(userBody)
                .pathParam("path", "2")
                .when().patch("api/users/{path}")
                .then().log().status().statusCode(200);
        log.info("patchUpdateUser END");
    }

    @Test
    public void deleteUser() {
        log.info("deleteUser BEGIN");
        given().config(RestAssured.config().logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL)))
                .log().uri()
                .pathParam("path", 2)
                .when().delete("api/unknown/{path}")
                .then().log().status().statusCode(204)
                .header("Content-Length", "0");
        log.info("deleteUser END");
    }

    @Test
    public void postRegisterSuccessful() {
        log.info("postRegisterSuccessful BEGIN");
        File file = new File("src/test/resources/json/registerUser.json");
        given().config(RestAssured.config().logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL)))
                .log().uri()
                .contentType(ContentType.JSON)
                .body(file)
                .when().post("api/register")
                .then().log().status().statusCode(200)
                .assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("json/postRegisterSuccessfulJsonSchema.json"));
        log.info("postRegisterSuccessful END");
    }

    @Test
    public void postRegisterUnsuccessful() {
        log.info("postRegisterUnsuccessful BEGIN");
        File file = new File("src/test/resources/json/registerUserUnsuccessful.json");
        given().config(RestAssured.config().logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL)))
                .log().uri()
                .contentType(ContentType.JSON)
                .body(file)
                .when().post("api/register")
                .then().log().status().statusCode(400)
                .body("error",  equalTo("Missing password"));
        log.info("postRegisterUnsuccessful END");
    }

    @Test
    public void postLoginSuccessful() {
        log.info("postLoginSuccessful BEGIN");
        File file = new File("src/test/resources/json/registerUser.json");
        given().config(RestAssured.config().logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL)))
                .log().uri()
                .contentType(ContentType.JSON)
                .body(file)
                .when().post("api/login")
                .then().log().status().statusCode(200)
                .assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("json/postLoginSuccessfulJsonSchema.json"));
        log.info("postLoginSuccessful END");
    }

    @Test
    public void postLoginUnsuccessful() {
        log.info("postLoginUnsuccessful BEGIN");
        File file = new File("src/test/resources/json/registerUserUnsuccessful.json");
        given().config(RestAssured.config().logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL)))
                .log().uri()
                .contentType(ContentType.JSON)
                .body(file)
                .when().post("api/login")
                .then().log().status().statusCode(400)
                .body("error",  equalTo("Missing password"));
        log.info("postLoginUnsuccessful END");
    }

    @Test
    public void getDelayResponse() {
        log.info("getDelayResponse BEGIN");
        given().config(RestAssured.config().logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL)))
                .log().uri()
                .queryParam("delay", "3")
                .when().get("api/users")
                .then().log().status().statusCode(200)
                .assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("json/getUsersJsonSchema.json"));
        log.info("getDelayResponse END");
    }

    @Test
    public void combineGetAndPutUser() {
        Response response = given().config(RestAssured.config().logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL)))
                .log().uri()
                .queryParam("id", "9").when().get("api/users").then().log().status()
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
                given().config(RestAssured.config().logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL)))
                .log().uri()
                .contentType(ContentType.JSON).body(userBody)
                .pathParam("path", "2")
                .queryParam("id", "9")
                .when().put("api/users/{path}").jsonPath().getString("name");
        assertEquals(first_name, responseName);

    }

}
