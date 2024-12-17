package FeaturesMain;

import java.util.ArrayList;

public class User {
	private String name;
	private String id;
	private String role;
	private String status;
	// for login
	private String password;
	// for client
	private ClientProfile profile;
	private int height;
	private int weight;
	private int NumOfPro;
	private ArrayList<AchievementBadge> earnedBadges = new ArrayList<>();
	private ArrayList<ProgramAttendence> attendence = new ArrayList<>();
	private ArrayList<InboxItem> inbox = new ArrayList<>();

	// constructor for backward compatibility (before i added password and profile)
	public User(String name, String id, String role, String status) {
		this(name, id, role, status, "default_password", null);
	}

	// constructor for backward compatibility (before i added profile)
	public User(String name, String id, String role, String status, String password) {
		this(name, id, role, status, password, null);
	}

	// Constructor with password and profile
	public User(String name, String id, String role, String status, String password, ClientProfile profile) {
		this.name = name;
		this.id = id;
		this.role = role;
		this.status = status;
		this.password = password;
		this.profile = profile;
		earnedBadges = new ArrayList<>();
		attendence = new ArrayList<>();
		inbox = new ArrayList<>();
	}

	public User(String name, String id, String role, String status, String password, int NumOfPro) {
		this.name = name;
		this.id = id;
		this.role = role;
		this.status = status;
		this.password = password;
		this.NumOfPro = NumOfPro;
		earnedBadges = new ArrayList<>();
	}

	// Getter for name
	public String getName() {
		return name;
	}

	// Setter for name
	public void setName(String name) {
		this.name = name;
	}

	// Getter for ID
	public String getID() {
		return id;
	}

	// Setter for ID
	public void setID(String id) {
		this.id = id;
	}

	// Getter for role
	public String getRole() {
		return role;
	}

	// Setter for role
	public void setRole(String role) {
		this.role = role;
	}

	// Getter for status
	public String getStatus() {
		return status;
	}

	// Setter for status
	public void setStatus(String status) {
		this.status = status;
	}

	// Override toString to include status
	@Override
	public String toString() {
		return "User [name=" + name + ", id=" + id + ", role=" + role + ", status=" + status + "]";
	}

	public String getPassword() {
		return password;
	}

	public void setProfile(ClientProfile profile) {
		this.profile = profile;
	}

	public ClientProfile getProfile() {
		return profile;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getWeight() {
		return weight;
	}

	public void setHeight(int i) {
		this.height = i;
	}

	public int getHeight() {
		return height;
	}

	public double getBMI() {
		double h = height / 100.0;
		double BMI = weight / (h * h);
		return (int) BMI; // No need to print here
	}

	public String getBodyStatus() {
		double BMI = getBMI();
		if (BMI > 25) {
			System.out.println("Overweight");
			return "Overweight";
		} else if (BMI < 19) {
			System.out.println("Underweight");
			return "Underweight";
		} else {
			System.out.println("Healthy");
			return "Healthy";
		}

	}

	public String getAction() {
		double h = height / 100.0;
		double goal = h * h * 25;
		String a;
		if (goal > weight) {
			a = "Gain: " + (int) (goal - weight) + " Kg";
			System.out.println(a);
			return a;
		} else {
			a = "Lose: " + (int) (weight - goal) + " Kg";
			System.out.println(a);
			return a;
		}
	}

	public void incNumOfPro() {
		++NumOfPro;
		// Add badges only if not already earned
		if (getNumOfPro() >= 1 && !earnedBadges.contains(AchievementBadge.NEWBIE_CHAMP)) {
			earnedBadges.add(AchievementBadge.NEWBIE_CHAMP);
		}
		if (getNumOfPro() >= 2 && !earnedBadges.contains(AchievementBadge.RISING_STAR)) {
			earnedBadges.add(AchievementBadge.RISING_STAR);
		}
		if (getNumOfPro() >= 3 && !earnedBadges.contains(AchievementBadge.FITNESS_WARRIOR)) {
			earnedBadges.add(AchievementBadge.FITNESS_WARRIOR);
		}
		if (getNumOfPro() >= 4 && !earnedBadges.contains(AchievementBadge.TENACIOUS_TITAN)) {
			earnedBadges.add(AchievementBadge.TENACIOUS_TITAN);
		}
		if (getNumOfPro() >= 5 && !earnedBadges.contains(AchievementBadge.GOAL_CRUSHER)) {
			earnedBadges.add(AchievementBadge.GOAL_CRUSHER);
		}
		if (getNumOfPro() >= 6 && !earnedBadges.contains(AchievementBadge.UNSTOPPABLE_FORCE)) {
			earnedBadges.add(AchievementBadge.UNSTOPPABLE_FORCE);
		}
		if (getNumOfPro() >= 7 && !earnedBadges.contains(AchievementBadge.LEGENDARY_GRIT)) {
			earnedBadges.add(AchievementBadge.LEGENDARY_GRIT);
		}
	}

	public int getNumOfPro() {
		return NumOfPro;
	}

	public ArrayList<AchievementBadge> getEarnedBadges() {
		return earnedBadges;
	}

	public void showBadges() {
		if (earnedBadges.isEmpty()) {
			System.out.println("No badges earned yet.");
		} else {
			System.out.println("Badges earned: ");
			for (AchievementBadge badge : earnedBadges) {
				System.out.println("- " + badge.getDescription());
			}
		}
	}

	public void addProgram(Program program) {
		ProgramAttendence enrolledInProgram = new ProgramAttendence(program);
		for (ProgramAttendence pe : attendence) {
			if (pe.getProgram().equals(enrolledInProgram.getProgram())) {
				System.out.println("program exists");
				return;
			}
		}
		attendence.add(enrolledInProgram);

	}

	public int getAttendence(Program program) {
		int t = 0;
		for (ProgramAttendence pe : attendence) {
			if (pe.getProgram().equals(program)) {
				t = pe.getAttendedSessions();
			}

		}
		return t;
	}

	public String getCompletionRate(Program program) {
		double percentage = 0;
		for (ProgramAttendence pe : attendence) {
			if (pe.getProgram().equals(program)) {
				Double rate = pe.getCompletionRate();
				if (rate != null) { // Avoid NullPointerException
					percentage = rate;
				}
			}
		}
		String completionRate = String.format("%.2f%%", percentage);
		return completionRate;
	}

	public void addItemToInbox(InboxItem item) {
		inbox.add(item);

	}

	public void setAttendence(String programId, int attended) {
		for (ProgramAttendence pe : attendence) {
			if (pe.getProgram().getProgramId().equals(programId)) {
				pe.setAttendedSessions(attended);
			}

		}

	}

	public void setAbsent(String programId) {
		for (ProgramAttendence pe : attendence) {
			if (pe.getProgram().getProgramId().equals(programId)) {
				pe.updateAbsent();
			}

		}
	}

	public String getReport(String programId) {
		for (ProgramAttendence pe : attendence) {
			if (pe.getProgram().getProgramId().equals(programId)) {
				return pe.createProgressReport();
			}

		}
		return "no data";
	}

	public ArrayList<Program> getPrograms() {
		ArrayList<Program> programs = new ArrayList<>();

		for (Program p : ProgramDataBase.getProgramsList()) {
			for (User u : p.getEnrolledClient()) {
				if (u.getID().equals(id))
					programs.add(p);
			}
		}

		return programs;
	}

}
