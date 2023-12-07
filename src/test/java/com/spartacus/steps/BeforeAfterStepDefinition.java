package com.spartacus.steps;

import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spartacus.utilities.APIHelper;
import com.spartacus.utilities.TestOutputUtil;
import com.spartacus.utilities.TestResultUtils;
import com.spartacus.utilities.TestStepParams;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;

public class BeforeAfterStepDefinition {

	private static final Logger LOG = LoggerFactory.getLogger(BeforeAfterStepDefinition.class);
	TestOutputUtil testutil = new TestOutputUtil();
	public static HashMap<Object, Object> folderStrcuture;
	private static APIHelper apihelper = new APIHelper();
	public static boolean initializeObjects = false;

	public static APIHelper getApihelper() {
		return apihelper;
	}

	@Before
	public void beforeExecution(Scenario scenario) {
		try {
			if (!initializeObjects) {
				apihelper.InitAPIHelper();
				initializeObjects = true;
			} else {
				LOG.info("Properties objects are initialized, hence skip this method");
			}
			apihelper.isLogIn = false;
			folderStrcuture = testutil.createOutputFolder("APIExecution");
			if (TestResultUtils.htmlReporter == null) {
				TestResultUtils.extentReportInitialize(folderStrcuture.get("ExtentDirectory").toString(), "Test",
						"Test", "Test", "Test", "Test");
			}
			LOG.info("Reports Initialization::" + TestResultUtils.htmlReporter);
			TestResultUtils.extentTestInitialize(scenario.getName());
		} catch (Exception ex) {

		}
	}

	@BeforeStep
	public void beforeStepExecution() {
		apihelper.testStepParams = new TestStepParams();
		apihelper.assertionResults = new ArrayList<String>();
		apihelper.assertionObj = new HashMap<Boolean, ArrayList<String>>();
	}

	@AfterStep
	public void afterStepExecution() {
		if (apihelper.assertionResults != null)
			apihelper.assertionResults.clear();
		if (apihelper.assertionObj != null)
			apihelper.assertionObj.clear();
	}

	@After
	public void afterExecution() {
		if (apihelper.executionData != null)
			apihelper.executionData.clear();
		TestResultUtils.closeExtentReport();
	}

}
