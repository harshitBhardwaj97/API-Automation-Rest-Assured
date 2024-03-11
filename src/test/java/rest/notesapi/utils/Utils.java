package rest.notesapi.utils;

import java.util.Random;
import java.util.UUID;

public class Utils {

	private static final String[] NOTE_CATEGORIES = { "Home", "Work", "Personal" };
	private static final boolean[] NOTE_COMPLETION = { true, false };

	public static String getRandomNoteCategory() {
		Random random = new Random();
		int index = random.nextInt(NOTE_CATEGORIES.length);
		return NOTE_CATEGORIES[index];
	}

	public static String generateRandomHexId() {
		UUID uuid = UUID.randomUUID();
		String hexId = uuid.toString().replace("-", "");
		return hexId;
	}

	public static boolean getRandomNoteCompletion() {
		Random random = new Random();
		int index = random.nextInt(NOTE_COMPLETION.length);
		return NOTE_COMPLETION[index];
	}

}
