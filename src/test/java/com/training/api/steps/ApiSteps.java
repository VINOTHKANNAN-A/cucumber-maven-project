package com.training.api.steps;

import com.training.api.context.ApiContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.testng.Assert;

public class ApiSteps {

	private final ApiContext context;

	public ApiSteps(ApiContext context) {
		this.context = context;
	}

	@Given("API base URI is {string}")
	public void api_base_uri_is(String baseUri) {

		context.setBaseUri(baseUri);

		// Common request setup + SSL relaxed
		context.setRequest(RestAssured.given().relaxedHTTPSValidation().baseUri(baseUri).contentType(ContentType.JSON)
				.accept(ContentType.JSON).log().all());
	}

	@When("I send GET request to {string}")
	public void i_send_get_request_to(String path) {

		Response res = context.getRequest().when().get(path);

		context.setResponse(res);

		System.out.println("GET " + path + " -> " + res.getStatusCode());
		res.then().log().all();
	}

	@When("I send POST request to {string} with json body:")
	public void i_send_post_request_to_with_json_body(String path, String body) {

		Response res = context.getRequest().body(body).when().post(path);

		context.setResponse(res);

		System.out.println("POST " + path + " -> " + res.getStatusCode());
		res.then().log().all();
	}

	@Then("response status should be {int}")
	public void response_status_should_be(Integer expectedStatus) {

		Assert.assertNotNull(context.getResponse(), "Response is null. Did you send a request?");

		Assert.assertEquals(context.getResponse().getStatusCode(), expectedStatus.intValue(), "Status code mismatch!");
	}

	@Then("response header {string} should contain {string}")
	public void response_header_should_contain(String headerName, String expectedValuePart) {

		String actual = context.getResponse().getHeader(headerName);

		Assert.assertNotNull(actual, "Header not found: " + headerName);

		Assert.assertTrue(actual.toLowerCase().contains(expectedValuePart.toLowerCase()),
				"Expected header '" + headerName + "' to contain '" + expectedValuePart + "' but got: " + actual);
	}

	@Then("response json path {string} should be {string}")
	public void response_json_path_should_be(String jsonPath, String expected) {

		Object actualValue = context.getResponse().jsonPath().get(jsonPath);

		Assert.assertNotNull(actualValue, "JSON path not found: " + jsonPath);

		Assert.assertEquals(String.valueOf(actualValue), expected, "JSON path mismatch for '" + jsonPath + "'");
	}
}
