package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.base.BaseTest;
import br.com.restassuredapitesting.suites.AcceptanceTests;
import br.com.restassuredapitesting.suites.AllTests;
import br.com.restassuredapitesting.suites.SmokeTests;
import br.com.restassuredapitesting.tests.auth.request.PostAuthRequest;
import br.com.restassuredapitesting.tests.booking.request.GetBookingRequest;
import br.com.restassuredapitesting.tests.booking.request.PutBookingRequest;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.hamcrest.number.OrderingComparison.greaterThan;

public class PutBookingTest extends BaseTest {
    PutBookingRequest putBookingRequest = new PutBookingRequest();
    GetBookingRequest getBookingRequest = new GetBookingRequest();
    PostAuthRequest postAuthRequest = new PostAuthRequest();

    @Feature("Feature - Atualização de Reservas")
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, br.com.restassuredapitesting.suites.SmokeTests.class})
    @DisplayName(" Valida a alteraração de uma reserva somente utilizando o token")

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
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, SmokeTests.class})
    @DisplayName("Valida a alteraração de uma reserva somente utilizando o Basic auth")
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
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, br.com.restassuredapitesting.suites.AcceptanceTests.class})
    @DisplayName("Valida que não haja  alterada a reserva com token inválido")

    public void validarTentativaDeAlterarComTokenInvalido() {
        int primeiroId = getBookingRequest.bookingReturnIds("","","","","","")
                .then()
                .statusCode(200)
                .extract()
                .path("[0].bookingid");
        putBookingRequest.tentaAlterar(primeiroId, "215142") //Token inválido
                .then()
                .log().all()
                .statusCode(403);
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, AcceptanceTests.class})
    @DisplayName("Valida que não haja alteração sem o envio do token")
    public void validarTentativaDeAlterarSemEnviarToken() {
        int primeiroId = getBookingRequest.bookingReturnIds("","","","","","")
                .then()
                .statusCode(200)
                .extract()
                .path("[0].bookingid");
        putBookingRequest.tentaAlterar(primeiroId,"") //Sem Token
                .then()
                .log().all()
                .statusCode(403);
    }
}
