package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.base.BaseTest;
import br.com.restassuredapitesting.runners.SmokeTests;
import br.com.restassuredapitesting.suites.AllTests;
import br.com.restassuredapitesting.tests.auth.request.PostAuthRequest;
import br.com.restassuredapitesting.tests.booking.request.GetBookingRequest;
import br.com.restassuredapitesting.tests.booking.request.PutBookingRequest;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.http.Cookie;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.hamcrest.number.OrderingComparison.greaterThan;

public class PutBookingTest extends BaseTest {
    PutBookingRequest putBookingRequest = new PutBookingRequest();
    GetBookingRequest getBookingRequest = new GetBookingRequest();
    PostAuthRequest postAuthRequest = new PostAuthRequest();

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class})
    @DisplayName("Alterar Uma reserva somente utilizando o token")


    @Feature("Feature - Atualização de Reservas")

    public void validarAlteracaoDeUmaReservaUtilizandoToken() {
        int primeiroId = getBookingRequest.bookingReturnIds("","","","","","")
                .then()
                .statusCode(200)
                .extract()
                .path("[0].bookingid");

        putBookingRequest.updateBookingToken(primeiroId, postAuthRequest.getToken())
                .then()
                .log().all()
                .statusCode(200)
                .body("size()", greaterThan(0));

    }

    @Test
    public void validaAlteracaoPorBasicauth() {

        int primeiroId = getBookingRequest.bookingReturnIds("","","","","","")
                .then()
                .statusCode(200)
                .extract()
                .path("[0].bookingid");


        putBookingRequest.atualizaReservaBasicauth(primeiroId, postAuthRequest.getToken())
                .then()
                .log().all()
                .statusCode(200);

    }

    @Test
    public void validarTentaAtualizarSemToken() {
        int primeiroId = getBookingRequest.bookingReturnIds("","","","","","")
                .then()
                .statusCode(200)
                .extract()
                .path("[0].bookingid");

        putBookingRequest.tentaAtualizarSemToken()
                .then()
                .log().all()
                .statusCode(403)
                .body("size()", greaterThan(0));

    }

    @Test
    @Category({AllTests.class, SmokeTests.class})
    @DisplayName("Garantir o que não seja alterada a reserva com token invalido")
    public void validarTentativaDeAlterarComTokenInvalido() {
        int primeiroId = getBookingRequest.bookingReturnIds("","","","","","")
                .then()
                .statusCode(200)
                .extract()
                .path("[0].bookingid");
        putBookingRequest.tentaAlterarComTokenInvalido(primeiroId, "215142") //Token inválido
                .then()
                .log().all()
                .statusCode(403);

    }
    @Test
    @Category({AllTests.class, SmokeTests.class})
    @DisplayName("Garantir o que não seja alterada sem o envio do token")
    public void validarTentativaDeAlterarComSemEnviarToken() {
        int primeiroId = getBookingRequest.bookingReturnIds("","","","","","")
                .then()
                .statusCode(200)
                .extract()
                .path("[0].bookingid");
        putBookingRequest.tentaAtualizarSemToken(primeiroId) //Sem Token
                .then()
                .log().all()
                .statusCode(403);

    }

}
