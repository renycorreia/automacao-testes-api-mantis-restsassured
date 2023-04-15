package com.mantisapi.requests;

import com.mantisapi.bases.RequestRestBase;
import io.restassured.http.Method;

public class GetPetRequest extends RequestRestBase {
    public GetPetRequest(int petId){
        requestService = "/pet/"+petId;
        method = Method.GET;
    }
}
