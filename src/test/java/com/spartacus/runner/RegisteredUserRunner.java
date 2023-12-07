package com.spartacus.runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = { "src/test/resources/features/LoggedInUser.feature" }, dryRun = false, glue = {
		"com.spartacus.steps" })
public class RegisteredUserRunner {

}