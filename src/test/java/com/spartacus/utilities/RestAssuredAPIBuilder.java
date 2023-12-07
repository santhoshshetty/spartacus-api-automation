package com.spartacus.utilities;

import java.io.IOException;
import java.util.HashMap;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAssuredAPIBuilder {
	private RequestSpecBuilder builder = new RequestSpecBuilder();
	private String method;
	private String url;
	private Response response;

	public Response getResponse() {
		return response;
	}

	public RestAssuredAPIBuilder(String method, String uri) {
		this.url = uri;
		this.method = method;
	}

	public RestAssuredAPIBuilder setBodyParams(String bodyParams) throws IOException {
		builder.setBody(bodyParams);
		return this;
	}

	public RestAssuredAPIBuilder setRequestParams(String reqParams) {
		builder.setBody(reqParams);
		return this;
	}

	public RestAssuredAPIBuilder setHeaderParams(HashMap<String, String> reqParams) {
		builder.addHeaders(reqParams);
		return this;
	}

	public RestAssuredAPIBuilder setQueryParams(HashMap<String, String> reqParams) {
		builder.addQueryParams(reqParams);
		return this;
	}

	public RestAssuredAPIBuilder setPathParams(HashMap<String, String> reqParams) {
		builder.addPathParams(reqParams);
		return this;
	}

	public RestAssuredAPIBuilder executeAPI() {
		RequestSpecification requestSpec = builder.build();
		RequestSpecification request = RestAssured.given();
		request.spec(requestSpec);

		switch (this.method) {
		case APIConstants.GET:
			response = request.get(this.url);
			return this;
		case APIConstants.POST:
			response = request.post(this.url);
			return this;
		case APIConstants.DELETE:
			response = request.delete(this.url);
			return this;
		case APIConstants.PUT:
			response = request.put(this.url);
			return this;
		case APIConstants.HEAD:
			response = request.head(this.url);
			return this;
		case APIConstants.PATCH:
			response = request.patch(this.url);
			return this;
		}
		return null;
	}
}
