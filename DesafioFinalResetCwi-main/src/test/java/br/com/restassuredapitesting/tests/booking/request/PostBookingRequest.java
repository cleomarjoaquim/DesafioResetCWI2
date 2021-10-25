package br.com.restassuredapitesting.tests.booking.request;

import br.com.restassuredapitesting.tests.auth.request.PostAuthRequest;
import br.com.restassuredapitesting.tests.auth.request.payLoads.AuthPayLoads;
import br.com.restassuredapitesting.tests.booking.payloads.BookingPayLoads;
import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PostBookingRequest {
    Faker javaFaker = new Faker();
    BookingPayLoads bookingPayLoads = new BookingPayLoads();





        public Response createBookingRequest(int id/*, String token*/){

          return       given()
                .when()
                .header("Content-Type","application/json")
                .header("Accept","application/json")
                //.header("Cookie", token)
                .log().all()
                .body(bookingPayLoads.payLoadValidBooking(javaFaker.dragonBall().character(),javaFaker.dragonBall().character()).toString())
                .post("booking/"+id);


   }


}
