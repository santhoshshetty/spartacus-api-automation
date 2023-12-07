package com.spartacus.runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = { "src/test/resources/features/GuestUser.feature" }, dryRun = false, tags = {}, glue = {
		"com.spartacus.steps" })
public class GuestUserRunner {

}