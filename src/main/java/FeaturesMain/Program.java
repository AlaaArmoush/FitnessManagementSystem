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
		this.enrolledClient.add(client);
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

}
