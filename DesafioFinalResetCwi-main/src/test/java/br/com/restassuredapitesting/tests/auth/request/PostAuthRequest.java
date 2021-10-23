package br.com.restassuredapitesting.tests.auth.request;

import br.com.restassuredapitesting.tests.auth.request.payLoads.AuthPayLoads;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PostAuthRequest {
    @Step("Retorna o token")
    public Response tokenReturn(){
        AuthPayLoads authPayLoads = new AuthPayLoads();

        return given()
                .header("Content-Type", "application/json")
                .when()
                .body(authPayLoads.jsonAuthLogin().toString())
                .post("auth");
    }
    @Step("Busca o token")
    public String getToken(){
        return "token="+this.tokenReturn()
                .then()
                .statusCode(200)
                .extract()
                .path("token");
    }

}
