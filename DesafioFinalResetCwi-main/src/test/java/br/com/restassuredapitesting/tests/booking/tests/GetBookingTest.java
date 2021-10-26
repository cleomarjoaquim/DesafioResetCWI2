package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.base.BaseTest;
import br.com.restassuredapitesting.runners.AcceptanceTest;
import br.com.restassuredapitesting.runners.SmokeTests;
import br.com.restassuredapitesting.suites.AllTests;
import br.com.restassuredapitesting.suites.ContractTests;
import br.com.restassuredapitesting.tests.booking.request.GetBookingRequest;
import br.com.restassuredapitesting.tests.booking.request.PostBookingRequest;
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
    PostBookingRequest postBookingRequest= new PostBookingRequest();

    GetBookingRequest getBookingRequest = new GetBookingRequest();
    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class})
    @DisplayName("Listar IDs reservas")
    public void validaListagemDeIdsDasReservas(){
        getBookingRequest.bookingReturnIds("","","","","","")
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
        getBookingRequest.bookingReturnIds("","","","","","")
                .then()
                .log().all()
                .statusCode(200)
                .body(matchesJsonSchema(new File(Utils.getSchemaBasePath("booking", "bookings"))));



    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, SmokeTests.class})
    @DisplayName("Valida o  retorno da reservas por ID")


    public void validaReservaPorId(){
        int primeiroId = getBookingRequest.bookingReturnIds("","","","","","")
                .then()
                .statusCode(200)
                .extract()
                .path("[0].bookingid");


        getBookingRequest.buscaReservaPorIDRequest(primeiroId)
                .then()
                .log().all()
                .statusCode(200);


    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, SmokeTests.class})
    @DisplayName("Valida o  retorno da reservas por Nome")

    public void validaComBuscaDeReservaPorNome(){
        String firstname = javaFaker.dragonBall().character();
        String lastname = javaFaker.funnyName().name();
        postBookingRequest.createBookingRequest("application/json",firstname,lastname).then().statusCode(200);

        getBookingRequest.bookingReturnIds("firstname",firstname,"","","","")
                .then()
                .log().all()
                .statusCode(200)
                .body("size()",greaterThan(0));
    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, SmokeTests.class})
    @DisplayName("Valida o  retorno da reservas por Sobrenome")
    public void validaComBuscaDeReservaPorSobrenome(){

        getBookingRequest.bookingReturnIds("lastname","Brn","","","","")
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, SmokeTests.class})
    @DisplayName("Valida o  retorno da reservas por data de checkin")
    public void validaComBuscaDeReservaPorCheckin(){


        getBookingRequest.bookingReturnIds("checkin","2020-10-20","","","","")
                .then()
                .log().all()
                .statusCode(200);

    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, SmokeTests.class})
    @DisplayName("Valida o  retorno da reservas por data de checkout")

    public void validaComBuscaDeReservaPorCheckout(){

        getBookingRequest.bookingReturnIds("checkout","2021-09-03","","","","")
                .then()
                .log().all()
                .statusCode(200);

    }
    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, SmokeTests.class})
    @DisplayName("Valida o  retorno da reservas por nome, data de checkin e data de checkout")

    public void validaComBuscaDeReservaPorNomeCheckinCheckout(){

        getBookingRequest.bookingReturnIds("paulo","","checkin","2021-07-10","checkout","2021-07-10")
                .then()
                .log().all()
                .statusCode(200);

    }
    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, AcceptanceTest.class})
    @DisplayName("Valida o  retorno Do ERRO 500 com filtro mal formatado")

    public void validaComfiltroMalFormatado(){


        getBookingRequest.bookingReturnIds("firstname","Pele","lastname","paulo","checkout","25/10/2021")
                .then()
                .log().all()
                .statusCode(500);

    }






}
