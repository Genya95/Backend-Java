package org.example;

import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class ImageTests extends BaseClass {




    @Test
    void postFavoriteAnImageTest() {
        given()
                .headers("Authorization", token)
                .expect()
                .body("success", is(true))
                .when()
                .get("https://api.imgur.com/3/image/{imageHash}/favorite",imageHash )
                .prettyPeek()
                .then()
                .statusCode(200)
                .contentType("application/json");
    }
    @Test
    void postUpdateImageInformationTest() {
        given()
                .headers("Authorization", token)
                .multiPart("title","Update Information")
                .multiPart("description","New post")
                .expect()
                .body("success", is(true))
                .when()
                .get("https://api.imgur.com/3/image/{imageHash}/favorite",imageHash )
                .prettyPeek()
                .then()
                .statusCode(200)
                .contentType("application/json");
    }
    @Test
    void postAlbumCreationTest() {
         given()
                .headers("Authorization", token)
                .multiPart("cover",imageHash)
                .multiPart("title","new album_02")
                .multiPart("ids[]", imageHash)
                .when()
                .post("https://api.imgur.com/3/album" )
                .prettyPeek()
                .then()
                 .statusCode(200);


    }
    @Test
    void postRemoveImagesFromAnAlbumTest() {
        given()
                .headers("Authorization", token)
                .multiPart("ids[]",imageHash)
                .expect()
                .body("success", is(true))
                .when()
                .post("https://api.imgur.com/3/album/{albumDeleteHash}/remove_images", albumDeleteHash )
                .prettyPeek()
                .then()
                .statusCode(200)
                .contentType("application/json");
    }


}
