package org.example;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public abstract class BaseClass {
    static Properties properties = new Properties();
    static String token;
    static  String username;
    static String imageHash;
    static String imageDeleteHash;
    static String albumDeleteHash;

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

}
