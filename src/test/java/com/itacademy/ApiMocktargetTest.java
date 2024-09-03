package com.itacademy;

import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.matcher.RestAssuredMatchers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ApiMocktargetTest {

    @Test
    public void getMocktarget() {
        given().config(RestAssured.config().logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL)))
                .log().uri()
                .when().get("https://mocktarget.apigee.net/xml")
                .then().log().status().statusCode(200)
                .assertThat().body(RestAssuredMatchers.matchesXsdInClasspath("xml/getMocktargetXmlSchema.xsd"));
    }
}
