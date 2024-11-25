package test;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

@Listeners(TestListener.class)
public class StatusCodeTest {


    @BeforeMethod
    public void setUp(){
    }

    @Test
    public void testGoogleHomePageStatusCode() {
        RestAssured.baseURI = "https://www.google.com";
        given()
                .when().get("/")
                .then()
                .statusCode(200);
        System.out.println("Тест завершен успешно.");
    }

}
