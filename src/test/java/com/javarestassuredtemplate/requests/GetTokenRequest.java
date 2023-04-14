package com.javarestassuredtemplate.requests;

import com.javarestassuredtemplate.GlobalParameters;
import com.javarestassuredtemplate.bases.RequestRestBase;
import io.restassured.http.Method;

public class GetTokenRequest extends RequestRestBase {
    public GetTokenRequest(String usuario, String senha){
        url= GlobalParameters.URL_TOKEN;
        requestService = "token/"+usuario+"/"+senha;
        method = Method.GET;
    }
}
