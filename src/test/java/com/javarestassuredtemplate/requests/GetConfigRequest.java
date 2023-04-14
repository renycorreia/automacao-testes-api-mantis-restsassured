package com.javarestassuredtemplate.requests;

import com.javarestassuredtemplate.bases.RequestRestBase;
import io.restassured.http.Method;

public class GetConfigRequest extends RequestRestBase {
    public GetConfigRequest(String option){
        requestService = "/config?option="+option;
        method = Method.GET;
    }
}
