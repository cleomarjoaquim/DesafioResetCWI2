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

        public Response createBookingRequest(String accept,String firstname, String lastName){

          return       given()
                .when()
                .header("Content-Type","application/json")
                .header("Accept",accept)
                .log().all()
                .body(bookingPayLoads.payLoadValidBooking(firstname,lastName).toString())
                .post("booking");
   }
    public Response createBookingInvalidPayLoadRequest(String accept,String firstname, String lastName){

        return       given()
                .when()
                .header("Content-Type","application/json")
                .header("Accept",accept)
                .log().all()
                .body(bookingPayLoads.addPayLoadinvalidBooking(firstname,lastName).toString())
                .post("booking");
    }

    public Response createBookingAddParametersRequest(String accept,String firstname, String lastName){

        return       given()
                .when()
                .header("Content-Type","application/json")
                .header("Accept",accept)
                .log().all()
                .body(bookingPayLoads.payLoadValidBooking(firstname,lastName).toString())
                .post("booking");
    }
   /* public Response createSequenciBookingRequest(String accept,String firstname, String lastName){

        return       given()
                .when()
                .header("Content-Type","application/json")
                .header("Accept",accept)
                .log().all()
                .body(bookingPayLoads.payLoadValidBooking(firstname,lastName).toString())
                .body(bookingPayLoads.payLoadValidBooking(firstname,lastName).toString())

                .post("booking");
*/
    }





}
