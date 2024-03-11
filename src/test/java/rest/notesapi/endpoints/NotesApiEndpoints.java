package rest.notesapi.endpoints;

public class NotesApiEndpoints {

	public static final String BASE_URL = "https://practice.expandtesting.com/notes/api";

	public static String healthCheck = BASE_URL + "/health-check";

	/*
	 * User End-points
	 */
	public static String registerUser = BASE_URL + "/users/register";
	public static String loginUser = BASE_URL + "/users/login";
	public static String userProfile = BASE_URL + "/users/profile";
	public static String logoutUser = BASE_URL + "/users/logout";
	public static String deleteUser = BASE_URL + "/users/delete-account";
	public static String changeUserPassword = BASE_URL + "/users/change-password";

	/*
	 * Notes End-points
	 */
	public static String getAllNotesOrPostNote = BASE_URL + "/notes";
	public static String createReadUpdateOrDeleteNoteById = BASE_URL + "/notes/{id}";

}
