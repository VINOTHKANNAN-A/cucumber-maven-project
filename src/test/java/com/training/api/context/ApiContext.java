package com.training.api.context;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
 
public class ApiContext {
 
    private String baseUri;
    private RequestSpecification request;
    private Response response;
 
    public String getBaseUri() { return baseUri; }
    public void setBaseUri(String baseUri) { this.baseUri = baseUri; }
 
    public RequestSpecification getRequest() { return request; }
    public void setRequest(RequestSpecification request) { this.request = request; }
 
    public Response getResponse() { return response; }
    public void setResponse(Response response) { this.response = response; }
}