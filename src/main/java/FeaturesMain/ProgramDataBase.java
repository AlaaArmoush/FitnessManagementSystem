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
		addNewProgram(testProgram1);

		Program testProgram2 = new Program("yoga", "40 hours", "Beginners", "flexibility", "instructor11", "100001");
		addNewProgram(testProgram2);

		// added to test enrollment count
		Program testProgram3 = new Program("Cardio Blast", "20 hours", "Intermediate", "Endurance", "instructor14",
				"100002");
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
		System.out.println("target: "+newProgram.getTargetedHours());
		boolean priceSet = setPriceForProgram(newProgram, price);
		boolean attachmentAdded = addAttachmentsToProgram(newProgram, attachments);
		if (priceSet && attachmentAdded) {
			addNewProgram(newProgram);
			System.out.println("new program added: " + newProgram);
			programAdded = true;
			InboxManagement.announceNewProgram(newProgram.getProgramId());
		}
		setProgramCreated(programAdded);
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
		System.out.println("new program added: " + newProgram);
		return true;
	}

	public static boolean programExist(String id) {
		for (Program p : programsList)
			if (p.getProgramId().equals(id))
				return true;
		return false;

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
					InboxManagement.announceSpecialOffer(programID);
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
					InboxManagement.SendNotificationAboutNewSchedule(programId, schedule);
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
		System.out.println("program is null"+ programID);
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

}
