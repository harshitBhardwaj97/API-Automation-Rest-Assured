package rest.notesapi.responses.common;

import static io.restassured.RestAssured.when;

import io.restassured.response.Response;
import rest.notesapi.endpoints.NotesApiEndpoints;

public class CommonResponse {

	public static Response checkHealthOfNotesApi() {
		Response response = when().get(NotesApiEndpoints.healthCheck);
		return response;
	}

}