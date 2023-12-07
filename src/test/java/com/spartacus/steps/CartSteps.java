package com.spartacus.steps;

import java.util.ArrayList;
import java.util.HashMap;

import com.spartacus.utilities.APIHelper;
import com.spartacus.utilities.AssertionValidator;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CartSteps {

	BeforeAfterStepDefinition bs = new BeforeAfterStepDefinition();
	AssertionValidator validator = new AssertionValidator();
	Keywords api = new Keywords();
	String executionStep = null;
	APIHelper helper = BeforeAfterStepDefinition.getApihelper();

	@And("^I should be able to get customers addresses$")
	public void getAddress() throws Throwable {
		try {
			executionStep = Thread.currentThread().getStackTrace()[1].getMethodName();
			helper.response = api.getCustomerAddresses();
			if (helper.assertionUtil.assertResponse(helper.response, 200)) {
				helper.setGlobalParams(helper.response,
						helper.globalParamsUtils.getProperty("setCheckoutAcctMgmtAddressId"), "addressId");
				helper.logResult(helper.response, true, executionStep);
			} else {
				helper.logResult(helper.response, false, executionStep);
			}
		} catch (Exception ex) {
			helper.logError(helper.response, executionStep);
		}
	}

	@And("^I should be able to set saved addresses$")
	public void SetSavedAddress() throws Throwable {
		try {
			executionStep = Thread.currentThread().getStackTrace()[1].getMethodName();
			helper.response = api.setSavedAddress();
			if (helper.assertionUtil.assertResponse(helper.response, 200)) {
				helper.logResult(helper.response, true, executionStep);
			} else {
				helper.logResult(helper.response, false, executionStep);
			}
		} catch (Exception ex) {
			helper.logError(helper.response, executionStep);
		}
	}

	public void setCartGlobalParameters() {
		if (helper.isLogIn) {
			helper.setGlobalParams(helper.response, helper.globalParamsUtils.getProperty("cartId_login"), "gcid");
		} else {
			helper.setGlobalParams(helper.response, helper.globalParamsUtils.getProperty("cartId"), "gcid");
		}
	}

	@Given("^I create a cartID to add items to the cart$")
	public void CreateCart() throws Throwable {
		try {
			executionStep = Thread.currentThread().getStackTrace()[1].getMethodName();
			helper.response = api.createCart();
			if (helper.assertionUtil.assertResponse(helper.response, 201)) {
				setCartGlobalParameters();
				helper.logResult(helper.response, true, executionStep);
			} else {
				helper.logResult(helper.response, false, executionStep);
			}
		} catch (Exception ex) {
			helper.logError(helper.response, executionStep);
		}
	}

	@Given("I add an item {string} to the cart")
	public void ATC(String payLoad) {
		try {
			executionStep = Thread.currentThread().getStackTrace()[1].getMethodName();
			helper.executionData.put("payLoad", payLoad);
			helper.response = api.addToCart(helper.payLoadPath.concat(payLoad));
			if (helper.assertionUtil.assertResponse(helper.response, 200)) {
				helper.testStepParams.setStepAssertion(helper.extractAssertionValues("ATCUpdated"));
				helper.assertionObj = validator.validateAssertions(helper.testStepParams, helper.response.asString());
				sendResultStatus(helper.assertionObj, helper.response, executionStep);
			} else {
				helper.logResult(helper.response, false, executionStep);
			}
		} catch (Exception ex) {
			helper.logError(helper.response, executionStep);
		}
	}

	@Then("I should be able to get the cart details {string}")
	public void FetchCart(String payLoad) {
		try {
			executionStep = Thread.currentThread().getStackTrace()[1].getMethodName();
			helper.response = api.getCartDetails();
			if (helper.assertionUtil.assertResponse(helper.response, 200)) {
				helper.testStepParams.setStepAssertion(helper.extractAssertionValues("GetCartUpdated"));
				helper.assertionObj = validator.validateAssertions(helper.testStepParams, helper.response.asString());
				sendResultStatus(helper.assertionObj, helper.response, executionStep);
			} else {
				helper.logResult(helper.response, false, executionStep);
			}
		} catch (Exception ex) {
			helper.logError(helper.response, executionStep);
		}
	}

	@Given("I update a cartID to add items {string} to the cart")
	public void UpdateCart(String payLoad) {
		try {
			executionStep = Thread.currentThread().getStackTrace()[1].getMethodName();
			helper.executionData.put("payLoad", payLoad);
			helper.response = api.updateCart(helper.payLoadPath.concat(payLoad));
			if (helper.assertionUtil.assertResponse(helper.response, 200)) {
				helper.testStepParams.setStepAssertion(helper.extractAssertionValues("ATCUpdated"));
				helper.assertionObj = validator.validateAssertions(helper.testStepParams, helper.response.asString());
				sendResultStatus(helper.assertionObj, helper.response, executionStep);
			} else {
				helper.logResult(helper.response, false, executionStep);
			}
		} catch (Exception ex) {
			helper.logError(helper.response, executionStep);
		}

	}

	@And("^I generate a token$")
	public void GenerateToken(DataTable testData) {
		try {
			executionStep = Thread.currentThread().getStackTrace()[1].getMethodName();
			helper.response = api.generateGuestUserToken(testData);
			if (helper.assertionUtil.assertResponse(helper.response, 200)) {
				helper.setGlobalParams(helper.response, helper.globalParamsUtils.getProperty("bearerToken"),
						"bearerToken");
				helper.logResult(helper.response, true, executionStep);
			} else {
				helper.logResult(helper.response, false, executionStep);
			}
		} catch (Exception ex) {
			helper.logError(helper.response, executionStep);
		}
	}

	@Given("^I login to the application$")
	public void UserLogin(DataTable testData) {
		try {
			String executionStep = Thread.currentThread().getStackTrace()[1].getMethodName();
			helper.response = api.login(testData);
			helper.isLogIn = true;
			if (helper.assertionUtil.assertResponse(helper.response, 200)) {
				helper.setGlobalParams(helper.response, helper.globalParamsUtils.getProperty("bearerToken"),
						"bearerToken");
				helper.logResult(helper.response, true, executionStep);
			} else {
				helper.logResult(helper.response, false, executionStep);
			}
		} catch (Exception ex) {
			helper.logError(helper.response, executionStep);
		}
	}

	@Then("I should be able assign email {string} to the cart")
	public void AssignEmail(String email) {
		try {
			executionStep = Thread.currentThread().getStackTrace()[1].getMethodName();
			helper.executionData.put("email", email);
			helper.response = api.assignEmailToCart(email);
			if (helper.assertionUtil.assertResponse(helper.response, 200)) {
				helper.logResult(helper.response, true, executionStep);
			} else {
				helper.logResult(helper.response, false, executionStep);
			}
		} catch (Exception ex) {
			helper.logError(helper.response, executionStep);
		}
	}

	@Then("I should be able to add address {string}")
	public void AddAddress(String address) {
		try {
			executionStep = Thread.currentThread().getStackTrace()[1].getMethodName();
			helper.executionData.put("address", address);
			helper.response = api.addAddress(helper.payLoadPath.concat(address));
			if (helper.assertionUtil.assertResponse(helper.response, 201)) {
				helper.setGlobalParams(helper.response, helper.globalParamsUtils.getProperty("addressId"), "addressId");
				helper.testStepParams.setStepAssertion(helper.extractAssertionValues("AddAddressUpdated"));
				helper.assertionObj = validator.validateAssertions(helper.testStepParams, helper.response.asString());
				sendResultStatus(helper.assertionObj, helper.response, executionStep);
			} else {
				helper.logResult(helper.response, false, executionStep);
			}
		} catch (Exception ex) {
			helper.logError(helper.response, executionStep);
		}
	}

	@Then("I should be able to set delivery method {string}")
	public void setDeliveryMode(String deliveryMode) {
		try {
			executionStep = Thread.currentThread().getStackTrace()[1].getMethodName();
			helper.executionData.put("deliveryMode", deliveryMode);
			helper.response = api.setDeliveryMode(deliveryMode);
			if (helper.assertionUtil.assertResponse(helper.response, 200)) {
				helper.logResult(helper.response, true, executionStep);
			} else {
				helper.logResult(helper.response, false, executionStep);
			}
		} catch (Exception ex) {
			helper.logError(helper.response, executionStep);
		}
	}

	@Then("I should be able to add payment details {string}")
	public void addPayment(String payment) {
		try {
			executionStep = Thread.currentThread().getStackTrace()[1].getMethodName();
			helper.executionData.put("payment", payment);
			helper.response = api.addPayment(helper.payLoadPath.concat(payment));
			if (helper.assertionUtil.assertResponse(helper.response, 201)) {
				helper.testStepParams.setStepAssertion(helper.extractAssertionValues("AddPaymentUpdated"));
				helper.assertionObj = validator.validateAssertions(helper.testStepParams, helper.response.asString());
				sendResultStatus(helper.assertionObj, helper.response, executionStep);
			} else {
				helper.logResult(helper.response, false, executionStep);
			}
		} catch (Exception ex) {
			helper.logError(helper.response, executionStep);
		}
	}

	@Then("^I should be able to create order$")
	public void CreateOrder() throws Throwable {
		boolean isSuccess = true;
		ArrayList<String> assertions = new ArrayList<String>();
		try {
			executionStep = Thread.currentThread().getStackTrace()[1].getMethodName();
			helper.response = api.createOrder();
			if (helper.assertionUtil.assertResponse(helper.response, 201)) {
				helper.setGlobalParams(helper.response, helper.globalParamsUtils.getProperty("orderCode"),
						"OrderNumber");

				helper.testStepParams.setBodyParams(
						helper.getPayLoadFromFile(helper.payLoadPath.concat(helper.executionData.get("payLoad"))));
				helper.testStepParams.setStepAssertion(helper.extractAssertionValues("CreateOrderProductsUpdated"));
				helper.assertionObj = validator.validateAssertions(helper.testStepParams, helper.response.asString());

				isSuccess = ((Boolean) helper.assertionObj.keySet().toArray()[0]);
				if (isSuccess)
					assertions.addAll(helper.assertionObj.get(isSuccess));
				helper.testStepParams.setBodyParams(
						helper.getPayLoadFromFile(helper.payLoadPath.concat(helper.executionData.get("address"))));
				helper.testStepParams.setStepAssertion(helper.extractAssertionValues("CreateOrderAddressUpdated"));
				helper.assertionObj = validator.validateAssertions(helper.testStepParams, helper.response.asString());

				isSuccess = ((Boolean) helper.assertionObj.keySet().toArray()[0]);
				if (isSuccess)
					assertions.addAll(helper.assertionObj.get(isSuccess));
				helper.testStepParams.setBodyParams(
						helper.getPayLoadFromFile(helper.payLoadPath.concat(helper.executionData.get("payment"))));
				helper.testStepParams.setStepAssertion(helper.extractAssertionValues("CreateOrderPaymentUpdated"));
				helper.assertionObj = validator.validateAssertions(helper.testStepParams, helper.response.asString());

				assertions.addAll(helper.assertionObj.get(isSuccess));
				helper.assertionObj.clear();
				helper.assertionObj.put(isSuccess, assertions);
				sendResultStatus(helper.assertionObj, helper.response, executionStep);

			} else {
				helper.logResult(helper.response, false, executionStep);
			}
		} catch (

		Exception ex) {
			helper.logError(helper.response, executionStep);
		}
	}

	@Given("I search for a product {string} and verify the results")
	public void PLPVerify(String plp) {
		try {
			executionStep = Thread.currentThread().getStackTrace()[1].getMethodName();
			helper.response = api.plp(plp);
			if (helper.assertionUtil.assertResponse(helper.response, 200)) {
				helper.testStepParams.setStepAssertion(helper.extractAssertionValues("PLPUpdated"));
				helper.assertionObj = validator.validateAssertions(helper.testStepParams, helper.response.asString());
				sendResultStatus(helper.assertionObj, helper.response, plp);
			} else {
				helper.logResult(helper.response, false, executionStep);
			}
		} catch (Exception ex) {
			helper.logError(helper.response, executionStep);
		}
	}

	@Given("I search for a product using keyword {string} and verify the results")
	public void KeywordSearch(String query) {
		try {
			executionStep = Thread.currentThread().getStackTrace()[1].getMethodName();
			helper.response = api.search(query);
			if (helper.assertionUtil.assertResponse(helper.response, 200)) {
				helper.testStepParams.setStepAssertion(helper.extractAssertionValues("SearchUpdated"));
				helper.assertionObj = validator.validateAssertions(helper.testStepParams, helper.response.asString());
				sendResultStatus(helper.assertionObj, helper.response, query);
			} else {
				helper.logResult(helper.response, false, executionStep);
			}
		} catch (Exception ex) {
			helper.logError(helper.response, executionStep);
		}
	}

	@Given("I search for a product {string} using type ahead search and verify the results")
	public void TypeAheadSearch(String term) {
		try {
			executionStep = Thread.currentThread().getStackTrace()[1].getMethodName();
			helper.response = api.typeAheadSearch(term);
			if (helper.assertionUtil.assertResponse(helper.response, 200)) {
				helper.testStepParams.setStepAssertion(helper.extractAssertionValues("TypeAheadResultsUpdated"));
				helper.assertionObj = validator.validateAssertions(helper.testStepParams, helper.response.asString());
				sendResultStatus(helper.assertionObj, helper.response, term);
			} else {
				helper.logResult(helper.response, false, executionStep);
			}
		} catch (Exception ex) {
			helper.logError(helper.response, executionStep);
		}

	}

	public void sendResultStatus(HashMap<Boolean, ArrayList<String>> assertReturnObj, Response response,
			String executionStep) {
		boolean status = ((Boolean) assertReturnObj.keySet().toArray()[0]);
		if (status) {
			helper.logResultAssertion(response, executionStep, assertReturnObj.get(status));
		} else {
			helper.logResult(response, false, executionStep);
		}
	}

	@And("^I delete the cart$")
	public void DeleteCart() throws Throwable {
		try {
			executionStep = Thread.currentThread().getStackTrace()[1].getMethodName();
			helper.response = api.deleteCart();
			if (helper.assertionUtil.assertResponse(helper.response, 200)) {
				helper.logResult(helper.response, true, executionStep);
			} else {
				helper.logResult(helper.response, false, executionStep);
			}
		} catch (Exception ex) {
			helper.logError(helper.response, executionStep);
		}

	}

	@Then("^I should be able to delete address$")
	public void DeleteCheckoutAddress() throws Throwable {
		try {
			executionStep = Thread.currentThread().getStackTrace()[1].getMethodName();
			helper.response = api.deleteCheckoutAddress();
			if (helper.assertionUtil.assertResponse(helper.response, 200)) {
				helper.logResult(helper.response, true, executionStep);
			} else {
				helper.logResult(helper.response, false, executionStep);
			}
		} catch (Exception ex) {
			helper.logError(helper.response, executionStep);
		}
	}

	@Then("I should be able to delete delivery method {string}")
	public void deleteDeliveryMode(String deliveryMode) {
		try {
			executionStep = Thread.currentThread().getStackTrace()[1].getMethodName();
			helper.response = api.deleteDeliveryMode(deliveryMode);
			if (helper.assertionUtil.assertResponse(helper.response, 200)) {
				helper.logResult(helper.response, true, executionStep);
			} else {
				helper.logResult(helper.response, false, executionStep);
			}
		} catch (Exception ex) {
			helper.logError(helper.response, executionStep);
		}
	}

	@Given("I change the First name and Last Name {string} of the user")
	public void updateUserDetails(String userDetails) {
		try {
			executionStep = Thread.currentThread().getStackTrace()[1].getMethodName();
			helper.response = api.updateUserDetails(userDetails);
			if (helper.assertionUtil.assertResponse(helper.response, 200)) {
				helper.logResult(helper.response, true, executionStep);
			} else {
				helper.logResult(helper.response, false, executionStep);
			}
		} catch (Exception ex) {
			helper.logError(helper.response, executionStep);
		}
	}

	@Given("^I change the Email Address of the user with ([^\"]*) and ([^\"]*)$")
	public void ChangeEmailAddress(String newLogin, String password) throws Throwable {
		try {
			executionStep = Thread.currentThread().getStackTrace()[1].getMethodName();
			helper.response = api.changeEmail(newLogin, password);
			if (helper.assertionUtil.assertResponse(helper.response, 200)) {
				helper.logResult(helper.response, true, executionStep);
			} else {
				helper.logResult(helper.response, false, executionStep);
			}
		} catch (Exception ex) {
			helper.logError(helper.response, executionStep);
		}
	}

	@Given("^I update the password of the user with ([^\"]*) using old ([^\"]*)$")
	public void UpdatePassword(String newPassword, String oldPassword) throws Throwable {
		try {
			executionStep = Thread.currentThread().getStackTrace()[1].getMethodName();
			helper.response = api.updatePassword(newPassword, oldPassword);
			if (helper.assertionUtil.assertResponse(helper.response, 202)) {
				helper.logResult(helper.response, true, executionStep);

			} else {
				helper.logResult(helper.response, false, executionStep);
			}
		} catch (Exception ex) {
			helper.logError(helper.response, executionStep);
		}
	}

	@Then("I Add the New Address {string}")
	public void AddAccountAddress(String address) {
		try {
			executionStep = Thread.currentThread().getStackTrace()[1].getMethodName();
			helper.response = api.addAccountAddress(helper.payLoadPath.concat(address));
			if (helper.assertionUtil.assertResponse(helper.response, 201)) {
				helper.setGlobalParams(helper.response, helper.globalParamsUtils.getProperty("addressId"), "addressId");
				helper.testStepParams.setStepAssertion(helper.extractAssertionValues("AddAddressUpdated"));
				helper.assertionObj = validator.validateAssertions(helper.testStepParams, helper.response.asString());
				sendResultStatus(helper.assertionObj, helper.response, executionStep);
			} else {
				helper.logResult(helper.response, false, executionStep);
			}
		} catch (Exception ex) {
			helper.logError(helper.response, executionStep);
		}
	}

	@Then("I update the Address {string}")
	public void UpdateAccountAddress(String updateAddress) {
		try {
			executionStep = Thread.currentThread().getStackTrace()[1].getMethodName();
			helper.response = api.updateAccountAddress(helper.payLoadPath.concat(updateAddress));
			if (helper.assertionUtil.assertResponse(helper.response, 200)) {
				helper.logResult(helper.response, true, executionStep);
			} else {
				helper.logResult(helper.response, false, executionStep);
			}
		} catch (Exception ex) {
			helper.logError(helper.response, executionStep);
		}
	}

	@Then("^I delete the Address$")
	public void DeleteAccountAddress() throws Throwable {
		try {
			executionStep = Thread.currentThread().getStackTrace()[1].getMethodName();
			helper.response = api.deleteAccountAddress(helper.globalParams.get("addressId"));
			if (helper.assertionUtil.assertResponse(helper.response, 200)) {
				helper.logResult(helper.response, true, executionStep);
			} else {
				helper.logResult(helper.response, false, executionStep);
			}
		} catch (Exception ex) {
			helper.logError(helper.response, executionStep);
		}
	}

	@Then("^I Get Total Number of Orders$")
	public void GetTotalNumberofOrders() throws Throwable {
		try {
			executionStep = Thread.currentThread().getStackTrace()[1].getMethodName();
			helper.response = api.getCountOfOrders();
			if (helper.assertionUtil.assertResponse(helper.response, 200)) {
				int count = Integer.parseInt(helper.response.getHeader("X-Total-Count"));
				if (count > 0)
					helper.logResult(helper.response, true, executionStep + " Orders count matched");

			} else {
				helper.logResult(helper.response, false, executionStep);
			}
		} catch (Exception ex) {
			System.out.println(ex);
			helper.logError(helper.response, executionStep);
		}
	}

	@Then("^I Get order history details and verify created order$")
	public void GetOrdersHistory() throws Throwable {
		try {
			executionStep = Thread.currentThread().getStackTrace()[1].getMethodName();
			helper.response = api.getOrderDetailsAndVerifyOrderCreated();
			if (helper.assertionUtil.assertResponse(helper.response, 200)) {
				helper.logResult(helper.response, true, executionStep);
			} else {
				helper.logResult(helper.response, false, executionStep);
			}
		} catch (Exception ex) {
			System.out.println(ex);
			helper.logError(helper.response, executionStep);
		}
	}

	@Then("I delete the cart before scenario exec")
	public void DeleteCartBeforeToScenarioExec() {
		try {
			executionStep = Thread.currentThread().getStackTrace()[1].getMethodName();
			helper.response = api.getAllCarts();
			if (helper.assertionUtil.assertResponse(helper.response, 200)) {
				String cartCode;
				try {
					cartCode = JsonPath.from(helper.response.asString()).getString("carts[0].code");
					if (cartCode == null) {
						helper.logResult(helper.response, true, executionStep + ": There are no carts exists");
						return;
					}
				} catch (Exception ex) {
					helper.logResult(helper.response, true, executionStep + ": There are no carts exists");
					return;
				}
				helper.response = api.deleteCart();
				if (helper.assertionUtil.assertResponse(helper.response, 200)) {
					helper.logResult(helper.response, true, executionStep);
				}
			} else {
				helper.logResult(helper.response, false, executionStep);
			}
		} catch (Exception ex) {
			helper.logError(helper.response, executionStep);
		}

	}

	@Then("^I Get order details for the created order$")
	public void GetOrderDetailsById() throws Throwable {
		try {
			executionStep = Thread.currentThread().getStackTrace()[1].getMethodName();
			helper.response = api.getOrderDetails();
			if (helper.assertionUtil.assertResponse(helper.response, 200)) {
				helper.testStepParams.setStepAssertion(helper.extractAssertionValues("CreateOrderVerifyOrderId"));
				helper.assertionObj = validator.validateAssertions(helper.testStepParams, helper.response.asString());
				sendResultStatus(helper.assertionObj, helper.response, executionStep);
			} else {
				helper.logResult(helper.response, false, executionStep);
			}
		} catch (Exception ex) {
			helper.logError(helper.response, executionStep);
		}
	}

	@Then("I reset the userlogin")
	public void resetLogin() {
		helper.isLogIn = false;
	}
}
