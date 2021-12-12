package org.example;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class BaseClass {
    static Properties properties = new Properties();
    static String token;
    static  String username;
    static String imageHash;
    static String imageDeleteHash;
    static String albumDeleteHash;
    ResponseSpecification responseSpec;
    RequestSpecification requestSpecification;


    @BeforeAll
     static void beforeAll(){
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    RestAssured.filters(new AllureRestAssured());
    getProperties();
    token = properties.getProperty("token");
    username = properties.getProperty("username");
    imageHash = properties.getProperty("imageHash");
    imageDeleteHash = properties.getProperty("imageDeleteHash");
        albumDeleteHash = properties.getProperty("albumDeleteHash");
    }

    private static void getProperties(){
        try (InputStream output = new FileInputStream("src/test/java/application.properties")) {
            properties.load(output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @BeforeEach
    void beforeTests(){
     responseSpec= new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectStatusLine("HTTP/1.1 200 OK")
            .expectContentType(ContentType.JSON)
            .build();
     requestSpecification = new RequestSpecBuilder()
             .addHeader("Authorization", token)
             .build();

    }


}
