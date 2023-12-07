package com.spartacus.runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = { "src/test/resources/features/AccountManagement.feature" }, dryRun = false, tags = {
		"@AcctMgmtAddress" }, glue = { "com.spartacus.steps" })
public class AcctMgmtRunner {

}