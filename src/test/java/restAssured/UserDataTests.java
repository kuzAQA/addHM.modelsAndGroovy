package restAssured;

import models.LombokUserData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static restAssured.Specs.request;
import static restAssured.Specs.responseSpec;

public class UserDataTests {
    @Test
    @DisplayName("Проверка email с помощью моделей Lombok")
    void checkSingleUserWithLombokTest() {
        LombokUserData data = given()
                .spec(request)
                .when()
                .get("/users/10")
                .then()
                .spec(responseSpec)
                .log().body()
                .extract().as(LombokUserData.class);
        assertEquals(10, data.getUser().getId());
    }

    @Test
    @DisplayName("Проверка email с groovy")
    void checkEmailUsingGroovy() {
        given()
                .spec(request)
                .when()
                .get("/users")
                .then()
                .spec(responseSpec)
                .log().body()
                .body("data.findAll{it.email =~/.*?@reqres.in/}.email.flatten()",
                        hasItem("charles.morris@reqres.in"));
    }


    @Test
    @DisplayName("Проверка id с groovy")
    void checkUserIdUsingGroovy() {
        given()
                .spec(request)
                .when()
                .get("/users")
                .then()
                .spec(responseSpec)
                .log().body()
                .body("data.findAll{it.id == 6}.id.flatten()",
                        hasItem(6));
    }
}