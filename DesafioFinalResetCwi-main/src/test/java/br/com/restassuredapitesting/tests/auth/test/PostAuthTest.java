package br.com.restassuredapitesting.tests.auth.test;

import br.com.restassuredapitesting.base.BaseTest;
import br.com.restassuredapitesting.runners.ContractsTests;
import br.com.restassuredapitesting.suites.AllTests;
import br.com.restassuredapitesting.suites.SmokeTests;
import br.com.restassuredapitesting.tests.auth.request.PostAuthRequest;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.hamcrest.core.IsNull.notNullValue;
@Feature("Featute- Autencicação de usuário")

public class PostAuthTest extends BaseTest {
    PostAuthRequest postAuthRequest= new PostAuthRequest();
    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, SmokeTests.class})
    @DisplayName("Retorna token para o usuário")

    public void validaRetornoDeTokenParaUsuario(){
        postAuthRequest.tokenReturn()
                .then()
                .statusCode(200)
                .body("token", notNullValue());

    }
}
