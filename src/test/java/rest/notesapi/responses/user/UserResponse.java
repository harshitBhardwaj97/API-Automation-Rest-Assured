package rest.notesapi.responses.user;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

import java.util.HashMap;
import java.util.Map;

import com.github.javafaker.Faker;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import rest.notesapi.endpoints.NotesApiEndpoints;
import rest.notesapi.models.UserResponseAndInfo;

public class UserResponse {

	private final static Faker faker = new Faker();

	public static UserResponseAndInfo registerValidUser() {
		Map<String, Object> validUserPayload = new HashMap<>();

		String validUserName = faker.name().fullName();
		String validEmail = faker.internet().emailAddress();
		String password = faker.bothify("????####", false);

		validUserPayload.put("name", validUserName);
		validUserPayload.put("email", validEmail);
		validUserPayload.put("password", password);

		Response response = given().contentType(ContentType.JSON).body(validUserPayload).when()
				.post(NotesApiEndpoints.registerUser);
		return new UserResponseAndInfo(response, validUserPayload);
	}

	public static UserResponseAndInfo registerUserWithInvalidEmail() {
		Map<String, Object> userPayloadWithInvalidEmail = new HashMap<>();

		String validUserName = faker.name().fullName();
		String invalidEmail = faker.bothify("?#?#?#?#", false); // Invalid Email
		String password = faker.bothify("????####", false);

		userPayloadWithInvalidEmail.put("name", validUserName);
		userPayloadWithInvalidEmail.put("email", invalidEmail);
		userPayloadWithInvalidEmail.put("password", password);

		Response response = given().contentType(ContentType.JSON).body(userPayloadWithInvalidEmail).when()
				.post(NotesApiEndpoints.registerUser);
		return new UserResponseAndInfo(response, userPayloadWithInvalidEmail);
	}

	public static UserResponseAndInfo registerUserWithInvalidPassword() {
		Map<String, Object> userPayloadWithInvalidPassword = new HashMap<>();

		String validUserName = faker.name().fullName();
		String validEmail = faker.internet().emailAddress();
		String invalidPassword = faker.bothify("?#?", false); // Invalid Password

		userPayloadWithInvalidPassword.put("name", validUserName);
		userPayloadWithInvalidPassword.put("email", validEmail);
		userPayloadWithInvalidPassword.put("password", invalidPassword);

		Response response = given().contentType(ContentType.JSON).body(userPayloadWithInvalidPassword).when()
				.post(NotesApiEndpoints.registerUser);
		return new UserResponseAndInfo(response, userPayloadWithInvalidPassword);
	}

	public static UserResponseAndInfo loginUserWithCredentials(String email, String password) {
		Map<String, Object> userPayloadOrInfo = new HashMap<>();

		userPayloadOrInfo.put("email", email);
		userPayloadOrInfo.put("password", password);

		Response response = given().contentType(ContentType.JSON).body(userPayloadOrInfo).when()
				.post(NotesApiEndpoints.loginUser).then().extract().response();

		String token = response.path("data.token");
		userPayloadOrInfo.put("token", token);
		return new UserResponseAndInfo(response, userPayloadOrInfo);
	}

	public static Response logoutUserWithToken(String token) {
		Response response = given().header("x-auth-token", token).when().delete(NotesApiEndpoints.logoutUser);
		return response;
	}

	public static Response deleteUserWithToken(String token) {
		Response response = given().header("x-auth-token", token).when().delete(NotesApiEndpoints.deleteUser);
		return response;
	}

	public static Response getUserProfileWithoutAuthorization() {
		Response response = when().get(NotesApiEndpoints.userProfile);
		return response;
	}

	public static Response logoutUserWithoutAuthorization() {
		Response response = when().delete(NotesApiEndpoints.logoutUser);
		return response;
	}

	public static Response deleteUserAccountWithoutAuthorization() {
		Response response = when().delete(NotesApiEndpoints.deleteUser);
		return response;
	}

	public static Response changeUserPasswordWithoutAuthorization() {
		Map<String, Object> payload = new HashMap<>();
		payload.put("currentPassword", "John");
		payload.put("newPassword", "Doe");

		Response response = given().contentType(ContentType.JSON).body(payload).when()
				.post(NotesApiEndpoints.changeUserPassword);
		return response;
	}

	public static Response changeUserPasswordWithoutAuthorizationAndPayload() {
		Response response = given().contentType(ContentType.JSON).when().post(NotesApiEndpoints.changeUserPassword);
		return response;
	}

}
