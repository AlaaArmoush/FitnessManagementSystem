package FeaturesMain;

import java.util.ArrayList;

public class Program {

	private String title;
	private String durationTime;
	private String difficultyLevel;
	private String goals;
	private String price;
	private Session groupSession;
	private ArrayList<Attachment> attachments;
	private String instructorId;
	private String programId;
	private ArrayList<User> enrolledClient = new ArrayList<>();
	private String programStatus;
	private Integer sessionsCount;
	private Integer targetedHours;

	public Program(String title, String durationTime, String difficultyLevel, String goals, String instructorId) {
		this(title, durationTime, difficultyLevel, goals, null, null);
		String id = ProgramDataBase.generateProgramId();
		this.programId = id;
		this.instructorId = instructorId;
	}

	public Program(String title, String durationTime, String difficultyLevel, String goals) {
		this(title, durationTime, difficultyLevel, goals, null, null);
	}

	public Program(String title, String durationTime, String difficultyLevel, String goals, String instructorId,
			String programId) {
		this.title = title;
		this.durationTime = durationTime;
		this.difficultyLevel = difficultyLevel;
		this.goals = goals;

		this.setInstructorId(instructorId);
		this.programId = programId;
		this.price = "0";
		this.attachments = new ArrayList<Attachment>();
		this.setTargetedHours(durationTime);

	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDurationTime() {
		return durationTime;
	}

	public void setDurationTime(String durationTime) {
		this.durationTime = durationTime;
	}

	public String getdifficultyLevel() {
		return difficultyLevel;
	}

	public void setdifficultyLevel(String difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}

	public String getGoals() {
		return goals;
	}

	public void setGoals(String goals) {
		this.goals = goals;
	}

	public String getProgramId() {
		return programId;
	}

	public void setProgramId(String programId) {
		this.programId = programId;
	}

	public String getInstructorId() {
		return instructorId;
	}

	public void setInstructorId(String instructorId) {
		this.instructorId = instructorId;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Session getGroupSession() {
		return groupSession;
	}

	public void setGroupSession(String type, String sessionSchedule) {
		Session groupSessionTemp = new Session(type, sessionSchedule);
		this.groupSession = groupSessionTemp;
	}

	public ArrayList<Attachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(ArrayList<Attachment> attachments) {
		this.attachments = attachments;
	}

	public void addAttachment(Attachment attachment) {
		this.attachments.add(attachment);
	}

	public void addClient(User client) {
		if (checkIfClientExistInProgram(client)) {
			// System.out.println("client was not added");
		} else {
			this.enrolledClient.add(client);
			client.addProgram(this);
			// System.out.println(client.getName() + " was added to " + this.getTitle());
		}

	}

	public void addClient(String clientId) {
		User client = UserDataBase.getUser(clientId);
		this.addClient(client);
	}

	public int getEnrolledClientCount() {
		return enrolledClient == null ? 0 : enrolledClient.size();
	}

	public void setStaus(int active) {
		if (active == 1)
			this.programStatus = "Active";
		else
			this.programStatus = "Currently Inactive";
	}

	public String toString() {
		return String.format("title: " + this.title + " duration time: " + this.durationTime + " difficulty level: "
				+ this.difficultyLevel + " goals: " + this.goals + " price: " + this.getPrice() + " program ID: "
				+ this.getProgramId());
	}

	public String getStatus() {
		return this.programStatus;
	}

	public void showClientsProgress() {
		for (User c : enrolledClient) {
			System.out.println("name: " + c.getName() + "BMI: " + c.getBMI() + "Attendence: " + c.getAttendence(this)
					+ " completion rate: " + c.getCompletionRate(this));
		}

	}

	public Integer getSessionsCount() {
		return sessionsCount;
	}

	public void setSessionsCount(Integer sessionsCount) {
		this.sessionsCount = sessionsCount;
	}

	public ArrayList<User> getEnrolledClient() {
		return this.enrolledClient;
	}

	public Integer getTargetedHours() {
		return targetedHours;
	}

	public void setTargetedHours(Integer targetedHours) {
		this.targetedHours = targetedHours;
	}

	private void setTargetedHours(String durationTime) {
		// Extract the numeric part using regular expressions
		String numberPart = durationTime.replaceAll("[^0-9]", "");

		// Validate if numberPart is not empty
		if (!numberPart.isEmpty()) {
			int result = Integer.parseInt(numberPart);
			this.targetedHours = result;
		} else {
			// Handle cases where no numbers are found
			System.err.println("Error: No numeric value found in durationTime: " + durationTime);
			this.targetedHours = 25;
		}

		// used this during debugging
		// System.out.println("Input durationTime: " + durationTime + ", Extracted
		// numberPart: " + numberPart);
	}

	private boolean checkIfClientExistInProgram(User client) {
		boolean exists = false;
		for (User u : enrolledClient) {
			if (u.getID().equals(client.getID())) {
				exists = true;
			}
		}
		return exists;
	}

	public boolean notifyClientsAboutNewSchedule() {
		boolean notified = InboxManagement.SendNotificationAboutNewSchedule(this.getProgramId(),
				this.getGroupSession().getSessionSchedule());
		return notified;
	}

	public boolean hasClient(String clientId) {
		for (User client : enrolledClient) {
			if (client.getID().equals(clientId)) {
				return true; // Client found
			}
		}
		return false; // Client not found
	}

}
