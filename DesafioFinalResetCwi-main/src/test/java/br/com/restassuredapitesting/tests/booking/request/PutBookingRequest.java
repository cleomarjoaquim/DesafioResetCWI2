package br.com.restassuredapitesting.tests.booking.request;

import br.com.restassuredapitesting.tests.auth.request.PostAuthRequest;
import br.com.restassuredapitesting.tests.auth.request.payLoads.AuthPayLoads;
import br.com.restassuredapitesting.tests.booking.payloads.BookingPayLoads;
import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PutBookingRequest {
    BookingPayLoads bookingPayLoads = new BookingPayLoads();
    Faker javaFaker = new Faker();
    GetBookingRequest getBookingRequest = new GetBookingRequest();
    AuthPayLoads authPayLoads = new AuthPayLoads();
    PostAuthRequest postAuthRequest = new PostAuthRequest();


    @Step("Atualiza uma reserva especifica com um parametro token")
    public Response updateBookingToken(int id, String token) {
        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Cookie", token)
                .when()
                .body(bookingPayLoads.payLoadValidBooking(javaFaker.dragonBall().character(), javaFaker.dragonBall().character()).toString())
                .put("booking/" + id);

    }


    @Step("Atualiza uma reserva específica com Basic auth")

    public Response atualizaReservaBasicauth(int id, String token) {
        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Cookie", token)
                .header("Authorisation","BasicYWRtaW46cGFzc3dvcmQxMjM=")
                .when()
                .body(bookingPayLoads.payLoadValidBooking("Maradona","Pele").toString())
                .put("booking/"+id);
    }
    @Step("Tenta alterar uma reserva sem token enviado")
    public Response tentaAtualizarSemToken(int id ) {
        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                //.header("Cookie")
                .when()
                .body(bookingPayLoads.payLoadValidBooking("Maradona","Pele").toString())
                .put("booking/" + id);

    }
    @Step("Tenta alterar reserva com token inválido")
    public Response tentaAlterarComTokenInvalido(int id, String token) {
        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Cookie", token)
                .when()
                .body(bookingPayLoads.payLoadValidBooking(javaFaker.dragonBall().character(), javaFaker.dragonBall().character()).toString())
                .put("booking/" + id);

    }
    @Step("Tenta alterar reserva sem enviar o token")
    public Response tentaAlterarComSemEnviarToken(int id) {
        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                //.header("Cookie", token)
                .when()
                .body(bookingPayLoads.payLoadValidBooking(javaFaker.dragonBall().character(), javaFaker.dragonBall().character()).toString())
                .put("booking/" + id);

    }




}

