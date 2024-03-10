package rest.notesapi.tests;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import rest.notesapi.responses.user.UserResponse;

public class NotesApiUnauthorizedUserTests {

	private static final Logger logger = LoggerFactory.getLogger(NotesApiUnauthorizedUserTests.class);
	private final String AUTH_MISSING_MESSAGE = "No authentication token specified in x-auth-token header";

	@Test
	public void getUserProfile_withoutAuthorization_returnsUnauthorizedRequestStatus() {
		logger.info(" ### Checking getUserProfile_withoutAuthorization ### ");
		Response response = UserResponse.getUserProfileWithoutAuthorization();
		response.then().log().body();
		Assert.assertEquals(response.statusCode(), 401);
		response.then().body("success", equalTo(false));
		response.then().body("status", equalTo(401));
		response.then().body("message", equalTo(AUTH_MISSING_MESSAGE));
		response.then().assertThat().body(matchesJsonSchemaInClasspath("userprofile-without-auth.json"));
		logger.info(" ============================================== ");
	}

	@Test
	public void logoutUser_withoutAuthorization__returnsUnauthorizedRequestStatus() {
		logger.info(" ### Checking logoutUser_withoutAuthorization ### ");
		Response response = UserResponse.logoutUserWithoutAuthorization();
		response.then().log().body();
		Assert.assertEquals(response.statusCode(), 401);
		response.then().body("success", equalTo(false));
		response.then().body("status", equalTo(401));
		response.then().body("message", equalTo(AUTH_MISSING_MESSAGE));
		response.then().assertThat().body(matchesJsonSchemaInClasspath("userprofile-without-auth.json"));
		logger.info(" ============================================== ");
	}

	@Test
	public void deleteUserProfile_withoutAuthorization_returnsUnauthorizedRequestStatus() {
		logger.info(" ### Checking deleteUserProfile_withoutAuthorization ### ");
		Response response = UserResponse.deleteUserAccountWithoutAuthorization();
		response.then().log().body();
		Assert.assertEquals(response.statusCode(), 401);
		response.then().body("success", equalTo(false));
		response.then().body("status", equalTo(401));
		response.then().body("message", equalTo(AUTH_MISSING_MESSAGE));
		response.then().assertThat().body(matchesJsonSchemaInClasspath("userprofile-without-auth.json"));
		logger.info(" ============================================== ");
	}

	@Test
	public void changeUserPassword_withoutAuthorization_returnsUnauthorizedRequestStatus() {
		logger.info(" ### Checking changeUserPassword_withoutAuthorization ### ");
		Response response = UserResponse.changeUserPasswordWithoutAuthorization();
		response.then().log().body();
		Assert.assertEquals(response.statusCode(), 401);
		response.then().body("success", equalTo(false));
		response.then().body("status", equalTo(401));
		response.then().body("message", equalTo(AUTH_MISSING_MESSAGE));
		response.then().assertThat().body(matchesJsonSchemaInClasspath("userprofile-without-auth.json"));
		logger.info(" ============================================== ");
	}

	@Test
	public void changeUserPassword_withoutAuthorizationAndPayload__returnsUnauthorizedRequestStatus() {
		logger.info(" ### Checking changeUserPassword_withoutAuthorizationAndPayload ### ");
		Response response = UserResponse.changeUserPasswordWithoutAuthorizationAndPayload();
		response.then().log().body();
		response.then().body("success", equalTo(false));
		response.then().body("status", equalTo(401));
		response.then().body("message", equalTo(AUTH_MISSING_MESSAGE));
		Assert.assertEquals(response.statusCode(), 401);
		logger.info(" ============================================== ");
	}

}
