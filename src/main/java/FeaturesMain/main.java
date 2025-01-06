package FeaturesMain;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome To Our Fitness Management System!");
		boolean isLoggedOut = false;
		do {
			int choice = showMainMenu(scanner);

			switch (choice) {
			case 1:
				handleLogin(scanner);
				break;
			case 2:
				handleSignUp(scanner);
				break;
			default:
				System.out.println("Invalid option. Please select 1 for Log In or 2 for Sign Up.");
				break;
			}

		} while (!isLoggedOut);
	}

	private static int showMainMenu(Scanner scanner) {
		System.out.println("Please choose an option:");
		System.out.println("1. Log In");
		System.out.println("2. Sign Up as Instructor");
		System.out.print("Enter your choice: ");
		int choice = scanner.nextInt();
		scanner.nextLine(); // Consume the newline character left by nextInt()
		return choice;
	}

	private static void handleLogin(Scanner scanner) {
		String id = getValidUserId(scanner, false);
		if (id != null) {
			String pass = getPassword(scanner);
			if (UserDataBase.getUser(id).getPassword().equals(pass)) {
				System.out.println("Welcome " + UserDataBase.getUser(id).getName() + "!");
				navigateToUserRole(id);
			} else {
				System.out.println("Wrong Credentials !!!");
			}
		}
	}

	private static void handleSignUp(Scanner scanner) {
		String id = getValidUserId(scanner, true);
		if (id != null) {
			String name = getName(scanner);
			String pass = getPassword(scanner);
			if (UserDataBase.addPendingInstructor(id, name, pass)) {
				System.out.println("Instructor registration request has been submitted for approval.");
			} else {
				System.out.println("Failed to submit the registration request. Please try again.");
			}
		}
	}

	private static String getValidUserId(Scanner scanner, boolean isSignUp) {
		String id = null;
		while (id == null) {
			System.out.print("Please Enter Your ID: ");
			id = scanner.nextLine();
			if (!UserDataBase.userExist(id)) {
				if (isSignUp) {
					// Allow new user ID for sign-up if it's not already in the system
					break;
				} else {
					System.out.println("Please Enter A Valid ID!!!");
					id = null; // Invalid ID, ask again
				}
			} else {
				break;
			}
		}
		return id;
	}

	private static String getName(Scanner scanner) {
		System.out.print("Please Enter Your Name: ");
		return scanner.nextLine();
	}

	private static String getPassword(Scanner scanner) {
		System.out.print("Please Enter Your Password: ");
		return scanner.nextLine();
	}

	private static void navigateToUserRole(String userId) {
		String role = UserDataBase.getUser(userId).getRole();
		switch (role) {
		case "Admin":
			AdminMenu();
			break;
		case "Instructor":
			InstructorMenu(userId);
			break;
		case "Client":
			ClientMenu(userId);
			break;
		default:
			System.out.println("Invalid role.");
			break;
		}
	}

	// *****************************Admin Menu**************************
	private static void AdminMenu() {
		Scanner scanner = new Scanner(System.in);
		boolean logged = true;

		while (logged) {
			System.out.println("\n--- Admin Features ---");
			System.out.println("1. User Management");
			System.out.println("2. Program Monitoring");
			System.out.println("3. Content Management");
			System.out.println("4. Subscription Management");
			System.out.println("5. Logout");
			System.out.print("Please select an option: ");

			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1:
				AdminUserManagementMenu();
				break;

			case 2:
				AdminProgramMonitoringMenu();
				break;

			case 3:
				AdminContentManagement();
				break;

			case 4:
				AdminSubscriptionManagement();
				break;

			case 5:
				logged = handleLogOut();
				break;

			default:
				System.out.println("Invalid option. Please try again.");
				break;
			}
		}
	}

	private static void AdminUserManagementMenu() {
		Scanner scanner = new Scanner(System.in);

		System.out.println("\n--- User Management ---");
		System.out.println("1. Add a new user account.");
		System.out.println("2. Update user account details.");
		System.out.println("3. Deactivate a user account.");
		System.out.println("4. Approve new instructor registrations.");
		System.out.println("5. Monitor user activity statistics.");
		System.out.println("6. Back to Admin Menu");
		System.out.print("Please select an option: ");

		int userChoice = scanner.nextInt();
		scanner.nextLine();

		switch (userChoice) {
		case 1:
			addANewUser(scanner);
			break;
		case 2:
			updateUserDetails(scanner);
			break;
		case 3:
			deactivateUser(scanner);
			break;
		case 4:
			approveOrRejectInstructor(scanner);
			break;
		case 5:
			monitorUserActivity();
			break;
		case 6:
			System.out.println("Returning to Admin Menu...");
			return; // Exit back to Admin Menu
		default:
			System.out.println("Invalid option.");
			break;
		}
	}

	private static void approveOrRejectInstructor(Scanner scanner) {
		if (UserDataBase.getPendingInstructors().isEmpty()) {
			System.out.println("No new applications.");
			return;
		}

		System.out.println("\n--- Pending Instructor Registrations ---");
		for (User instructor : UserDataBase.getPendingInstructors()) {
			System.out.println("ID: " + instructor.getID() + " - Name: " + instructor.getName());
		}

		System.out.print("Enter the ID of the instructor to approve or reject: ");
		String instructorID = scanner.nextLine();

		System.out.print("Enter your choice (approve/reject): ");
		String choice = scanner.nextLine().toLowerCase();

		if (choice.equals("approve")) {
			if (UserDataBase.approveInstructorRequest(instructorID)) {
				System.out.println("Instructor with ID " + instructorID + " has been approved.");
			} else {
				System.out.println("Instructor with ID " + instructorID + " not found in pending list.");
			}
		} else if (choice.equals("reject")) {
			if (UserDataBase.rejectInstructorRequest(instructorID)) {
				System.out.println("Instructor with ID " + instructorID + " has been rejected.");
			} else {
				System.out.println("Instructor with ID " + instructorID + " not found in pending list.");
			}
		} else {
			System.out.println("Invalid choice. Please enter 'approve' or 'reject'.");
		}
	}

	private static void monitorUserActivity() {
		System.out.println("Monitoring user activity statistics...");
		int activeClients = UserDataBase.getActiveUserCount("Client");
		int activeInstructors = UserDataBase.getActiveUserCount("Instructor");
		System.out.println("Active Clients: " + activeClients);
		System.out.println("Active Instructors: " + activeInstructors);
	}

	private static void deactivateUser(Scanner scanner) {
		printUsers();
		System.out.print("Enter user ID to deactivate: ");
		String deactivateId = scanner.nextLine();
		boolean deactivated = UserDataBase.deactivate(deactivateId);
		if (deactivated) {
			System.out.println("User account deactivated successfully.");
		} else {
			System.out.println("Failed to deactivate user account.");
		}
	}

	private static void updateUserDetails(Scanner scanner) {
		printUsers();
		System.out.print("Enter user ID to update: ");
		String updateId = scanner.nextLine();
		System.out.print("Enter new name: ");
		String newName = scanner.nextLine();
		System.out.print("Enter new role: ");
		String newRole = scanner.nextLine();
		boolean updated = UserDataBase.updateUserDetails(newName, updateId, newRole, "Activated");
		if (updated) {
			System.out.println("User account updated successfully.");
		} else {
			System.out.println("Failed to update user account.");
		}
	}

	private static void printUsers() {
		for (User u : UserDataBase.getUsersList())
			if (!u.getRole().equals("Admin"))
				System.out.println(u.getID() + " : " + u.getName());
	}

	private static void addANewUser(Scanner scanner) {
		System.out.print("Enter user name: ");
		String name = scanner.nextLine();
		System.out.print("Enter user ID: ");
		String id = scanner.nextLine();
		System.out.print("Enter user password: ");
		String pass = scanner.nextLine();
		System.out.print("Enter user role: ");
		String role = scanner.nextLine();
		System.out.print("Enter user status: ");
		String status = scanner.nextLine();

		User newUser = new User(name, id, role, status);
		newUser.setPassword(pass);
		boolean userAdded = UserDataBase.addUser(newUser);
		if (userAdded) {
			System.out.println("User account created successfully.");
		} else {
			System.out.println("Failed to create user account.");
		}
	}

	private static void AdminProgramMonitoringMenu() {
		Scanner scanner = new Scanner(System.in);

		boolean monitoring = true;

		while (monitoring) {
			System.out.println("\n--- Program Monitoring ---");
			System.out.println("1. View Program Ranking by Popularity");
			System.out.println("2. View Monthly Revenue Report");
			System.out.println("3. View Monthly Client Progress Report");
			System.out.println("4. View Client Attendance Report");
			System.out.println("5. Back to Admin Menu");
			System.out.print("Please select an option: ");

			int choice = scanner.nextInt();
			scanner.nextLine(); // Consume the newline

			switch (choice) {
			case 1:
				// Display Program Ranking by Popularity
				displayProgramRanking();
				break;

			case 2:
				// Display Monthly Revenue Report
				displayMonthlyRevenueReport();
				break;

			case 3:
				// Display Monthly Client Progress Report
				displayClientProgressReport();
				break;

			case 4:
				// Display Client Attendance Report
				displayClientAttendanceReport();
				break;

			case 5:
				System.out.println("Returning to Admin Menu...");
				monitoring = false;
				break;

			default:
				System.out.println("Invalid option. Please try again.");
				break;
			}
		}
	}

	// Helper Methods for Report Generation (Admin Program Monitoring)
	private static void displayProgramRanking() {
		ArrayList<Program> rankedPrograms = ProgramDataBase.getRankedPrograms();

		// Sorting the programs by the number of enrolled clients (descending order)
		rankedPrograms.sort((p1, p2) -> Integer.compare(p2.getEnrolledClientCount(), p1.getEnrolledClientCount()));

		System.out.println("\n--- Program Ranking by Popularity ---");
		for (Program program : rankedPrograms) {
			System.out.println("Program ID: " + program.getProgramId() + " | Enrolled Clients: "
					+ program.getEnrolledClientCount() + " | Status: " + program.getStatus());
		}
	}

	private static void displayMonthlyRevenueReport() {
		String report = UserDataBase.generateRevenueEstimate();
		System.out.println("\n--- Monthly Revenue Report ---");
		System.out.println(report);
	}

	private static void displayClientProgressReport() {
		String report = UserDataBase.generateClientProgressReport();
		System.out.println("\n--- Monthly Client Progress Report ---");
		System.out.println(report);
	}

	private static void displayClientAttendanceReport() {
		String report = UserDataBase.generateClientAttendanceReport();
		System.out.println("\n--- Client Attendance Report ---");
		System.out.println(report);
	}

	private static void AdminContentManagement() {
		Scanner scanner = new Scanner(System.in);
		boolean logged = true;

		while (logged) {
			System.out.println("\n--- Admin Content Management ---");
			System.out.println("1. Show pending articles.");
			System.out.println("2. View approved articles.");
			System.out.println("3. View client feedback.");
			System.out.println("4. View client program reviews.");
			System.out.println("5. Back to Admin Menu");
			System.out.print("Please select an option: ");

			int choice = scanner.nextInt();
			scanner.nextLine(); // Consume newline character

			switch (choice) {
			case 1:
				showPendingArticles();
				break;
			case 2:
				viewApprovedArticles();
				break;
			case 3:
				viewClientFeedback();
				break;
			case 4:
				viewClientProgramReviews();
				break;
			case 5:
				System.out.println("Returning to Admin Menu...");
				logged = false;
				break;
			default:
				System.out.println("Invalid option. Please try again.");
				break;
			}
		}
	}

	private static void showPendingArticles() {
		Scanner scanner = new Scanner(System.in);

		// Display the list of pending articles
		ArrayList<Article> pendingArticles = ArticlesDataBase.getPendingArticles();
		if (printPendingArticles(pendingArticles)) {
			System.out.print("Enter article ID to approve or reject: ");
			String id = scanner.nextLine();

			System.out.print("Type 'A' to approve or 'R' to reject: ");
			String action = scanner.nextLine().toUpperCase();

			if (action.equals("A") || action.equals("a")) {
				if (ArticlesDataBase.approveArticle(id)) {
					System.out.println("Article approved successfully.");
				} else {
					System.out.println("Failed to approve the article.");
				}
			} else if (action.equals("R") || action.equals("r")) {
				if (ArticlesDataBase.rejectArticle(id)) {
					System.out.println("Article rejected successfully.");
				} else {
					System.out.println("Failed to reject the article.");
				}
			} else {
				System.out.println("Invalid action. Please enter 'A' to approve or 'R' to reject.");
			}
		} else {
			System.out.println("No pending articles available.");
		}
	}

	// Helper method to print pending articles
	private static boolean printPendingArticles(ArrayList<Article> pendingArticles) {
		if (pendingArticles != null && !pendingArticles.isEmpty()) {
			System.out.println("\n--- Pending Articles ---");
			for (Article article : pendingArticles) {
				System.out.println("Article ID: " + article.getID() + " | Title: " + article.getTitle());
			}
			return true;
		}
		return false;
	}

	private static void viewApprovedArticles() {
		ArrayList<Article> articlesList = ArticlesDataBase.getApprovedList();
		if (articlesList != null && !articlesList.isEmpty()) {
			System.out.println("****** Approved Articles ******");
			for (Article article : articlesList) {
				System.out.println(article);
			}
			System.out.println("*******************************");
		} else {
			System.out.println("No approved articles available.");
		}
	}

	private static void viewClientFeedback() {
		ArrayList<Feedback> feedbackList = ReviewsDataBase.getFeedbackList();
		if (feedbackList != null && !feedbackList.isEmpty()) {
			System.out.println("****** Client Feedback ******");
			for (Feedback feedback : feedbackList) {
				System.out.println(feedback);
			}
			System.out.println("*****************************");
		} else {
			System.out.println("No feedback available.");
		}
	}

	private static void viewClientProgramReviews() {
		ArrayList<Review> reviewsList = ReviewsDataBase.getReviewsList();
		if (reviewsList != null && !reviewsList.isEmpty()) {
			System.out.println("****** Client Program Reviews ******");
			for (Review review : reviewsList) {
				System.out.println(review);
			}
			System.out.println("**********************************");
		} else {
			System.out.println("No reviews available.");
		}
	}

	private static void AdminSubscriptionManagement() {
		Scanner scanner = new Scanner(System.in);
		boolean logged = true;

		while (logged) {
			System.out.println("\n--- Admin Subscription Management ---");
			System.out.println("1. Create a new subscription plan.");
			System.out.println("2. Update an existing subscription plan.");
			System.out.println("3. View subscription plans.");
			System.out.println("4. Delete a subscription plan.");
			System.out.println("5. Back to Admin Menu");
			System.out.print("Please select an option: ");

			int choice = scanner.nextInt();
			scanner.nextLine(); // Consume newline character

			switch (choice) {
			case 1:
				createNewSubscriptionPlan();
				break;
			case 2:
				updateSubscriptionPlan();
				break;
			case 3:
				viewSubscriptionPlans();
				break;
			case 4:
				deleteSubscriptionPlan();
				break;
			case 5:
				System.out.println("Returning to Admin Menu...");
				logged = false;
				break;
			default:
				System.out.println("Invalid option. Please try again.");
				break;
			}
		}
	}

	private static void createNewSubscriptionPlan() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter tier: ");
		String tier = scanner.nextLine();
		System.out.print("Enter price: ");
		String price = scanner.nextLine();
		System.out.print("Enter discount: ");
		String discount = scanner.nextLine();
		System.out.print("Enter days per month: ");
		String days = scanner.nextLine();

		Subscription newPlan = new Subscription(tier, price, discount, days);
		boolean planCreated = SubsDataBase.addPlan(newPlan);
		if (planCreated) {
			System.out.println("Subscription plan created successfully.");
		} else {
			System.out.println("Failed to create the subscription plan.");
		}
	}

	private static void updateSubscriptionPlan() {
		Scanner scanner = new Scanner(System.in);
		printTiers();
		System.out.print("Enter tier of the plan to update: ");
		String tier = scanner.nextLine();
		System.out.print("Enter new price: ");
		String price = scanner.nextLine();
		System.out.print("Enter new discount: ");
		String discount = scanner.nextLine();
		System.out.print("Enter new days per month: ");
		String days = scanner.nextLine();

		boolean updated = SubsDataBase.updatePlan(tier, price, discount, days);
		if (updated) {
			System.out.println("Subscription plan updated successfully.");
		} else {
			System.out.println("Failed to update the subscription plan.");
		}
	}

	private static void viewSubscriptionPlans() {
		ArrayList<Subscription> subscriptionPlans = SubsDataBase.getAllSubscriptionPlans();
		if (subscriptionPlans != null && !subscriptionPlans.isEmpty()) {
			System.out.println("****** Subscription Plans ******");
			for (Subscription plan : subscriptionPlans) {
				System.out.println("Tier: " + plan.getTier());
				System.out.println("Price: " + plan.getPrice());
				System.out.println("Discount: " + plan.getDiscount());
				System.out.println("Days per month: " + plan.getDays());
				System.out.println("--------------------------------");
			}
		} else {
			System.out.println("No subscription plans available.");
		}
	}

	private static void deleteSubscriptionPlan() {
		Scanner scanner = new Scanner(System.in);
		printTiers();
		System.out.print("Enter tier of the plan to delete: ");
		String tier = scanner.nextLine();

		boolean deleted = SubsDataBase.deletePlan(tier);
		if (deleted) {
			System.out.println("Subscription plan deleted successfully.");
		} else {
			System.out.println("Failed to delete the subscription plan.");
		}
	}

	public static void printTiers() {
		System.out.println("Available Plans:");
		HashSet<String> uniqueTiers = new HashSet<>();
		for (Subscription s : SubsDataBase.getAllSubscriptionPlans()) {
			uniqueTiers.add(s.getTier());
		}
		for (String tier : uniqueTiers) {
			System.out.println(tier);
		}
	}

//***************************************Instructor Menu*************************************************
	private static void InstructorMenu(String instructorId) {
		Scanner scanner = new Scanner(System.in);
		boolean logged = true;

		while (logged) {
			System.out.println("\n--- Instructor Features ---");
			System.out.println("1. Submit an Article");
			System.out.println("2. Client Interaction");
			System.out.println("3. Program Management");
			System.out.println("4. Progress Tracking");
			System.out.println("5. Notifications and Updates");
			System.out.println("6. Logout");
			System.out.print("Please select an option: ");

			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1:
				InstructorSubmitArticle();
				break;

			case 2:
				InstructorClientInteraction(instructorId);
				break;

			case 3:
				InstructorProgramManagement(instructorId);
				break;

			case 4:
				InstructorProgressTracking(instructorId);
				break;

			case 5:
				InstructorNotificationsAndUpdates(instructorId);
				break;

			case 6:
				logged = handleLogOut();
				break;

			default:
				System.out.println("Invalid option. Please try again.");
				break;
			}
		}
	}

	private static void InstructorSubmitArticle() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("\n--- Submit an Article ---");

		System.out.print("Enter Article ID: ");
		String id = scanner.nextLine();

		System.out.print("Enter Article Title: ");
		String title = scanner.nextLine();

		System.out.print("Enter Article URL: ");
		String urlString = scanner.nextLine();

		try {
			URL url = new URL(urlString);
			ArticlesDataBase.addArticle(id, title, url); // Use instructorId for tracking
			System.out.println("Article submitted successfully and is pending approval.");
		} catch (MalformedURLException e) {
			System.err.println("Invalid URL format. Please try again.");
		}
	}

	private static void InstructorNotificationsAndUpdates(String instructorId) {
		Scanner scanner = new Scanner(System.in);
		boolean running = true;

		while (running) {
			System.out.println("\n--- Notifications and Updates ---");
			System.out.println("1. Update Program Schedule");
			System.out.println("2. Create New Program");
			System.out.println("3. Update Program Price");
			System.out.println("4. Back to Menu");
			System.out.print("Choose an option: ");

			int choice = scanner.nextInt();
			scanner.nextLine(); // Clear the buffer

			switch (choice) {
			case 1:
				updateProgramSchedule(scanner, instructorId);
				break;
			case 2:
				createNewProgram(instructorId, scanner);
				break;
			case 3:
				updateProgramPrice(scanner, instructorId);
				break;
			case 4:
				running = false;
				break;
			default:
				System.out.println("Invalid option. Please try again.");
			}
		}
	}

	private static void updateProgramSchedule(Scanner scanner, String instructorId) {
		printPrograms(instructorId);
		System.out.print("Enter Program ID to update the schedule: ");
		String programId = scanner.nextLine();
		System.out.print("Enter new schedule details: ");
		String newSchedule = scanner.nextLine();

		if (ProgramDataBase.setSchedule("online", newSchedule, programId)) {
			System.out.println("Schedule updated successfully.");
			if (InboxManagement.SendNotificationAboutNewSchedule(programId, newSchedule)) {
				System.out.println("Clients have been notified of the new schedule.");
			} else {
				System.out.println("Failed to notify clients.");
			}
		} else {
			System.out.println("Failed to update the schedule. Please check the program ID.");
		}
	}

	private static void createNewProgram(String instructorId, Scanner scanner) {
		System.out.print("Enter Program Title: ");
		String title = scanner.nextLine();
		System.out.print("Enter Duration: ");
		String duration = scanner.nextLine();
		System.out.print("Enter Difficulty Level: ");
		String difficulty = scanner.nextLine();
		System.out.print("Enter Goal: ");
		String goal = scanner.nextLine();

		System.out.print("Enter Program ID: ");
		String newProgramId = scanner.nextLine();
		System.out.print("Enter Price: ");
		String price = scanner.nextLine();

		ArrayList<Attachment> attachments = new ArrayList<>();
		attachments.add(new Attachment("document", "Program Overview"));

		if (ProgramDataBase.addNewProgram(title, duration, difficulty, goal, instructorId, newProgramId, attachments,
				price)) {
			System.out.println("New program created successfully.");
			if (InboxManagement.announceNewProgram(newProgramId)) {
				System.out.println("Clients have been notified about the new program.");
			} else {
				System.out.println("Failed to notify clients.");
			}
		} else {
			System.out.println("Failed to create the program. Please try again.");
		}
	}

	private static void updateProgramPrice(Scanner scanner, String instructorId) {
		printPrograms(instructorId);
		System.out.print("Enter Program ID to update the price: ");
		String programId = scanner.nextLine();
		System.out.print("Enter new price: ");
		String newPrice = scanner.nextLine();

		if (ProgramDataBase.updatePrice(newPrice, programId)) {
			System.out.println("Price updated successfully.");
			if (InboxManagement.announceSpecialOffer(programId)) {
				System.out.println("Clients have been notified about the new price.");
			} else {
				System.out.println("Failed to notify clients.");
			}
		} else {
			System.out.println("Failed to update the price. Please check the program ID.");
		}
	}

	private static void InstructorProgressTracking(String instructorId) {
		Scanner scanner = new Scanner(System.in);
		boolean running = true;

		while (running) {
			System.out.println("\n--- Progress Tracking ---");
			System.out.println("1. View Client Progress for a Program");
			System.out.println("2. Send Motivational Reminder to Clients");
			System.out.println("3. Back to Menu");
			System.out.print("Choose an option: ");

			int choice = scanner.nextInt();
			scanner.nextLine(); // Clear the buffer

			switch (choice) {
			case 1:
				viewClientProgress(scanner, instructorId);
				break;
			case 2:
				sendMotivationalReminder(scanner, instructorId);
				break;
			case 3:
				running = false;
				break;
			default:
				System.out.println("Invalid option. Please try again.");
			}
		}
	}

	private static void viewClientProgress(Scanner scanner, String instructorId) {
		printPrograms(instructorId);
		System.out.print("Enter Program ID to view client progress: ");
		String programId = scanner.nextLine();

		// Check if the program exists
		if (ProgramDataBase.programExist(programId)) {
			boolean progressDisplayed = ProgressTracking.displayClientsProgress(programId);
			if (progressDisplayed) {
				System.out.println("Clients' progress displayed successfully.");
			} else {
				System.out.println("Failed to display clients' progress.");
			}
		} else {
			System.out.println("Invalid Program ID. Please try again.");
		}
	}

	private static void printPrograms(String instructorId) {
		ArrayList<Program> instructorPrograms = ProgramDataBase.getProgramsForInstructor(instructorId);
		for (Program p : instructorPrograms)
			System.out.println(p.getProgramId() + " : " + p.getTitle());
		System.out.print("Enter the Program ID: ");

	}

	private static void sendMotivationalReminder(Scanner scanner, String instructorId) {
		printPrograms(instructorId);
		System.out.print("Enter Program ID to send motivational reminder: ");
		String programId = scanner.nextLine();

		// Check if the program exists
		if (ProgramDataBase.programExist(programId)) {
			System.out.print("Enter motivational message: ");
			String message = scanner.nextLine();

			// Send reminder to all clients enrolled in the program
			boolean reminderSent = InboxManagement.sendReminderToAllClients(message, programId);
			if (reminderSent) {
				System.out.println("Motivational reminder sent to all clients.");
			} else {
				System.out.println("Failed to send reminder. Please try again.");
			}
		} else {
			System.out.println("Invalid Program ID. Please try again.");
		}
	}

	private static void InstructorProgramManagement(String instructorId) {
		Scanner scanner = new Scanner(System.in);
		boolean logged = true;

		while (logged) {
			System.out.println("\n--- Program Management ---");
			System.out.println("1. Display all programs");
			System.out.println("2. Add new program");
			System.out.println("3. Edit program details, price, attachments or schedule");
			System.out.println("4. Delete program");
			System.out.println("5. Main menu");
			System.out.print("Please select an option: ");

			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1:
				displayInstructorsPrograms(instructorId);
				break;
			case 2:
				addNewProgram(instructorId);
				break;
			case 3:
				editProgram(instructorId);
				break;
			case 4:
				deleteProgram(instructorId);
				break;
			case 5:
				logged = handleLogOut();
				break;
			default:
				System.out.println("Invalid option. Please try again.");
				break;

			}

		}

	}

	private static void deleteProgram(String instructorId) {
		displayInstructorsPrograms(instructorId);
		String programID = getProgramIDToDelete();

		Program programToDelete = ProgramDataBase.getProgram(programID);
		if (programToDelete != null) {
			confirmDeletion(programToDelete);
		} else {
			System.out.println("Program with ID " + programID + " does not exist.");
		}
	}

	private static String getProgramIDToDelete() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter ID of Program to delete:");
		return scanner.nextLine();
	}

	private static void confirmDeletion(Program programToDelete) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Are you sure you want to delete the \"" + programToDelete.getTitle() + "\" program?");
		System.out.println("Enter 1 to delete or 2 to cancel");

		int choice = scanner.nextInt();
		switch (choice) {
		case 1:
			ProgramDataBase.deleteProgram(programToDelete.getProgramId());
			System.out.println("Deleting program.");
			break;
		case 2:
			System.out.println("Canceling.");
			break;
		default:
			System.out.println("Invalid option. Please try again.");
			confirmDeletion(programToDelete); // Retry on invalid input
			break;
		}
	}

	private static void displayInstructorsPrograms(String instructorId) {
		System.out.println(instructorId);
		ArrayList<Program> instructorPrograms = ProgramDataBase.getProgramsForInstructor(instructorId);
		if (instructorPrograms.isEmpty()) {
			System.out.println("No Programs Found");
		} else {
			instructorPrograms
					.forEach(program -> System.out.println(program.getProgramId() + " : " + program.getTitle()));
		}
	}

	private static void addNewProgram(String instructorId) {
		Scanner scanner = new Scanner(System.in);
		Program newProgram = gatherProgramDetails(scanner, instructorId);
		String price = gatherProgramPrice(scanner);
		newProgram.setInstructorId(instructorId);
		newProgram.setPrice(price);
		setProgramSchedule(scanner, newProgram);
		System.out.println("program added");
		ProgramDataBase.addNewProgram(newProgram);
	}

	private static Program gatherProgramDetails(Scanner scanner, String instructorId) {
		System.out.println("\n--- Create A New Program ---");
		String title = getInput("Enter program title: ");
		String durationTime = getInput("Enter program duration time: ");
		String goal = getInput("Enter program goal: ");
		String difficulty = getValidDifficultyLevel(scanner);

		ArrayList<Attachment> programAttachments = gatherProgramAttachments(scanner);
		Program newProgram = new Program(title, durationTime, difficulty, goal, instructorId);
		newProgram.setAttachments(programAttachments);
		return newProgram;
	}

	private static String getInput(String prompt) {
		Scanner scanner = new Scanner(System.in);
		System.out.println(prompt);
		return scanner.nextLine();
	}

	private static String getValidDifficultyLevel(Scanner scanner) {
		String difficulty = getInput("Enter program difficulty level (Beginners, Intermediate, Advanced): ");
		while (!isValidDifficulty(difficulty)) {
			difficulty = getInput(
					"Invalid input. Enter program difficulty level (Beginners, Intermediate, Advanced): ");
		}
		return capitalizeFirstLetter(difficulty);
	}

	private static boolean isValidDifficulty(String difficulty) {
		return difficulty.equalsIgnoreCase("Beginners") || difficulty.equalsIgnoreCase("Intermediate")
				|| difficulty.equalsIgnoreCase("Advanced");
	}

	private static String capitalizeFirstLetter(String difficulty) {
		if (difficulty.equalsIgnoreCase("beginners")) {
			return "Beginners";
		} else if (difficulty.equalsIgnoreCase("intermediate")) {
			return "Intermediate";
		} else {
			return "Advanced";
		}
	}

	private static ArrayList<Attachment> gatherProgramAttachments(Scanner scanner) {
		ArrayList<Attachment> attachments = new ArrayList<>();
		boolean notAdded = true;
		while (notAdded) {
			String attachmentType = getAttachmentType(scanner);
			String attachmentPath = getInput("Enter the path of the attachment: ");
			attachments.add(new Attachment(attachmentType, attachmentPath));

			notAdded = !shouldAddAnotherAttachment(scanner);
		}
		return attachments;
	}

	private static String getAttachmentType(Scanner scanner) {
		System.out.println("Enter attachment type (choose number: 1. Image , 2. Video , 3. Document): ");
		int choice = scanner.nextInt();
		scanner.nextLine(); // Consume the newline
		switch (choice) {
		case 1:
			return "Image";
		case 2:
			return "Video";
		case 3:
			return "Document";
		default:
			System.out.println("Invalid option. Please try again.");
			return getAttachmentType(scanner); // Retry on invalid input
		}
	}

	private static boolean shouldAddAnotherAttachment(Scanner scanner) {
		System.out.println("\n1. Add another attachment");
		System.out.println("2. Exit");
		System.out.println("Please choose: ");
		int choice = scanner.nextInt();
		scanner.nextLine(); // Consume the newline
		return choice == 2;
	}

	private static String gatherProgramPrice(Scanner scanner) {
		return getInput("Enter program price: ");
	}

	private static void setProgramSchedule(Scanner scanner, Program newProgram) {
		System.out.println("\n--- Set new schedule ---");
		String sessionType = getSessionType(scanner);
		String schedule = getInput("Enter new schedule: ");
		newProgram.setGroupSession(sessionType, schedule);
	}

	private static String getSessionType(Scanner scanner) {
		System.out.println("Enter session type (choose 1. online , 2. in-person): ");
		int choice = scanner.nextInt();
		scanner.nextLine(); // Consume the newline
		switch (choice) {
		case 1:
			return "Online";
		case 2:
			return "In-person";
		default:
			System.out.println("Invalid option. Please try again.");
			return getSessionType(scanner); // Retry on invalid input
		}
	}

	private static void editProgram(String instructorId) {
		displayInstructorsPrograms(instructorId);
		Scanner scanner = new Scanner(System.in);
		String programId = getInput("Enter program id: ");
		if (ProgramDataBase.programExist(programId, instructorId)) {
			Program editProgram = ProgramDataBase.getProgram(programId);
			editProgramDetails(scanner, editProgram);
		} else {
			System.out.println("Program does not exist.");
		}
	}

	private static void editProgramDetails(Scanner scanner, Program editProgram) {
		boolean editing = true;
		while (editing) {
			System.out.println("\n1. Edit Program Details.");
			System.out.println("2. Edit Program Attachments");
			System.out.println("3. Edit Program Price");
			System.out.println("4. Edit Program Schedule");
			System.out.println("5. Exit and save changes");
			System.out.println("Please select an option: ");

			int choice = scanner.nextInt();
			scanner.nextLine(); // Consume newline
			switch (choice) {
			case 1:
				editProgramTitleAndDetails(scanner, editProgram);
				break;
			case 2:
				editProgramAttachments(scanner, editProgram);
				break;
			case 3:
				editProgramPrice(scanner, editProgram);
				break;
			case 4:
				editProgramSchedule(scanner, editProgram);
				break;
			case 5:
				System.out.println("Program details: \n" + editProgram);
				editing = false;
				break;
			default:
				System.out.println("Invalid option. Please try again.");
			}
		}
	}

	private static void editProgramTitleAndDetails(Scanner scanner, Program editProgram) {
		System.out.println("ENTER \"*\" if you don't want to change something");
		String newTitle = getInput("Enter new program title: ");
		if (!newTitle.equals("*"))
			editProgram.setTitle(newTitle);

		String newDurationTime = getInput("Enter new program duration time: ");
		if (!newDurationTime.equals("*"))
			editProgram.setDurationTime(newDurationTime);

		String newDifficulty = getInput("Enter new program difficulty level: ");
		if (!newDifficulty.equals("*"))
			editProgram.setdifficultyLevel(newDifficulty);

		String newGoal = getInput("Enter new program goals: ");
		if (!newGoal.equals("*"))
			editProgram.setGoals(newGoal);
	}

	private static void editProgramAttachments(Scanner scanner, Program editProgram) {
		System.out.println("\n--- Edit attachments ---");
		System.out.println("1. Replace old attachments");
		System.out.println("2. Add new attachments");
		int choice = scanner.nextInt();
		scanner.nextLine(); // Consume newline
		switch (choice) {
		case 1:
			replaceAttachments(scanner, editProgram);
			break;
		case 2:
			addNewAttachments(scanner, editProgram);
			break;
		default:
			System.out.println("Invalid option.");
		}
	}

	private static void replaceAttachments(Scanner scanner, Program editProgram) {
		ArrayList<Attachment> newAttachments = gatherProgramAttachments(scanner);
		editProgram.setAttachments(newAttachments);
	}

	private static void addNewAttachments(Scanner scanner, Program editProgram) {
		ArrayList<Attachment> newAttachments = editProgram.getAttachments();
		newAttachments.addAll(gatherProgramAttachments(scanner));
		editProgram.setAttachments(newAttachments);
	}

	private static void editProgramPrice(Scanner scanner, Program editProgram) {
		String newPrice = getInput("Enter new price: ");
		ProgramDataBase.updatePrice(newPrice, editProgram.getProgramId());
	}

	private static void editProgramSchedule(Scanner scanner, Program editProgram) {
		String sessionType = getSessionType(scanner);
		String schedule = getInput("Enter new schedule: ");
		ProgramDataBase.setSchedule(sessionType, schedule, editProgram.getProgramId());
	}

	private static void InstructorClientInteraction(String instructorId) {
		Scanner scanner = new Scanner(System.in);
		boolean logged = true;
		while (logged) {
			System.out.println("\n--- Client Interactions ---");
			System.out.println("1. Send a direct message to client");
			System.out.println("2. create a discussion forum");
			System.out.println("3. Send a feedback to client");
			System.out.println("4. Send progress reports to clients");
			System.out.println("5. exit");
			System.out.println("Please select an option: ");
			int option;
			boolean invalid = true;
			while (invalid) {
				option = scanner.nextInt();
				switch (option) {
				case 1:
					sendMessage(instructorId);
					invalid = false;
					break;
				case 2:
					creatDuscussionForum(instructorId);
					invalid = false;
					break;
				case 3:
					senFeedback(instructorId);
					invalid = false;
					break;
				case 4:
					sendProgressReports(instructorId);
					invalid = false;
					break;
				case 5:
					invalid = false;
					logged = false;
					break;
				default:
					System.out.println("please enter valid option");

				}
			}
		}
	}

	private static void sendProgressReports(String instructorId) {
		Scanner scanner = new Scanner(System.in);
		String programId;

		displayInstructorsPrograms(instructorId);

		System.out.println("\nEnter program id: ");
		boolean invalid = true;
		while (invalid) {
			programId = scanner.nextLine();
			boolean programExist = ProgramDataBase.programExist(programId, instructorId);
			if (programExist) {
				InboxManagement.sendProgressRepots(programId);
				invalid = false;
			} else {
				System.out.println("enter valid id");
			}
		}
	}

	private static void senFeedback(String instructorId) {
		Scanner scanner = new Scanner(System.in);
		String client;
		String programId;
		System.out.println("\n--- Your Clients ---");
		for (User e : ProgramDataBase.getAllClientsForInstructor(instructorId)) {
			System.out.println("name: " + e.getName() + " id: " + e.getID());
		}
		System.out.print("Enter client id: ");
		client = scanner.nextLine();
		boolean noUser = !UserDataBase.userExist(client);
		while (noUser) {
			System.out.println("Please enter a valid id.");
			client = scanner.nextLine();
			noUser = !UserDataBase.userExist(client);
		}
		displayInstructorsPrograms(instructorId);
		System.out.println("\nEnter program id: ");
		boolean invalid = true;
		while (invalid) {
			programId = scanner.nextLine();
			boolean programExist = ProgramDataBase.programExist(programId, instructorId);
			if (programExist) {
				System.out.println("Enter your message: ");
				String message = scanner.nextLine();

				InboxManagement.sendFeedback(instructorId, null, message, programId);
				invalid = false;
			} else {
				System.out.println("enter valid id");
			}
		}

	}

	private static void creatDuscussionForum(String instructorId) {
		Scanner scanner = new Scanner(System.in);
		String client;
		String programId;
		String topic = "non";

		displayInstructorsPrograms(instructorId);

		System.out.println("\nEnter program id: ");
		boolean invalid = true;
		while (invalid) {
			programId = scanner.nextLine();
			boolean programExist = ProgramDataBase.programExist(programId, instructorId);
			if (programExist) {
				System.out.println("Enter discussion topic: ");
				topic = scanner.nextLine();
				System.out.println("Enter your message: ");
				String message = scanner.nextLine();
				InboxManagement.creatDicussionForum(instructorId, topic, message, programId);
				invalid = false;
			} else {
				System.out.println("enter valid id");
			}
		}

	}

	private static void sendMessage(String instructorId) {
		Scanner scanner = new Scanner(System.in);

		// Display available programs for the instructor
		ArrayList<Program> instructorPrograms = ProgramDataBase.getProgramsForInstructor(instructorId);
		if (instructorPrograms.isEmpty()) {
			System.out.println("You do not have any programs assigned.");
			return;
		}

		System.out.println("\n--- Your Programs ---");
		for (int i = 0; i < instructorPrograms.size(); i++) {
			Program program = instructorPrograms.get(i);
			System.out.println((i + 1) + ". " + program.getTitle() + " (ID: " + program.getProgramId() + ")");
		}

		// Prompt the instructor to select a program
		System.out.print("Enter the program number you want to send a message for: ");
		int programIndex = scanner.nextInt() - 1;
		scanner.nextLine(); // consume the newline character

		if (programIndex < 0 || programIndex >= instructorPrograms.size()) {
			System.out.println("Invalid selection.");
			return;
		}

		Program selectedProgram = instructorPrograms.get(programIndex);
		String programId = selectedProgram.getProgramId();

		System.out.println("\n--- Clients for Program: " + selectedProgram.getTitle() + " ---");
		for (User e : selectedProgram.getEnrolledClient()) {
			System.out.println("Name: " + e.getName() + " | ID: " + e.getID());
		}

		System.out.print("Enter client ID: ");
		String client = scanner.nextLine();

		boolean noUser = !UserDataBase.userExist(client);
		while (noUser) {
			System.out.println("Please enter a valid client ID.");
			client = scanner.nextLine();
			noUser = !UserDataBase.userExist(client);
		}

		System.out.println("Enter your message: ");
		String message = scanner.nextLine();

		InboxManagement.sendDirectMessage(instructorId, message, client, programId);
	}

//***************************************Client Menu*****************************************************
	private static void ClientMenu(String userId) {
		Scanner scanner = new Scanner(System.in);
		boolean logged = true;

		while (logged) {
			System.out.println("\n--- Client Features ---");
			System.out.println("1. Account Management");
			System.out.println("2. Program Exploration");
			System.out.println("3. Progress Tracking");
			System.out.println("4. Provide Feedback");
			System.out.println("5. Program Review");
			System.out.println("6. Check Inbox");
			System.out.println("7. Logout");
			System.out.print("Please select an option: ");

			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1:
				ClientAccountManagement(userId);
				break;

			case 2:
				ClientProgramExploration(userId);
				break;

			case 3:
				ClientProgressTracking(userId);
				break;

			case 4:
				ClientProvideFeedback(userId);
				break;

			case 5:
				ClientProgramReview(userId);
				break;

			case 6:
				UserDataBase.getUser(userId).showInbox();
				break;

			case 7:
				logged = handleLogOut();
				break;

			default:
				System.out.println("Invalid option. Please try again.");
				break;
			}
		}
	}

	private static boolean handleLogOut() {
		boolean logged;
		System.out.println("Logging out...");
		ClearConsole();
		System.out.flush();
		logged = false;
		return logged;
	}

	public static void ClearConsole() {
		for (int i = 0; i < 100; i++) {
			System.out.println();
		}
	}

	private static void ClientAccountManagement(String userId) {
		Scanner scanner = new Scanner(System.in);
		boolean managingAccount = true;

		while (managingAccount) {
			System.out.println("\n--- Client Account Management ---");
			System.out.println("1. Create Profile");
			System.out.println("2. Update Profile");
			System.out.println("3. Delete Profile");
			System.out.println("4. View Profile");
			System.out.println("5. Back to Main Menu");
			System.out.print("Please select an option: ");

			int choice = scanner.nextInt();
			scanner.nextLine(); // Consume newline

			switch (choice) {
			case 1:
				createProfile(userId, scanner);
				break;

			case 2:
				updateProfile(userId, scanner);
				break;

			case 3:
				boolean profileDeleted = ProfileDataBase.deleteProfile(userId);
				if (profileDeleted) {
					System.out.println("Profile deleted successfully.");
				} else {
					System.out.println("Failed to delete profile. Please try again.");
				}
				break;

			case 4:
				viewProfile(userId);
				break;

			case 5:
				System.out.println("Returning to the main menu...");
				managingAccount = false;
				break;

			default:
				System.out.println("Invalid option. Please try again.");
				break;
			}
		}
	}

	private static void viewProfile(String userId) {
		ClientProfile profile = UserDataBase.getUser(userId).getProfile();
		if (profile != null) {
			System.out.println("\n--- Profile Details ---");
			System.out.println("Age: " + profile.getAge());
			System.out.println("Goal: " + profile.getGoal());
			System.out.println("Dietary Preference: " + profile.getDiet());
		} else {
			System.out.println("No profile found for the user.");
		}
	}

	private static void updateProfile(String userId, Scanner scanner) {
		System.out.print("Enter your new age: ");
		String newAge = scanner.nextLine();

		System.out.print("Enter your new fitness goal (e.g., weight in kg): ");
		int newGoal = scanner.nextInt();
		scanner.nextLine(); // Consume newline

		printDietaryPreferences();
		System.out.print("\nEnter your new dietary preference (e.g., LOW_FAT, HIGH_PROTEIN): ");
		String newDiet = scanner.nextLine();

		boolean profileUpdated = ProfileDataBase.updateProfile(userId, newAge, newGoal, newDiet);
		if (profileUpdated) {
			System.out.println("Profile updated successfully.");
		} else {
			System.out.println("Failed to update profile. Please try again.");
		}
	}

	private static void createProfile(String userId, Scanner scanner) {
		System.out.print("Enter your age: ");
		String age = scanner.nextLine();

		System.out.print("Enter your fitness goal (e.g., weight in kg): ");
		int goal = scanner.nextInt();
		scanner.nextLine(); // Consume newline

		printDietaryPreferences();
		System.out.print("\nEnter your dietary preference (e.g., LOW_FAT, HIGH_PROTEIN): ");
		String diet = scanner.nextLine();

		boolean profileCreated = ProfileDataBase.addProfile(userId, age, goal, diet);
		if (profileCreated) {
			System.out.println("Profile created successfully.");
		} else {
			System.out.println("Failed to create profile. Please try again.");
		}
	}

	public static void printDietaryPreferences() {
		System.out.println("Available Dietary Preferences:");
		for (DietaryPreferences preference : DietaryPreferences.values()) {
			System.out.println(preference.name() + ": " + preference.getDescription());
		}
	}

	private static void ClientProgramExploration(String userId) {
		Scanner scanner = new Scanner(System.in);
		boolean exploring = true;

		while (exploring) {
			System.out.println("\n--- Program Exploration ---");
			System.out.println("1. Filter Programs by Difficulty");
			System.out.println("2. Filter Programs by Duration");
			System.out.println("3. Enroll in a Program");
			System.out.println("4. View Program Schedule");
			System.out.println("5. Back to Client Menu");
			System.out.print("Please select an option: ");

			int choice = scanner.nextInt();
			scanner.nextLine(); // Consume newline

			switch (choice) {
			case 1:
				filterByDifficulty(scanner);
				break;

			case 2:
				filterByDuration(scanner);
				break;

			case 3:
				clientEnroll(userId, scanner);
				break;

			case 4:
				viewClientSchedule(userId);
				break;

			case 5:
				System.out.println("Returning to Client Menu...");
				exploring = false;
				break;

			default:
				System.out.println("Invalid option. Please try again.");
				break;
			}
		}
	}

	private static void viewClientSchedule(String userId) {
		// View Program Schedule
		ArrayList<String> schedules = UserDataBase.getUser(userId).getSchedules();
		if (schedules != null) {
			System.out.println("*** Your Program Schedules ***");
			for (String schedule : schedules) {
				System.out.println(schedule);
			}
		} else {
			System.out.println("You are not enrolled in any programs.");
		}
	}

	private static void clientEnroll(String userId, Scanner scanner) {
		printPrograms();
		// Enroll in a Program
		System.out.print("Enter the Program ID to enroll in: ");
		String programId = scanner.nextLine();
		Program program = ProgramDataBase.getProgram(programId);
		if (program != null) {
			program.addClient(userId);
			System.out.println("You have successfully enrolled in " + program.getTitle());
		} else {
			System.out.println("Program ID not found.");
		}
	}

	private static void filterByDuration(Scanner scanner) {
		// Filter by Duration
		System.out.print("Enter maximum duration in hours: ");
		int duration = scanner.nextInt();
		scanner.nextLine(); // Consume newline
		ArrayList<Program> durationList = ProgramDataBase.getListDuration(duration);
		if (durationList != null) {
			System.out.println("*** Programs Filtered by Duration up to " + duration + " Hours ***");
			for (Program program : durationList) {
				System.out.println(program.getTitle() + " - Duration: " + program.getTargetedHours() + " hours");
			}
		} else {
			System.out.println("No programs found with the given duration.");
		}
	}

	public static void filterByDifficulty(Scanner scanner) {
		// Filter by Difficulty
		System.out.print("Enter difficulty level (Beginners, Intermediate, Advanced): ");
		String difficulty = scanner.nextLine();
		ArrayList<Program> difficultyList = ProgramDataBase.getListDifficulty(difficulty);
		if (difficultyList != null) {
			System.out.println("*** Programs Filtered by Difficulty: " + difficulty + " ***");
			for (Program program : difficultyList) {
				System.out.println(program.getTitle() + " - Difficulty: " + program.getdifficultyLevel());
			}
		} else {
			System.out.println("No programs found with the given difficulty.");
		}
	}

	private static void ClientProgressTracking(String userId) {
		Scanner scanner = new Scanner(System.in);
		boolean trackingProgress = true;

		while (trackingProgress) {
			System.out.println("\n--- Client Progress Tracking ---");
			System.out.println("1. Enter Weight and Height");
			System.out.println("2. View BMI and Body Status");
			System.out.println("3. View Earned Badges");
			System.out.println("4. Back to Client Menu");
			System.out.print("Please select an option: ");

			int choice = scanner.nextInt();
			scanner.nextLine(); // Consume newline

			switch (choice) {
			case 1:
				enterWeightAndHeight(userId, scanner);
				break;

			case 2:
				viewBMIandStatus(userId);
				break;

			case 3:
				viewEarnedBadges(userId);
				break;

			case 4:
				System.out.println("Returning to Client Menu...");
				trackingProgress = false;
				break;

			default:
				System.out.println("Invalid option. Please try again.");
				break;
			}
		}
	}

	private static void enterWeightAndHeight(String userId, Scanner scanner) {
		System.out.print("Enter your weight (in kg): ");
		String weight = scanner.nextLine();

		System.out.print("Enter your height (in cm): ");
		String height = scanner.nextLine();

		UserDataBase.getUser(userId).setWeight(Integer.parseInt(weight));
		UserDataBase.getUser(userId).setHeight(Integer.parseInt(height));

		System.out.println("Weight and Height updated successfully.");
	}

	private static void viewBMIandStatus(String userId) {
		double bmi = UserDataBase.getUser(userId).getBMI();
		String bodyStatus = UserDataBase.getUser(userId).getBodyStatus();
		String action = UserDataBase.getUser(userId).getAction();

		System.out.println("Your BMI: " + bmi);
		System.out.println("Body Status: " + bodyStatus);
		System.out.println("Suggested Action: " + action);
	}

	private static void viewEarnedBadges(String userId) {
		ArrayList<AchievementBadge> earnedBadges = UserDataBase.getUser(userId).getEarnedBadges();

		if (earnedBadges.isEmpty()) {
			System.out.println("No badges earned yet.");
		} else {
			System.out.println("Your Earned Badges:");
			for (AchievementBadge badge : earnedBadges) {
				System.out.println(badge.name() + ": " + badge.getDescription());
			}
		}
	}

	private static void ClientProvideFeedback(String userId) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("\n--- Provide Feedback ---");
		System.out.print("Enter your feedback: ");
		String feedback = scanner.nextLine();

		boolean feedbackCreated = ReviewsDataBase.addFeedback(userId, feedback);
		if (feedbackCreated) {
			System.out.println("Feedback successfully added.");
		} else {
			System.out.println("Failed to add feedback. Please try again.");
		}
	}

	private static void ClientProgramReview(String userId) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("\n--- Program Review ---");

		// Collecting program ID, rating, and review
		printPrograms();
		String programID = scanner.nextLine();

		System.out.print("Enter your rating (e.g., 1-5): ");
		String rating = scanner.nextLine();

		System.out.print("Enter your review: ");
		String review = scanner.nextLine();

		boolean reviewCreated = ReviewsDataBase.addReview(userId, programID, rating, review);

		if (reviewCreated) {
			System.out.println("Your review has been successfully added.");
		} else {
			System.out.println("Failed to add review. Please try again.");
		}
	}

	private static void printPrograms() {
		for (Program p : ProgramDataBase.getProgramsList())
			System.out.println(p.getProgramId() + " : " + p.getTitle());
		System.out.print("Enter the Program ID: ");
	}

}
