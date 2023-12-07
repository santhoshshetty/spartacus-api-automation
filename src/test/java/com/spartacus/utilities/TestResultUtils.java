package com.spartacus.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class TestResultUtils {
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest logger;

	public static void extentReportInitialize(String extentReportPath, String reportTitle, String reportName,
			String hostName, String environment, String testSuite) {
		htmlReporter = new ExtentHtmlReporter(extentReportPath.concat("/extentReport.html"));
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host Name", hostName);
		extent.setSystemInfo("Environment", environment);
		extent.setSystemInfo("Test Suite", testSuite);
		htmlReporter.config().setDocumentTitle(reportTitle);
		htmlReporter.config().setReportName(reportName);
		htmlReporter.config().setTheme(Theme.STANDARD);
	}

	public static void extentTestInitialize(String testName) {
		logger = extent.createTest(testName);

	}

	public static void closeExtentReport() {
		extent.flush();
	}
}
