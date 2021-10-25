package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.base.BaseTest;
import br.com.restassuredapitesting.runners.SmokeTests;
import br.com.restassuredapitesting.suites.AllTests;
import br.com.restassuredapitesting.suites.ContractTests;
import br.com.restassuredapitesting.tests.booking.request.GetBookingRequest;
import br.com.restassuredapitesting.utils.Utils;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.File;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.Matchers.greaterThan;
@Feature("Feature- Retorno de reservas")


public class GetBookingTest extends BaseTest {

    GetBookingRequest getBookingRequest = new GetBookingRequest();
    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class})
    @DisplayName("Listar IDs reservas")
    public void validaListagemDeIdsDasReservas(){
        getBookingRequest.bookingReturnIds()
                       .then()
                        .log().all()
                        .statusCode(200)
                        .body("size()", greaterThan(0));

    }


    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, ContractTests.class})
    @DisplayName("Garantir o schema do retorno da listagem de reservas")

    public void validaSchemaDaListagemDeReservas(){
        getBookingRequest.bookingReturnIds()
                .then()
                .log().all()
                .statusCode(200)
                .body(matchesJsonSchema(new File(Utils.getSchemaBasePath("booking", "bookings"))));



    }
    @Test
    @Category({AllTests.class, SmokeTests.class})
    @DisplayName("Garantir o  retorno da reservas por ID")


    public void validaReservaPorId(){

        getBookingRequest.buscaReservaPorIDRequest(29)
                .then()
                .log().all()
                .statusCode(200);


    }
    @Test
    @Category({AllTests.class, SmokeTests.class})
    @DisplayName("Garantir o  retorno da reservas por Nome")

    public void validaComBuscaDeReservaPorNome(){

        getBookingRequest.buscaReservaPorNomeRequest("Crisiano")
                .then()
                .log().all()
                .statusCode(200);
    }
    @Test
    @Category({AllTests.class, SmokeTests.class})
    @DisplayName("Garantir o  retorno da reservas por Sobrenome")
    public void validaComBuscaDeReservaPorSobrenome(){

        getBookingRequest.buscaReservaPorSobrenomeRequest("Brown")
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    @Category({AllTests.class, SmokeTests.class})
    @DisplayName("Garantir o  retorno da reservas por data de checkin")
    public void validaComBuscaDeReservaPorCheckin(){


        getBookingRequest.buscaReservaPorCheckin("checkin","2020-10-20")
                .then()
                .log().all()
                .statusCode(200);

    }
    @Test
    @Category({AllTests.class, SmokeTests.class})
    @DisplayName("Garantir o  retorno da reservas por data de checkout")

    public void validaComBuscaDeReservaPorCheckout(){

        getBookingRequest.buscaReservaPorCheckout("checkout","2021-09-03")
                .then()
                .log().all()
                .statusCode(200);

    }
    @Test
    @Category({AllTests.class, SmokeTests.class})
    @DisplayName("Garantir o  retorno da reservas por nome, data de checkin e data de checkout")

    public void validaComBuscaDeReservaPorNomeCheckinCheckout(){

        getBookingRequest.buscaReservaPorTresFiltros("E","2021-07-10","2021-09-03")
                .then()
                .log().all()
                .statusCode(200);

    }
    @Test
    @Category({AllTests.class, SmokeTests.class})
    @DisplayName("Garantir o  retorno da reservas por data de checkin")

    public void validaComfiltroMalFormatado(){


        getBookingRequest.filtroMalFormatado("")
                .then()
                .log().all()
                .statusCode(200);

    }






}
