package br.com.restassuredapitesting.tests.auth.request.payLoads;

import org.json.JSONObject;

public class AuthPayLoads {
    public JSONObject jsonAuthLogin() {
        JSONObject payLoadLogin = new JSONObject();

        payLoadLogin.put("username", "admin");
        payLoadLogin.put("password", "password123");
        return payLoadLogin;

    }
}