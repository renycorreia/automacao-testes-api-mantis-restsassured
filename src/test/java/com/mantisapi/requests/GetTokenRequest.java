package com.mantisapi.requests;

import com.mantisapi.GlobalParameters;
import com.mantisapi.bases.RequestRestBase;
import io.restassured.http.Method;

public class GetTokenRequest extends RequestRestBase {
    public GetTokenRequest(String usuario, String senha){
        url= GlobalParameters.URL_TOKEN;
        requestService = "token/"+usuario+"/"+senha;
        method = Method.GET;
    }
}
