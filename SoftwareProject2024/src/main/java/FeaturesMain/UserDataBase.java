package FeaturesMain;

import java.util.ArrayList;

public class UserDataBase {
	private static ArrayList<User> usersList = new ArrayList<>();
	private static ArrayList<User> pendingInstructors = new ArrayList<>();

	// default test user
	static {

		User testAdmin = new User("Admin Test", "admin123", "Admin", "Activated", "1001");
		usersList.add(testAdmin);
	}

	public static boolean addUser(User user) {
		// Check if the user already exists by ID
		for (User u : usersList) {
			if (u.getID().equals(user.getID())) {
				u.setName(user.getName());
				u.setRole(user.getRole());
				// note when "updating" we only change name/role not status
				System.out.println("User details updated: " + u);
				return true;
			}
		}

		// If user does not exist, add a new user
		usersList.add(user);
		System.out.println("New user added: " + user);
		return true;
	}

	// Second method: Takes name, id, and role as string arguments
	public static boolean addUser(String name, String id, String role) {
		// Check if the user already exists by ID
		for (User u : usersList) {
			if (u.getID().equals(id)) {
				// If the user exists, update the details
				u.setName(name);
				u.setRole(role);
				// Note: status is not updated here, it remains the same
				System.out.println("User details updated: " + u);
				return true;
			}
		}

		// If user does not exist, create a new user and add to the list
		User newUser = new User(name, id, role, "Activated"); // Default status as "Activated"
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
	public static boolean addPendingInstructor(String id, String name) {
		// Create a new user with default status "Pending"
		User newInstructor = new User(name, id, "Instructor", "Pending");
		// Add the instructor to the pending list
		pendingInstructors.add(newInstructor);
		System.out.println("Instructor registration request added to pending: " + newInstructor);
		return true;
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
}
