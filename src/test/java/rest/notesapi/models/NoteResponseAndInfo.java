package rest.notesapi.models;

import java.util.Map;

import io.restassured.response.Response;

public class NoteResponseAndInfo {

	private Response response;
	private Map<String, Object> notePayloadOrInfo;

	public NoteResponseAndInfo(Response response, Map<String, Object> notePayloadOrInfo) {
		this.response = response;
		this.notePayloadOrInfo = notePayloadOrInfo;
	}

	public Response getResponse() {
		return response;
	}

	public Map<String, Object> getNotePayloadOrInfo() {
		return notePayloadOrInfo;
	}

}
