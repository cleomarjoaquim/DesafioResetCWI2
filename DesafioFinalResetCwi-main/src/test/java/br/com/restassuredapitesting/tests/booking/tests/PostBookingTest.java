package br.com.restassuredapitesting.tests.booking.tests;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.IsNull.notNullValue;

import br.com.restassuredapitesting.base.BaseTest;
import br.com.restassuredapitesting.runners.AcceptanceTest;
import br.com.restassuredapitesting.runners.SmokeTests;
import br.com.restassuredapitesting.suites.AllTests;
import br.com.restassuredapitesting.tests.auth.request.PostAuthRequest;
import br.com.restassuredapitesting.tests.booking.payloads.BookingPayLoads;
import br.com.restassuredapitesting.tests.booking.request.GetBookingRequest;
import br.com.restassuredapitesting.tests.booking.request.PostBookingRequest;
import com.github.javafaker.Faker;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class PostBookingTest extends BaseTest {
    PostBookingRequest postBookingRequest = new PostBookingRequest();
    GetBookingRequest getBookingRequest = new GetBookingRequest();
    PostAuthRequest postAuthRequest = new PostAuthRequest();
    Faker javafaker = new Faker();


    @Test
    @Category({AllTests.class, AcceptanceTest.class})
    @DisplayName("Garantir o  a criação de reserva")
    public void validaPostBookingTest() {
        String firstname = javafaker.dragonBall().character();
        String lastname = javafaker.funnyName().name();

        postBookingRequest.createBookingRequest(firstname,lastname)
                .then()
                .log().all()
                .statusCode(200)
                .body("bookingid",notNullValue())
                .body("booking.firstname",is(firstname))
                .body("booking.lastname",is(lastname));

    }



    }

