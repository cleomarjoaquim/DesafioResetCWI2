package br.com.restassuredapitesting.tests.booking.request;

import br.com.restassuredapitesting.tests.booking.payloads.BookingPayLoads;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PutBookingRequest {
    BookingPayLoads bookingPayLoads = new BookingPayLoads();


    @Step("Atualiza uma reserva especifica com um parametro token")
    public Response updateBookingToken(int id, String token){
        return given()
                .header("Content-Type","application/json")
                .header("Accept","application/json")
                .header("Cookie", token)
                .when()
                .body(bookingPayLoads.payLoadValidBooking().toString())
                .put("booking/"+id);

    }
}

