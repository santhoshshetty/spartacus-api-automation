package com.spartacus.utilities;

import java.util.HashMap;

public class TestStepParams {

	HashMap<String, String> queryParams;

	HashMap<String, String> pathParams;

	HashMap<String, String> headerParams;

	HashMap<String, String> globalParams;

	String bodyParams;

	TestStepAssertion stepAssertion;

	public HashMap<String, String> getQueryParams() {
		return queryParams;
	}

	public TestStepParams setQueryParams(HashMap<String, String> queryParams) {
		this.queryParams = queryParams;
		return this;
	}

	public HashMap<String, String> getGlobalParams() {
		return globalParams;
	}

	public TestStepParams setGlobalParams(HashMap<String, String> globalParams) {
		this.globalParams = globalParams;
		return this;
	}

	public HashMap<String, String> getPathParams() {
		return pathParams;
	}

	public TestStepParams setPathParams(HashMap<String, String> pathParams) {
		this.pathParams = pathParams;
		return this;
	}

	public HashMap<String, String> getHeaderParams() {
		return headerParams;
	}

	public TestStepParams setHeaderParams(HashMap<String, String> headerParams) {
		this.headerParams = headerParams;
		return this;
	}

	public String getBodyParams() {
		return bodyParams;
	}

	public TestStepParams setBodyParams(String bodyParams) {
		this.bodyParams = bodyParams;
		return this;
	}

	public TestStepAssertion getStepAssertion() {
		return stepAssertion;
	}

	public TestStepParams setStepAssertion(TestStepAssertion stepAssertion) {
		this.stepAssertion = stepAssertion;
		return this;
	}

}
