package rest.notesapi.responses.notes;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

import java.util.HashMap;
import java.util.Map;

import com.github.javafaker.Faker;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import rest.notesapi.endpoints.NotesApiEndpoints;
import rest.notesapi.models.NoteResponseAndInfo;
import rest.notesapi.utils.Utils;

public class NotesResponse {

	private final static Faker faker = new Faker();

	public static Response getAllNotesWithoutAuthorization() {
		Response response = when().get(NotesApiEndpoints.getAllNotesOrPostNote);
		return response;
	}

	public static NoteResponseAndInfo postNoteWithoutAuthorization() {
		Map<String, Object> notePayload = new HashMap<>();

		String noteTitle = faker.lorem().sentence();
		String noteDescription = faker.lorem().paragraph();
		String noteType = Utils.getRandomNoteCategory();

		notePayload.put("title", noteTitle);
		notePayload.put("description", noteDescription);
		notePayload.put("category", noteType);

		Response response = given().contentType(ContentType.JSON).when().post(NotesApiEndpoints.getAllNotesOrPostNote);
		return new NoteResponseAndInfo(response, notePayload);
	}

	public static Response postNoteWithoutAuthorizationAndPayload() {
		Response response = given().contentType(ContentType.JSON).when().post(NotesApiEndpoints.getAllNotesOrPostNote);
		return response;
	}

	public static Response getNoteWithoutAuthorization() {
		String randomId = Utils.generateRandomHexId();
		Response response = given().pathParam("id", randomId).when()
				.get(NotesApiEndpoints.createReadUpdateOrDeleteNoteById);
		return response;
	}

	public static Response putNoteWithoutAuthorization() {
		String randomId = Utils.generateRandomHexId();
		Map<String, Object> updatedNotePayload = new HashMap<>();

		String updatedNoteTitle = faker.lorem().sentence();
		String updatedNoteDescription = faker.lorem().paragraph();
		String updatedNoteType = Utils.getRandomNoteCategory();
		boolean updatedNoteCompletionStatus = Utils.getRandomNoteCompletion();

		updatedNotePayload.put("title", updatedNoteTitle);
		updatedNotePayload.put("description", updatedNoteDescription);
		updatedNotePayload.put("completed", updatedNoteCompletionStatus);
		updatedNotePayload.put("category", updatedNoteType);

		System.out.println("updatedNote -> " + updatedNotePayload);

		Response response = given().pathParam("id", randomId).contentType(ContentType.JSON).when()
				.put(NotesApiEndpoints.createReadUpdateOrDeleteNoteById);
		return response;
	}

	public static Response patchNoteWithoutAuthorization() {
		String randomId = Utils.generateRandomHexId();
		Map<String, Object> patchedNotePayload = new HashMap<>();

		boolean patchedNoteCompletionStatus = Utils.getRandomNoteCompletion();
		patchedNotePayload.put("completed", patchedNoteCompletionStatus);

		System.out.println("patchedNote -> " + patchedNotePayload);

		Response response = given().pathParam("id", randomId).contentType(ContentType.JSON).when()
				.patch(NotesApiEndpoints.createReadUpdateOrDeleteNoteById);
		return response;
	}

	public static Response deleteNoteWithoutAuthorization() {
		String randomId = Utils.generateRandomHexId();
		Response response = given().pathParam("id", randomId).when()
				.delete(NotesApiEndpoints.createReadUpdateOrDeleteNoteById);
		return response;
	}

}
