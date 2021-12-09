package org.example;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;


public class AccountTests extends BaseClass {


    @Test
    void getAccountInfoTest() {
        given()
                .headers("Authorization", token)
                .log()
                .method()
                .log()
                .uri()
                .when()
                .get("https://api.imgur.com/3/account/{username}", username)
                .then()
                .statusCode(200);
    }

    @Test
    void getAccountImagesTest() {
         imageHash=given()
                .headers("Authorization", token)
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
                .headers("Authorization", token)
                .multiPart("public_images", true)
                .multiPart("messaging_enabled", false)
                .when()
                .put("https://api.imgur.com/3/account/{username}/settings", username)
                .prettyPeek()
                .then()
                .statusCode(200)
                .contentType("application/json");
    }
    @Test
    void getGalleryTagsTest() {
        given()
                .headers("Authorization", token)
                .log()
                .method()
                .log()
                .uri()
                .when()
                .get("https://api.imgur.com/3/tags")
                .prettyPeek()
                .then()
                .extract()
                .response()
                .jsonPath()
                .getString("data.tags.name");
    }
    @Test
    void postFollowTagTest() {
        given()
                .headers("Authorization", token)
                .log()
                .method()
                .log()
                .uri()
                .when()
                .post("https://api.imgur.com/3/account/me/follow/tag/{tagName}", "disney")
                .prettyPeek()
                .then()
                .statusCode(200);
    }


}

