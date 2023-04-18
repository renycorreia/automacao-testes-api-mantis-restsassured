package com.mantisapi.requests.user;

import com.mantisapi.bases.RequestRestBase;
import io.restassured.http.Method;

public class DeleteUserRequest extends RequestRestBase {
    public DeleteUserRequest(int userId){
        requestService = "/users/"+userId;
        method = Method.DELETE;
    }
}
