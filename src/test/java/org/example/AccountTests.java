package org.example;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;

public class AccountTests extends BaseClass {



    @Test
    void getAccountInfoTest() {
        given()
                .spec(requestSpecification)
                .log()
                .method()
                .log()
                .uri()
                .when()
                .get("https://api.imgur.com/3/account/{username}", username)
                .prettyPeek()
                .then()
                .spec(responseSpec);
    }

    @Test
    void getAccountImagesTest() {
         imageHash=given()
                .spec(requestSpecification)
                .log()
                .method()
                .log()
                .uri()
                .when()
                .get("https://api.imgur.com/3/account/me/images")
                .prettyPeek()
                .then()
                 .extract()
                 .response()
                 .jsonPath()
                 .getString("data.id");

    }



    @Test
    void putChangeAccountSettingsTest() {
        given()
                .spec(requestSpecification)
                .multiPart("public_images", true)
                .multiPart("messaging_enabled", false)
                .when()
                .put("https://api.imgur.com/3/account/{username}/settings", username)
                .prettyPeek()
                .then()
                .spec(responseSpec);
    }
    @Test
    void getGalleryTagsTest() {
        given()
                .spec(requestSpecification)
                .log()
                .method()
                .log()
                .uri()
                .when()
                .get("https://api.imgur.com/3/tags")
                .prettyPeek()
                .then()
                .spec(responseSpec)
                .extract()
                .response()
                .jsonPath()
                .getString("data.tags.name");
    }
    @Test
    void postFollowTagTest() {
        given()
                .spec(requestSpecification)
                .log()
                .method()
                .log()
                .uri()
                .when()
                .post("https://api.imgur.com/3/account/me/follow/tag/{tagName}", "secret_santa_workshop")
                .prettyPeek()
                .then()
                .spec(responseSpec);
    }


}

