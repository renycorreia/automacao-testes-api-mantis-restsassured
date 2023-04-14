package com.javarestassuredtemplate.tests;

import com.javarestassuredtemplate.bases.TestBase;
import com.javarestassuredtemplate.requests.GetConfigRequest;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.hamcrest.Matchers.*;

public class GetConfigTests extends TestBase {
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

        response.body("configs", hasSize(1)
                        , "configs.option",  is(Arrays.asList("default_bug_priority"))
                        , "configs.value",  is(Arrays.asList(30))
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

        response.body("configs", hasSize(0)
                    );
    }
}
