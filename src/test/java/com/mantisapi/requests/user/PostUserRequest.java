package com.mantisapi.requests.user;

import com.mantisapi.bases.RequestRestBase;
import io.restassured.http.Method;

public class PostUserRequest extends RequestRestBase {
    public PostUserRequest(Object user){
        requestService = "/users";
        jsonBody = user;
        method = Method.POST;
    }
}
