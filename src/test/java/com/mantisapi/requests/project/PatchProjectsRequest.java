package com.mantisapi.requests.project;

import com.mantisapi.bases.RequestRestBase;
import io.restassured.http.Method;

public class PatchProjectsRequest extends RequestRestBase {
    public PatchProjectsRequest(int projectId, Object projectData){
        requestService = "/projects/"+projectId;
        jsonBody = projectData;
        method = Method.PATCH;
    }
}
