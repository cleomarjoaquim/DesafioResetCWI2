package br.com.restassuredapitesting.tests.booking.request;

import br.com.restassuredapitesting.base.BaseTest;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import javax.xml.soap.SAAJResult;

import static io.restassured.RestAssured.given;

public class GetBookingRequest {


    @Step("Retorna os IDs da listagem de reservas")
    public Response bookingReturnIds(){
        return given()
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

    @Step("Retorna Reserva pelo Nome cadastrado na reserva")
    public Response buscaReservaPorNomeRequest(String firstname){
        return given()
                .header("Accept","applican/json")
                .queryParam("firstname", firstname)
                .when()
                .log().all()
                .get("booking"+firstname);
    }

    @Step("Retorna Reserva pelo Sobrenome cadastrado na reserva")
    public Response buscaReservaPorSobrenomeRequest(String lastname){
        return given()
                .header("Accept","application/json")
                .queryParam("lastname", lastname)
                .when()
                .log().all()
                .get("booking"+lastname);
    }
    @Step("Retorna Reserva pela data de Checkin cadastrado na reserva")
    public Response buscaReservaPorCheckin(String key, String date){
        return given()
                .header("Accept","applican/json")

                .queryParam(key,date)
                .when()
                .log().all()
                .get("booking");
    }
    @Step("Retorna Reserva pela data de checkout cadastrado na reserva")
    public Response buscaReservaPorCheckout(String key, String date){
        return given()
                .header("Accept","application/json")
                .queryParam(key,date)

                .when()
                .log().all()
                .get("booking");
    }
    @Step("Retorna Reserva pelo nome, data de checkin e datadde checkout cadastrado na reserva")
    public Response buscaReservaPorTresFiltros(String name1, String checkin1, String checkout1){
        return given()
                .header("Accept","application/json")
                .queryParam(name1,checkin1,checkout1)
                .when()
                .log().all()
                .get("booking");
    }
    @Step("Retorna Reserva pela data de Checkin cadastrado na reserva")
    public Response filtroMalFormatado(String checkin){
        return given()
                .header("Accept","application/json")
                .when()
                .log().all()
                .get("booking?dates%5Bcheckin%5D="+checkin);
    }

}


