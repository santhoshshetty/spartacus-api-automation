package com.spartacus.utilities;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aventstack.extentreports.Status;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class APIHelper extends TestResultUtils {

	private final Logger LOG = LoggerFactory.getLogger(APIHelper.class);

	public String baseProjectPath;
	public AssertionHelper assertionUtil;
	public PropertyUtils utils;
	public PropertyUtils globalParamsUtils;
	public PropertyUtils assertionUtils;
	public PropertyUtils collectionUtils;
	public PropertyUtils payLoadUtils;
	public String baseURL;
	public String baseURLToken;
	public String loginURLToken;
	public boolean isLogIn;

	public HashMap<Boolean, ArrayList<String>> assertionObj;
	public HashMap<String, String> globalParams;
	public HashMap<String, String> payLoads;
	public HashMap<String, String> executionData;
	public SetRequestParams reqParamsObj;
	public Response response;
	public ArrayList<String> assertionResults;
	public TestStepParams testStepParams;
	public String payLoadPath;

	public void InitAPIHelper() {
		baseProjectPath = System.getProperty("user.dir");
		assertionUtil = new AssertionHelper();
		utils = new PropertyUtils(baseProjectPath.concat("/src/test/resources/properties/config.property"));
		collectionUtils = new PropertyUtils(
				baseProjectPath.concat("/src/test/resources/properties/collection.property"));
		globalParamsUtils = new PropertyUtils(
				baseProjectPath.concat("/src/test/resources/properties/globalparams.property"));
		payLoadUtils = new PropertyUtils(baseProjectPath.concat("/src/test/resources/properties/payload.property"));
		assertionUtils = new PropertyUtils(baseProjectPath.concat("/src/test/resources/properties/assertion.property"));
		baseURL = utils.getProperty("baseURL");
		baseURLToken = utils.getProperty("baseURLToken");
		loginURLToken = utils.getProperty("loginURLToken");
		payLoadPath = baseProjectPath.concat("\\src\\test\\resources\\payloads\\");
		globalParams = new HashMap<String, String>();
		executionData = new HashMap<String, String>();

	}

	public HashMap<String, String> setContentTypeParam() {
		return getRequestObj().setReqParam("Content-Type", "application/json").returnParamsObj();
	}

	public HashMap<String, String> setUserTypeHeaderParams() {
		String bearer = "bearer ".concat(globalParams.get("bearerToken"));
		return getRequestObj().setReqParam("Content-Type", "application/json").setReqParam("Authorization", bearer)
				.returnParamsObj();
	}

	public HashMap<String, String> setUserTypePathParams() {
		if (isLogIn) {
			return getRequestObj().setReqParam("usertype", "current").returnParamsObj();
		} else {
			return getRequestObj().setReqParam("usertype", "anonymous").returnParamsObj();
		}
	}

	public SetRequestParams getRequestObj() {
		return new SetRequestParams();
	}

	public String getPayLoadFromFile(String path) {
		try {
			String payLoad = new String(Files.readAllBytes(Paths.get(path)));
			return payLoad;
		} catch (Exception ex) {
			return null;
		}
	}

	public void setGlobalParams(Response response, String path, String key) {
		try {
			globalParams.put(key, JsonPath.from(response.getBody().asString()).getString(path));
		} catch (Exception ex) {
			LOG.info("Exception occurred in setting the global params");
		}
	}

	public void logAssertionResult(boolean status, String message) {
		if (status) {
			LOG.info("Assertions on the field: " + message + " is success");
		} else {
			LOG.info("Assertions on the field: " + message + " is failure");
		}
	}

	public TestStepAssertion extractAssertionValues(String assertionProperty) {
		String assertionRecord = assertionUtils.getProperty(assertionProperty);
		TestStepAssertion stepAssertion = new TestStepAssertion();
		ArrayList<AssertionRecord> listRecords = new ArrayList<AssertionRecord>();
		HashMap<String, ArrayList<AssertionRecord>> records = new HashMap<String, ArrayList<AssertionRecord>>();
		StringTokenizer strToken;
		strToken = new StringTokenizer(assertionRecord, "-");
		String record = strToken.nextToken();
		String value = strToken.nextToken();
		strToken = new StringTokenizer(value, ";");
		while (strToken.hasMoreTokens()) {
			LinkedHashMap<String, AssertionValues> assertVal = new LinkedHashMap<String, AssertionValues>();
			AssertionRecord assertRecord = new AssertionRecord();
			StringTokenizer str = new StringTokenizer(strToken.nextToken(), "|");
			AssertionValues assertValues = new AssertionValues();
			String assertType = str.nextToken();
			assertValues.setAssertOnField(str.nextToken());
			assertValues.setExpected(str.nextToken());
			assertValues.setActual(str.nextToken());
			assertVal.put(assertType, assertValues);
			assertRecord.setAssertType(assertType);
			assertRecord.setAssertionRecord(assertVal);
			listRecords.add(assertRecord);
		}
		records.put(record, listRecords);
		stepAssertion.setValidatorType(record);
		stepAssertion.setAssertionObj(records);
		return stepAssertion;
	}

	// Use in case of assertions
	public void logResultAssertion(Response response, String message, ArrayList<String> assertions) {
		LOG.info(message + response.getBody().asString());
		LOG.info(message + response.getHeaders());
		LOG.info("Detailed Info..");
		LOG.info(message + response.then().log().all());
		if (assertions == null || assertions.isEmpty()) {
			logger.log(Status.PASS, message + " is success ");
		} else {
			logger.log(Status.PASS, message + " is success " + assertions);
		}
	}

	// Use in catch block
	public void logError(Response response, String message) {
		LOG.error(message + response.getBody().asString());
		LOG.error(message + response.getHeaders());
		LOG.error("Detailed Info..");
		LOG.error(message + response.then().log().all());
		logger.log(Status.FAIL, message);
	}

	// Use when no assertions exists
	public void logResult(Response response, boolean success, String message) {
		if (success) {
			LOG.info(message + " Status code is expected: ");
			LOG.info(message + response.getStatusCode());
			LOG.info(message + response.getBody().asString());
			logger.log(Status.PASS, message + " - Status code is expected");

		} else {
			LOG.info(message + " Status is not expected: ");
			LOG.info(message + response.getStatusCode());
			LOG.info(message + response.getBody().asString());
			logger.log(Status.FAIL, message + " - Status code is not expected");

		}
	}
}
