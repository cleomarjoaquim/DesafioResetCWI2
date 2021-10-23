package br.com.restassuredapitesting.tests.booking.request;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.junit.Test;
import sun.util.calendar.BaseCalendar;

import java.util.Date;

import static io.restassured.RestAssured.given;

public class ReservaEspecificaRequest {
    //@Step("Exibe dados de uma reserva especifica")
 @Step("Retorna uma reserva específica pelo Id")

    public Response reservaEspecificaRequest(int id){
        return given()
                .when()
                .get("booking/"+id);
    }

    @Step("Retorna Reserva pelo Nome cadastrado na reserva")
    public Response buscaReservaPorNomeRequest(String firstname){
        return given()
                .header("Accept","application/json")
                .when()
                .log().all()
                .get("booking?firstname="+firstname);
    }

    @Step("Retorna Reserva pelo Sobrenome cadastrado na reserva")
    public Response buscaReservaPorSobrenomeRequest(String lastname){
        return given()
                .header("Accept","application/json")
                .when()
                .log().all()
                .get("booking?lastname="+lastname);
    }
    @Step("Retorna Reserva pela data de Checkin cadastrado na reserva")
    public Response buscaReservaPorCheckin(String checkin){
        return given()
                .header("Accept","application/json")
                .when()
                .log().all()
                .get("booking?dates%5Bcheckin%5D="+checkin);
    }
    @Step("Retorna Reserva pela data de checkout cadastrado na reserva")
    public Response buscaReservaPorCheckout(String checkout){
        return given()
                .header("Accept","application/json")
                .when()
                .log().all()
                .get("booking?dates%5Bcheckout%5D="+checkout);
    }
    @Step("Retorna Reserva pelo nome, data de checkin e datadde checkout cadastrado na reserva")
    public Response buscaReservaPorTresFiltros(String name1, String checkin1, String checkout1){
        return given()
                .header("Accept","application/json")
                .when()
                .log().all()
                .get("booking?firstname="+name1+
                        "&booking?dates%5Bcheckin%5D=="+checkin1+
                        "&booking?dates%5Bcheckout%5D="+checkout1);
    }
    @Step("Exlui Reserva com sucesso")
    public Response ExcluiReservaComSucesso(int id){
        return given()
                .header("Content-Type","application/json")
                .header("Cookie","token=abc123")
                .when()
                .log().all()
                .delete("booking/"+id);
    }








}
