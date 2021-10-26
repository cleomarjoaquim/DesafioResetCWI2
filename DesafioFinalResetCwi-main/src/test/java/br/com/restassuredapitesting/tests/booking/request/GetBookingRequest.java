package br.com.restassuredapitesting.tests.booking.request;

import br.com.restassuredapitesting.base.BaseTest;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import javax.xml.soap.SAAJResult;

import static io.restassured.RestAssured.given;

public class GetBookingRequest {


    @Step("Retorna os IDs conforme o filtro")
    public Response bookingReturnIds(String filtro1, String valorFiltro1, String filtro2, String valorFiltro2,String filtro3, String valorFiltro3){
        return given()
                .queryParams(filtro1,valorFiltro1,filtro2,valorFiltro2, filtro3,valorFiltro3)
                .when()
                .get("booking");
    }

    @Step("Retorna uma reserva específica pelo Id")
    public Response buscaReservaPorIDRequest(int id){
        return given()
                .when()
                .log().all()
                .get("booking/"+id);
    }
}