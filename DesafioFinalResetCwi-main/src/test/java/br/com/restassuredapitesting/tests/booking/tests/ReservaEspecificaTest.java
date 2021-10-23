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

        reservaEspecificaRequest.reservaEspecificaRequest(24)
                .then()
                .log().all()
                .statusCode(200);


    }
}
