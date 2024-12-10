package FeaturesMain;

import java.util.ArrayList;

public class UserDataBase {
	private static ArrayList<User> usersList = new ArrayList<>();
	private static ArrayList<User> pendingInstructors = new ArrayList<>();

	// default test user
	static {

		User testAdmin = new User("Admin Test", "admin123", "Admin", "Activated", "1001");
		usersList.add(testAdmin);

		User testClient1 = new User("Client Test1", "client123", "Client", "Activated", "2002", 1);
		usersList.add(testClient1);

		User testClient2 = new User("Client Test2", "client456", "Client", "Activated", "3003");
		usersList.add(testClient2);
		
		User instructorTest = new User("instructor test", "instructor11", "instructor", "actived", "123123");
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
		userExist(user.getID());
		usersList.add(user);
		System.out.println("New user added: " + user);
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
