package br.com.restassuredapitesting.base;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import org.junit.BeforeClass;

public class BaseTest {
    public Faker javaFaker = new Faker();
    @BeforeClass
    public static void setup(){
        RestAssured.baseURI= "https://treinamento-api.herokuapp.com/";
    }
}
