package com.mantisapi.requests.user;

import com.mantisapi.bases.RequestRestBase;
import io.restassured.http.Method;

public class GetUserLogadoRequest extends RequestRestBase {
    public GetUserLogadoRequest(){
        requestService = "/users/me";
        method = Method.GET;
    }
}
