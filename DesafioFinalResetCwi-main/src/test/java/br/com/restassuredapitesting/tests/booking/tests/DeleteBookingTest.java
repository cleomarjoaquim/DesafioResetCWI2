package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.base.BaseTest;
import br.com.restassuredapitesting.runners.SmokeTests;
import br.com.restassuredapitesting.suites.AllTests;
import br.com.restassuredapitesting.tests.auth.request.PostAuthRequest;
import br.com.restassuredapitesting.tests.booking.request.DeleteBookingRequest;
import br.com.restassuredapitesting.tests.booking.request.GetBookingRequest;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DeleteBookingTest extends BaseTest {
    DeleteBookingRequest deleteBookingRequest = new DeleteBookingRequest();
    GetBookingRequest getBookingRequest = new GetBookingRequest();
    PostAuthRequest postAuthRequest = new PostAuthRequest();

    @Test
    @Category({AllTests.class, SmokeTests.class})
    @DisplayName("Garantir a exclusao de reserva com token valido")
    public void validarExlusaoDeReservaComTokenValido() {
        int primeiroId = getBookingRequest.bookingReturnIds("","","","","","")
                .then()
                .statusCode(200)
                .extract()
                .path("[0].bookingid");

        deleteBookingRequest.excluiReserva(primeiroId, postAuthRequest.getToken())
                .then()
                .log().all()
                .statusCode(201);
    }

    @Test
    @Category({AllTests.class, SmokeTests.class})
    @DisplayName("Garantir a não exclusão, caso não tenha a autorização")
    public void validarExlusaoSemAutorização() {
        int primeiroId = getBookingRequest.bookingReturnIds("","","","","","")
                .then()
                .statusCode(200)
                .extract()
                .path("[0].bookingid");
        deleteBookingRequest.excluiReserva(primeiroId, "215142") //Token inválido
                .then()
                .log().all()
                .statusCode(403);



    }
    @Test
    @Category({AllTests.class, SmokeTests.class})
    @DisplayName("Garantir a tentativa de exclusao de reserva que não existe")
    public void validaexcluiReservaQueNaoExiste() {
       /* int primeiroId = getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .extract()
                .path("[0].bookingid");*/

        deleteBookingRequest.excluiReservaQueNaoExiste(516, postAuthRequest.getToken())
                .then()
                .log().all()
                .statusCode(405);
    }

}
