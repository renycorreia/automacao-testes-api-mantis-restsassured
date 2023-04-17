package com.mantisapi.requests.project;

import com.mantisapi.bases.RequestRestBase;
import io.restassured.http.Method;

public class GetProjectsRequest extends RequestRestBase {
    public GetProjectsRequest(){
        requestService = "/projects";
        method = Method.GET;
    }
}
