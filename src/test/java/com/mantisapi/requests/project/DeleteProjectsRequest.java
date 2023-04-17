package com.mantisapi.requests.project;

import com.mantisapi.bases.RequestRestBase;
import io.restassured.http.Method;

public class DeleteProjectsRequest extends RequestRestBase {
    public DeleteProjectsRequest(int projectId){
        requestService = "/projects/"+projectId;
        method = Method.DELETE;
    }
}
