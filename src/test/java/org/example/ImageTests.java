package org.example;

import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class ImageTests extends BaseClass {

    @Test
    void postFavoriteAnImageTest() {
        given()
                .spec(requestSpecification)
                .expect()
                .body("success", is(true))
                .when()
                .get("https://api.imgur.com/3/image/{imageHash}/favorite",imageHash )
                .prettyPeek()
                .then()
                .spec(responseSpec);
    }
    @Test
    void postUpdateImageInformationTest() {
        given()
                .spec(requestSpecification)
                .multiPart("title","Update Information")
                .multiPart("description","New post")
                .expect()
                .body("success", is(true))
                .when()
                .get("https://api.imgur.com/3/image/{imageHash}/favorite",imageHash )
                .prettyPeek()
                .then()
                .spec(responseSpec);
    }
    @Test
    void postAlbumCreationTest() {
         given()
                .spec(requestSpecification)
                .multiPart("cover",imageHash)
                .multiPart("title","new album_02")
                .multiPart("ids[]", imageHash)
                .when()
                .post("https://api.imgur.com/3/album" )
                .prettyPeek()
                .then()
                .spec(responseSpec);


    }
    @Test
    void postRemoveImagesFromAnAlbumTest() {
        given()
                .spec(requestSpecification)
                .multiPart("ids[]",imageHash)
                .expect()
                .body("success", is(true))
                .when()
                .post("https://api.imgur.com/3/album/{albumDeleteHash}/remove_images", albumDeleteHash )
                .prettyPeek()
                .then()
                .spec(responseSpec);
    }


}
