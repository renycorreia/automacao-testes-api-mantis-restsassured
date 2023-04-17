package com.mantisapi.requests.project;

import com.mantisapi.bases.RequestRestBase;
import io.restassured.http.Method;

public class PostProjectsRequest extends RequestRestBase {
    public PostProjectsRequest(Object projectData){
        requestService = "/projects";
        jsonBody = projectData;
        method = Method.POST;
    }
}
