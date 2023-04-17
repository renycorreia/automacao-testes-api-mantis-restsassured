package com.mantisapi.tests;

import com.mantisapi.bases.TestBase;
import com.mantisapi.requests.config.GetConfigRequest;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.*;

public class ConfigTests extends TestBase {
    GetConfigRequest getConfigRequest;

    @Test
    public void buscarConfigPublica(){
        //Parâmetros
        String option = "default_bug_priority";
        int statusCodeEsperado = HttpStatus.SC_OK;

        //Fluxo
        getConfigRequest = new GetConfigRequest(option);
        ValidatableResponse response = getConfigRequest.executeRequest();

        //Asserções
        response.statusCode(statusCodeEsperado);

        response.body("configs", isA(List.class)
                , "configs", hasSize(1)
                , "configs.option", isA(List.class)
                , "configs.option",  is(Collections.singletonList("default_bug_priority"))
                , "configs.option[0]", isA(String.class)
                , "configs.value", isA(List.class)
                , "configs.value",  is(Collections.singletonList(30))
                , "configs.value[0]", isA(Integer.class)
        );
    }

    @Test
    public void buscarConfigInexistente(){
        //Parâmetros
        String option = "default_priority";
        int statusCodeEsperado = HttpStatus.SC_OK;

        //Fluxo
        getConfigRequest = new GetConfigRequest(option);
        ValidatableResponse response = getConfigRequest.executeRequest();

        //Asserções
        response.statusCode(statusCodeEsperado);

        response.body("configs", isA(List.class)
                ,"configs", hasSize(0)
        );
    }
}
