package br.com.restassuredapitesting.tests.booking.request;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class DeleteBookingRequest {

    @Step("Exlui Reserva")
    public Response excluiReserva(int id, String token){
        return given()
                .header("Content-Type","application/json")
                .header("Cookie",token)
                .when()
                .log().all()
                .delete("booking/"+id);
    }

    @Step("Tentar excluir reserva sem autorização")
    public Response tentaExcluir(int id, String token){
        return given()
                .header("Content-Type","application/json")
                .header("Cookie",token)
                .when()
                .log().all()
                .delete("booking/"+id);
    }
}