
package com.spartacus.steps;

import com.spartacus.utilities.APIHelper;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

public class VoucherSteps extends APIHelper {

	APIHelper helper = new APIHelper();
	Keywords api = new Keywords();

	@Then("^I should be able to add voucher$")
	public void AddVoucher(DataTable testData) throws Throwable {
		String executionStep = null;
		try {
			executionStep = Thread.currentThread().getStackTrace()[1].getMethodName();
			helper.response = api.addVoucherToCart(testData);
			if (assertionUtil.assertResponse(response, 200)) {
				helper.logResult(helper.response, true, executionStep);
			} else {
				logResult(helper.response, false, executionStep);
			}
		} catch (Exception ex) {
			logError(helper.response, executionStep);
		}
	}

	@And("^I should be able to get list of vouchers$")
	public void GetVouchers() throws Throwable {
		String executionStep = null;
		try {
			executionStep = Thread.currentThread().getStackTrace()[1].getMethodName();
			helper.response = api.getVouchernAppliedToCart();
			if (assertionUtil.assertResponse(response, 200)) {
				helper.logResult(helper.response, true, executionStep);
			} else {
				logResult(helper.response, false, executionStep);
			}
		} catch (Exception ex) {
			logError(helper.response, executionStep);
		}
	}

	@Then("^I should be able to delete vouchers$")
	public void DeleteVoucher(DataTable testData) throws Throwable {
		String executionStep = null;
		try {
			executionStep = Thread.currentThread().getStackTrace()[1].getMethodName();
			helper.response = api.deleteVoucherAppliedToCart(testData);
			if (assertionUtil.assertResponse(response, 200)) {
				helper.logResult(helper.response, true, executionStep);
			} else {
				logResult(helper.response, false, executionStep);
			}
		} catch (Exception ex) {
			logError(helper.response, executionStep);
		}
	}

}
