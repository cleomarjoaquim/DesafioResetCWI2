package br.com.restassuredapitesting.tests.booking.tests;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.IsNull.notNullValue;

import br.com.restassuredapitesting.base.BaseTest;
import br.com.restassuredapitesting.suites.AcceptanceTests;
import br.com.restassuredapitesting.suites.AllTests;
import br.com.restassuredapitesting.suites.SmokeTests;
import br.com.restassuredapitesting.tests.auth.request.PostAuthRequest;
import br.com.restassuredapitesting.tests.booking.request.GetBookingRequest;
import br.com.restassuredapitesting.tests.booking.request.PostBookingRequest;
import com.github.javafaker.Faker;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;
@Feature("Feacture de criação de reservas")

public class PostBookingTest extends BaseTest {
    PostBookingRequest postBookingRequest = new PostBookingRequest();
    GetBookingRequest getBookingRequest = new GetBookingRequest();
    PostAuthRequest postAuthRequest = new PostAuthRequest();
    Faker javafaker = new Faker();


    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, SmokeTests.class})
    @DisplayName("Valida a criação de reserva")
    public void validacreateBookingTest() {
        String firstname = javafaker.dragonBall().character();
        String lastname = javafaker.funnyName().name();

        postBookingRequest.createBookingRequest("application/json",firstname,lastname)
                .then()
                .log().all()
                .statusCode(200)
                .body("bookingid",notNullValue())
                .body("booking.firstname",is(firstname))
                .body("booking.lastname",is(lastname));
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, br.com.restassuredapitesting.suites.AcceptanceTests.class})
    @DisplayName("Valida o retorno do ERRO 418 com accept Invalido na criação de reserva")
    public void validaAcceptInvalidoTest() {
        String firstname = javafaker.dragonBall().character();
        String lastname = javafaker.funnyName().name();

        postBookingRequest.createBookingRequest("applcation/json",firstname,lastname)
                .then()
                .log().all()
                .statusCode(418);
    }
    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, AcceptanceTests.class})
    @DisplayName("Valida o retorno do ERRO 500 com payload da reserva inválido ")
    public void validaCreateBookingInvalidPayLoad() {
        String firstname = javafaker.dragonBall().character();
        String lastname = javafaker.funnyName().name();

        postBookingRequest.createBookingInvalidPayLoadRequest("application/json",firstname,lastname)
                .then()
                .log().all()
                .statusCode(500);
    }
    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, SmokeTests.class})
    @DisplayName("Valida o  a criação de duas reservas em sequência")

    public void validaTwoCreateBookingTest() {
        String firstname = javafaker.dragonBall().character();
        String lastname = javafaker.funnyName().name();

        postBookingRequest.createBookingRequest("application/json",firstname,lastname)
                .then()
                .log().all()
                .statusCode(200)
                .body("bookingid",notNullValue())
                .body("booking.firstname",is(firstname))
                .body("booking.lastname",is(lastname));

        String firstname2 = javafaker.dragonBall().character();
        String lastname2 = javafaker.funnyName().name();

        postBookingRequest.createBookingRequest("application/json",firstname2,lastname2)
                .then()
                .log().all()
                .statusCode(200)
                .body("bookingid",notNullValue())
                .body("booking.firstname",is(firstname2))
                .body("booking.lastname",is(lastname2));
    }
}

