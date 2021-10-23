package br.com.restassuredapitesting.tests.booking.request;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ReservaEspecificaRequest {
    //@Step("Exibe dados de uma reserva especifica")
 @Step("Retorna uma reserva específica pelo Id")

    public Response reservaEspecificaRequest(int id){
        return given()
                .when()
                .get("booking/"+id);
    }
}
