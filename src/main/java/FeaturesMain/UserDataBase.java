package FeaturesMain;

import java.util.ArrayList;

public class UserDataBase {
	private static ArrayList<User> usersList = new ArrayList<>();
	private static ArrayList<User> pendingInstructors = new ArrayList<>();

	public UserDataBase() {

	}

	// default test user
	static {

		User testAdmin = new User("Admin Test", "admin123", "Admin", "Activated", "1001");
		usersList.add(testAdmin);

		User testClient1 = new User("Client Test1", "client123", "Client", "Activated", "2002", 1);
		usersList.add(testClient1);

		User testClient2 = new User("Client Test2", "client456", "Client", "Activated", "3003");
		usersList.add(testClient2);

		// for testing programs popularity
		usersList.add(new User("Client Test3", "client789", "Client", "Activated", "3004"));
		usersList.add(new User("Client Test4", "client101", "Client", "Activated", "3005"));
		usersList.add(new User("Client Test5", "client102", "Client", "Activated", "3006"));
		usersList.add(new User("Client Test6", "client103", "Client", "Activated", "3007"));
		usersList.add(new User("Client Test7", "client104", "Client", "Activated", "3008"));
		usersList.add(new User("Client Test8", "client105", "Client", "Activated", "3009"));
		usersList.add(new User("Client Test9", "client106", "Client", "Activated", "3010"));
		usersList.add(new User("Client Test10", "client107", "Client", "Activated", "3011"));

		// for testing instructor progress tracking

		User clientTest1 = new User("Client Test01", "client790", "Client", "Activated", "30012");
		User clientTest2 = new User("Client Test02", "client791", "Client", "Activated", "30013");
		User clientTest3 = new User("Client Test03", "client792", "Client", "Activated", "30014");
		User clientTest4 = new User("Client Test04", "client793", "Client", "Activated", "30015");

		addUser(clientTest1);
		addUser(clientTest2);
		addUser(clientTest3);
		addUser(clientTest4);

		// Create profiles and assign them to existing users

		// Profile for client101
		ClientProfile P1 = new ClientProfile("25", 70, DietaryPreferences.VEGAN);
		UserDataBase.getUser("client101").setProfile(P1);
		UserDataBase.getUser("client101").setWeight(80);

		// Profile for client102
		ClientProfile P2 = new ClientProfile("30", 75, DietaryPreferences.VEGETARIAN);
		UserDataBase.getUser("client102").setProfile(P2);
		UserDataBase.getUser("client102").setWeight(85);

		// Profile for client103
		ClientProfile P3 = new ClientProfile("20", 60, DietaryPreferences.KETO);
		UserDataBase.getUser("client103").setProfile(P3);
		UserDataBase.getUser("client103").setWeight(65);

		// Profile for client104
		ClientProfile P4 = new ClientProfile("28", 68, DietaryPreferences.VEGAN);
		UserDataBase.getUser("client104").setProfile(P4);
		UserDataBase.getUser("client104").setWeight(75);

		// Profile for client105
		ClientProfile P5 = new ClientProfile("35", 80, DietaryPreferences.VEGETARIAN);
		UserDataBase.getUser("client105").setProfile(P5);
		UserDataBase.getUser("client105").setWeight(90);
		// -------------------------------------------------------------------

		User instructorTest = new User("instructor test", "instructor11", "Instructor", "Activated", "123123");
		usersList.add(instructorTest);

	}

	public static boolean updateUserDetails(String name, String id, String role, String status) {
		// Loop through the users list to find the matching user by ID
		for (User u : usersList) {
			if (u.getID().equals(id)) {
				boolean updated = false;

				// Update details only if there's a change
				if (!u.getName().equals(name)) {
					u.setName(name);
					updated = true;
				}

				if (!u.getRole().equals(role)) {
					u.setRole(role);
					updated = true;
				}

				if (!u.getStatus().equals(status)) {
					u.setStatus(status);
					updated = true;
				}

				// Log the update if there were any changes
				if (updated) {
					System.out.println("User details updated: " + u);
				}
				return true; // User found and updated
			}
		}

		return false; // User not found
	}

	public static boolean addUser(User user) {
		// check if already exists
		if (userExist(user.getID())) {
			System.out.println("user already exists");
		} else {
			usersList.add(user);
			// System.out.println("New user added: " + user);
		}

		return true;
	}

	public static boolean addUser(String name, String id, String role) {
		// check if already exists
		userExist(id);

		// If user does not exist, create and add a new user
		User newUser = new User(name, id, role, "Activated");
		usersList.add(newUser);
		System.out.println("New user added: " + newUser);
		return true;
	}

	public static ArrayList<User> getUsersList() {
		return usersList;
	}

	public static User getUser(String id) {
		for (User u : usersList)
			if (u.getID().equals(id))
				return u;
		return null;
	}

	public static boolean userExist(String id) {
		for (User u : usersList)
			if (u.getID().equals(id))
				return true;
		return false;
	}

	public static boolean deactivate(String id) {
		for (User u : usersList) {
			if (u.getID().equals(id)) {
				u.setStatus("Deactivated");
				System.out.println("User Deactivated: " + u);
				return true;
			}
		}
		return false;
	}

	// Method to add pending instructor request with string parameters
	public static boolean addPendingInstructor(String id, String name, String password) {
		// Create a new user with default status "Pending"
		User newInstructor = new User(name, id, "Instructor", "Pending");
		// Add the instructor to the pending list
		newInstructor.setPassword(password);
		pendingInstructors.add(newInstructor);
		System.out.println("Instructor registration request added to pending: " + newInstructor);
		return true;
	}

	public static boolean rejectInstructorRequest(String id) {
		for (User u : pendingInstructors) {
			if (u.getID().equals(id)) {
				pendingInstructors.remove(u);
				System.out.println("Instructor registration rejected: " + u);
				return true;
			}
		}
		return false;
	}

	// Method to approve pending instructor and activate their account
	public static boolean approveInstructorRequest(String id) {
		for (User u : pendingInstructors) {
			if (u.getID().equals(id)) {
				// Remove from pending list and add to users list
				pendingInstructors.remove(u);
				u.setStatus("Activated");
				usersList.add(u);
				System.out.println("Instructor registration approved: " + u);
				return true;
			}
		}
		return false;
	}

	public static int getActiveUserCount(String role) {
		int count = 0;
		for (User u : usersList) {
			if (u.getRole().equals(role) && u.getStatus().equals("Activated")) {
				count++;
			}
		}
		return count;
	}

	public static boolean login(String id, String password) {
		for (User user : usersList) {
			if (user.getID().equals(id) && user.getPassword().equals(password)) {
				return true;
			}
		}

		return false;
	}

	public static String generateRevenueEstimate() {
		// Get the number of active clients and instructors
		int activeClients = getActiveUserCount("Client");
		int activeInstructors = getActiveUserCount("Instructor");

		// Define the average monthly subscription for clients (175 ILS)
		int clientSubscription = 175;

		// Define the monthly salary for instructors (200 ILS)
		int instructorSalary = 200;

		long grossRevenue = (long) activeClients * clientSubscription;

		long netRevenue = grossRevenue - (activeInstructors * instructorSalary);
		String report = "-------------------------\n";
		report += "Monthly Revenue Report:\n";
		report += "-------------------------\n";
		report += "Active Clients: " + activeClients + "\n";
		report += "Active Instructors: " + activeInstructors + "\n";
		report += "Gross Revenue: " + grossRevenue + " ILS\n";
		report += "Net Revenue: " + netRevenue + " ILS\n";

		return report;
	}

	public static String generateClientProgressReport() {
		StringBuilder report = new StringBuilder();
		report.append("-------------------------\n");
		report.append("Client Progress Report:\n");
		report.append("-------------------------\n");

		for (User user : usersList) {
			if (user.getRole().equals("Client") && user.getProfile() != null) {
				ClientProfile profile = user.getProfile();
				int currentWeight = user.getWeight();
				int goalWeight = profile.getGoal();

				report.append("Client ID: ").append(user.getID()).append("\n");
				report.append("Name: ").append(user.getName()).append("\n");
				report.append("Current Weight: ").append(currentWeight).append(" kg\n");
				report.append("Goal Weight: ").append(goalWeight).append(" kg\n");

				if (currentWeight > goalWeight) {
					report.append("Status: Needs to lose ").append(currentWeight - goalWeight).append(" kg\n");
				} else if (currentWeight < goalWeight) {
					report.append("Status: Needs to gain ").append(goalWeight - currentWeight).append(" kg\n");
				} else {
					report.append("Status: Goal achieved!\n");
				}

				report.append("-------------------------\n");
			}
		}

		if (report.toString()
				.equals("-------------------------\nClient Progress Report:\n-------------------------\n")) {
			return "No clients with profiles available for the report.";
		}

		return report.toString();
	}

	public static String generateClientAttendanceReport() {
		StringBuilder report = new StringBuilder();
		report.append("-------------------------\n");
		report.append("Client Attendance Report:\n");
		report.append("-------------------------\n");

		for (User user : usersList) {
			if (user.getRole().equals("Client")) {

				report.append("Client ID: ").append(user.getID()).append("\n");
				report.append("Name: ").append(user.getName()).append("\n");
				for (Program program : user.getPrograms()) {
					report.append("Program Title: ").append(program.getTitle()).append("\n");
					report.append("Attendance: ").append(user.getAttendence(program)).append("%\n");
					// report.append("Completion Rate:
					// ").append(user.getCompletionRate(program)).append("%\n");
				}

				report.append("-------------------------\n");
			}
		}

		if (report.toString()
				.equals("-------------------------\nClient Attendance Report:\n-------------------------\n")) {
			return "No clients with attendance records available for the report.";
		}

		return report.toString();
	}

	public static ArrayList<User> getPendingInstructors() {
		return pendingInstructors;
	}

}
