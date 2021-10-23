package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.base.BaseTest;
import br.com.restassuredapitesting.tests.booking.request.GetBookingRequest;
import br.com.restassuredapitesting.tests.booking.request.ReservaEspecificaRequest;
import br.com.restassuredapitesting.utils.Utils;
import org.junit.Test;

import java.io.File;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.IsNull.notNullValue;

public class ReservaEspecificaTest extends BaseTest {

    ReservaEspecificaRequest reservaEspecificaRequest = new ReservaEspecificaRequest();
    GetBookingRequest getBookingRequests = new GetBookingRequest();
    @Test
    public void exibeListagemDeIdsDasReservas() {
        getBookingRequests.bookingReturnIds()
                .then()
                .log().all()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }
    @Test
    public void validaSchemaDaListagemDeReservas(){
        getBookingRequests.bookingReturnIds()
                .then()
                .log().all()
                .statusCode(200)
                .body(matchesJsonSchema(new File(Utils.getSchemaBasePath("booking", "bookings"))));
    }



    @Test

    public void validaReservaEspecifica(){

        reservaEspecificaRequest.reservaEspecificaRequest(39)
                .then()
                .log().all()
                .statusCode(200);


    }
    @Test

    public void validaIdComBuscaReservaPorNome(){

        reservaEspecificaRequest.buscaReservaPorNomeRequest("Cristiano")
                .then()
                .log().all()
                .statusCode(200);
    }
    @Test
    public void validaIdComBuscaDeReservaPorSobrenome(){

        reservaEspecificaRequest.buscaReservaPorSobrenomeRequest("Brown")
                .then()
                .log().all()
                .statusCode(200);
    }

   @Test
    public void validaIdComBuscaDeReservaPorCheckin(){

        reservaEspecificaRequest.buscaReservaPorCheckin("2021-07-10")
                .then()
                .log().all()
                .statusCode(200);

    }

    @Test
    public void validaIdComBuscaDeReservaPorCheckout(){

        reservaEspecificaRequest.buscaReservaPorCheckout("2021-09-03")
                .then()
                .log().all()
                .statusCode(200);

    }
    @Test
    public void validaIdComBuscaDeReservaPorTresFiltros(){

        reservaEspecificaRequest.buscaReservaPorTresFiltros("Eric","2021-07-10","2021-09-03")
                .then()
                .log().all()
                .statusCode(200);

    }

    @Test
    public void exluiReservaComSucesso(){
        reservaEspecificaRequest.ExcluiReservaComSucesso(39)
                .then()
                .log().all()
                .statusCode(200);

    }





}
