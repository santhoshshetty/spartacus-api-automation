
package com.spartacus.steps;

import com.spartacus.utilities.APIHelper;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class PromotionSteps {

	Keywords api = new Keywords();
	APIHelper helper = new APIHelper();

	@Then("^I should be able to add promotion$")
	public void AddPromotion(DataTable testData) throws Throwable {
		String executionStep = null;
		try {
			executionStep = Thread.currentThread().getStackTrace()[1].getMethodName();
			helper.response = api.addPromotionToCart(testData);
			if (helper.assertionUtil.assertResponse(helper.response, 200)) {
				helper.logResult(helper.response, true, executionStep);
			} else {
				helper.logResult(helper.response, false, executionStep);
			}
		} catch (Exception ex) {
			helper.logError(helper.response, executionStep);
		}
	}

	@And("^I should be able to get list of promotions$")
	public void GetPromotion() throws Throwable {
		String executionStep = null;
		try {
			executionStep = Thread.currentThread().getStackTrace()[1].getMethodName();
			helper.response = api.getPromotionAppliedToCart();
			if (helper.assertionUtil.assertResponse(helper.response, 200)) {
				helper.logResult(helper.response, true, executionStep);
			} else {
				helper.logResult(helper.response, false, executionStep);
			}
		} catch (Exception ex) {
			helper.logError(helper.response, executionStep);
		}
	}

	@Then("^I should be able to delete promotions$")
	public void DeletePromotion(DataTable testData) throws Throwable {
		String executionStep = null;
		try {
			executionStep = Thread.currentThread().getStackTrace()[1].getMethodName();
			helper.response = api.deletePromotionAppliedToCart(testData);
			if (helper.assertionUtil.assertResponse(helper.response, 200)) {
				helper.logResult(helper.response, true, executionStep);
			} else {
				helper.logResult(helper.response, false, executionStep);
			}
		} catch (Exception ex) {
			helper.logError(helper.response, executionStep);
		}
	}

}
