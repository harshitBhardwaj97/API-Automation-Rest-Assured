package rest.notesapi.models;

import java.util.Map;

import io.restassured.response.Response;

public class UserResponseAndInfo {

	private Response response;
	private Map<String, Object> payloadOrInfo;

	public UserResponseAndInfo(Response response, Map<String, Object> payloadOrInfo) {
		this.response = response;
		this.payloadOrInfo = payloadOrInfo;
	}

	public Response getResponse() {
		return response;
	}

	public Map<String, Object> getPayloadOrInfo() {
		return payloadOrInfo;
	}

}
