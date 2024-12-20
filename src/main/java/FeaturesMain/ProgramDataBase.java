package FeaturesMain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ProgramDataBase {

	private static ArrayList<Program> programsList = new ArrayList<Program>();
	private static Set<Integer> usedIds = new HashSet<>();
	private static ArrayList<Attachment> attachmentsTest = new ArrayList<Attachment>();

	private static boolean lastUpdate;
	private static boolean programCreated;

	static {
		Program testProgram1 = new Program("Lower Body Work Out", "30 hours", "Beginners", "goal", "instructor13",
				"100000");
		// testProgram1.addClient("client123");
		testProgram1.addClient("client456");
		testProgram1.addClient("client123");
		testProgram1.setGroupSession("Morning Session", "Monday, Wednesday, Friday at 8:00 AM");
		addNewProgram(testProgram1);

		Program testProgram2 = new Program("yoga", "40 hours", "Beginners", "flexibility", "instructor11", "100001");

		testProgram2.addClient("client789");
		testProgram2.addClient("client101");
		testProgram2.addClient("client102");
		addNewProgram(testProgram2);

		// Instructor client progress tracking*************
		ProgramDataBase.getProgram("100001").setSessionsCount(5);
		ProgramDataBase.getProgram("100001").addClient("client123");
		ProgramDataBase.getProgram("100001").addClient("client790");
		ProgramDataBase.getProgram("100001").addClient("client791");
		ProgramDataBase.getProgram("100001").addClient("client792");
		ProgramDataBase.getProgram("100001").addClient("client793");
		UserDataBase.getUser("client790").setAttendence("100001", 2);
		UserDataBase.getUser("client791").setAttendence("100001", 3);
		UserDataBase.getUser("client792").setAttendence("100001", 4);
		UserDataBase.getUser("client793").setAttendence("100001", 1);
		// ***********************************************

		// added to test enrollment count
		Program testProgram3 = new Program("Cardio Blast", "20 hours", "Intermediate", "Endurance", "instructor14",
				"100002");
		testProgram3.addClient("client123");
		testProgram3.addClient("client102");
		testProgram3.addClient("client103");
		testProgram3.addClient("client104");
		testProgram3.addClient("client105");
		addNewProgram(testProgram3);

	}

	public static boolean addNewProgram(String title, String durationTime, String difficultyLevel, String goals,
			String instructorId, String programId, ArrayList<Attachment> attachments, String price) {

		boolean programAdded = false;
		// if the program already exists
		if (programExist(programId)) {
			System.out.println("this program already exists");
			return programAdded;
		}

		Program newProgram = new Program(title, durationTime, difficultyLevel, goals, instructorId, programId);
		boolean priceSet = setPriceForProgram(newProgram, price);
		boolean attachmentAdded = addAttachmentsToProgram(newProgram, attachments);
		if (priceSet && attachmentAdded) {
			addNewProgram(newProgram);
			// System.out.println("new program added: " + newProgram);
			programAdded = true;
		}

		return programAdded;
	}

	public static boolean addAttachmentsToProgram(Program program, ArrayList<Attachment> attachments) {
		boolean attachmentsAdded = false;
		if (attachments.size() > 0) {
			for (Attachment a : attachments) {
				program.addAttachment(a);
			}
			attachmentsAdded = true;
		} else {
			System.out.println("no attachments were added");
		}

		return attachmentsAdded;
	}

	public static boolean addNewProgram(Program newProgram) {

		// if the program already exists
		if (programExist(newProgram.getProgramId())) {
			System.out.println("this program already exists");
			return false;
		}

		programsList.add(newProgram);
		usedIds.add(Integer.parseInt(newProgram.getProgramId()));
		// System.out.println("new program added: " + newProgram);
		return true;
	}

	public static boolean programExist(String id) {
		for (Program p : programsList)
			if (p.getProgramId().equals(id))
				return true;
		return false;

	}

	public static boolean programExist(String programId, String instructorId) {
		for (Program p : programsList)
			if (p.getProgramId().equals(programId)) {
				if (p.getInstructorId().equals(instructorId)) {
					System.out.println("Program exists");
					return true;
				} else {
					System.out.println("you dont have access to this program");
					return false;
				}
			}

		return false;
	}

	public static ArrayList<Program> getProgramsForInstructor(String id) {
		ArrayList<Program> programs = new ArrayList<>();
		for (Program p : programsList) {
			if (p.getInstructorId().equals(id)) {
				programs.add(p);
			}
		}
		return programs;
	}

	public static boolean checkIfApplicable(String price) {
		boolean applicable = true;
		if (Integer.parseInt(price) < 0) {
			applicable = false;
		}
		return applicable;

	}

	public static boolean updateProgramDetails(String title, String durationTime, String difficultyLevel, String goals,
			String programId) {
		boolean updated = false;
		for (Program p : programsList) {
			if (p.getProgramId().equals(programId)) {
				System.out.println("updating details");

				if (!p.getTitle().equals(title)) {
					p.setTitle(title);
					updated = true;
				}
				if (!p.getDurationTime().equals(durationTime)) {
					p.setDurationTime(durationTime);
					updated = true;
				}
				if (!p.getdifficultyLevel().equals(difficultyLevel)) {
					p.setdifficultyLevel(difficultyLevel);
					updated = true;
				}
				if (!p.getGoals().equals(goals)) {
					p.setGoals(goals);
					updated = true;
				}
				if (updated) {
					System.out.println("Program details updated: " + p);
				}
			}
		}

		lastUpdate = updated;
		return updated;

	}

	public static boolean updateAttachments(ArrayList<Attachment> attachments, String programID) {
		boolean updated = false;
		for (Program p : programsList) {
			if (p.getProgramId().equals(programID)) {
				p.setAttachments(attachments);
				updated = true;
				System.out.println("Attachments:\n  Attachment Type  |  Attachment Path ");
				for (Attachment a : p.getAttachments()) {
					System.out.println(a.getType() + "  |  " + a.getPath());
				}
			}

		}
		lastUpdate = updated;
		return updated;
	}

	public static boolean updatePrice(String price, String programID) {
		boolean updated = false;
		for (Program p : programsList) {
			if (!p.getPrice().equals(price)) {

				if (checkIfApplicable(price)) {
					p.setPrice(price);
					updated = true;
				} else {
					System.out.println("re enter an applicable price");
				}

			}

		}
		lastUpdate = updated;
		return updated;
	}

	public static String generateProgramId() {
		int idTemp = 100000 + programsList.size(); // Starting point
		while (usedIds.contains(idTemp)) {
			idTemp++; // Increment until a unique ID is found
		}
		usedIds.add(idTemp); // Mark the ID as used
		return Integer.toString(idTemp);
	}

	public static boolean deleteProgram(String programId) {
		boolean deleted = false;
		Iterator<Program> iterator = programsList.iterator();

		while (iterator.hasNext()) {
			Program p = iterator.next();
			if (p.getProgramId().equals(programId)) {
				iterator.remove(); // Safe removal
				usedIds.remove(Integer.parseInt(programId));
				deleted = true;
				System.out.println("Program has been deleted: " + p);
			}
		}

		return deleted;
	}

	public static boolean setSchedule(String type, String schedule, String programId) {
		boolean setScheduleTest = false;
		for (Program p : programsList) {
			if (p.getProgramId().equals(programId)) {
				p.setGroupSession(type, schedule);
				setScheduleTest = true;
				if (setScheduleTest) {
					System.out.println("schedule has been set: " + p.getGroupSession());
				}
			}
		}
		lastUpdate = setScheduleTest;
		return setScheduleTest;

	}

	private static boolean setPriceForProgram(Program program, String price) {
		boolean priceAdded = false;
		if (checkIfApplicable(price)) {
			program.setPrice(price);
			priceAdded = true;
		} else {
			program.setPrice(null);
			System.out.println("price was not set");
		}

		return priceAdded;

	}

	public static Program getProgram(String programID) {
		for (Program p : programsList)
			if (p.getProgramId().equals(programID))
				return p;

		return null;
	}

	public static boolean getLastUpdate() {
		return lastUpdate;
	}

	public static ArrayList<Program> getProgramsList() {
		return programsList;
	}

	public static ArrayList<Program> getRankedPrograms() {
		ArrayList<Program> rankedPrograms = new ArrayList<>(programsList);

		rankedPrograms.sort((program1, program2) -> Integer.compare(program2.getEnrolledClientCount(),
				program1.getEnrolledClientCount()));

		return rankedPrograms;
	}

	public static void showRankedPrograms() {
		ArrayList<Program> rankedPrograms = new ArrayList<>(getRankedPrograms());
		for (Program p : rankedPrograms)
			if (p.getStatus().equals("Active"))
				System.out.println(p.getTitle() + " program is active and currently and have: "
						+ p.getEnrolledClientCount() + " clients enrolled");
	}

	public static boolean isProgramCreated() {
		return programCreated;
	}

	public static void setProgramCreated(boolean programCreated) {
		ProgramDataBase.programCreated = programCreated;
	}

	public static ArrayList<Program> getListDifficulty(String difficulty) {
		ArrayList<Program> filteredPrograms = new ArrayList<>();
		for (Program p : getProgramsList())
			if (p.getdifficultyLevel().equals(difficulty))
				filteredPrograms.add(p);

		return filteredPrograms;
	}

	public static ArrayList<Program> getListDuration(int duration) {
		ArrayList<Program> filteredPrograms = new ArrayList<>();
		for (Program p : getProgramsList())
			if (p.getTargetedHours() <= duration)
				filteredPrograms.add(p);
		return filteredPrograms;
	}

	public static ArrayList<User> getAllClientsForInstructor(String instructorId) {
		ArrayList<User> clients = new ArrayList<>();
		for (Program p : getProgramsForInstructor(instructorId)) {
			for (User u : p.getEnrolledClient()) {
				clients.add(u);
			}
		}
		return clients;

	}
}
