package FeaturesMain;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class main {
	public static void main(String[] args) {
		String id, pass;
		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome To Our Fitness Management System!");

		boolean isLoggedOut = false;
		do {
			System.out.print("Please Enter Your ID: ");
			id = scanner.nextLine();
			if (!UserDataBase.userExist(id)) {
				System.out.println("Please Enter A Valid ID!!!");
			} else {
				System.out.print("Please Enter Your Password: ");
				pass = scanner.nextLine();
				if (!UserDataBase.getUser(id).getPassword().equals(pass)) {
					System.out.println("Wrong Credentials !!!");
				} else {
					String userId = id; // Capture the logged-in user ID
					switch (UserDataBase.getUser(id).getRole()) {
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
			}

			if (isLoggedOut) {
				break;
			}

		} while (true);
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
				System.out.println("Logging out...");
				logged = false;
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
			System.out.print("Enter user name: ");
			String name = scanner.nextLine();
			System.out.print("Enter user ID: ");
			String id = scanner.nextLine();
			System.out.print("Enter user role: ");
			String role = scanner.nextLine();
			System.out.print("Enter user status: ");
			String status = scanner.nextLine();

			User newUser = new User(name, id, role, status);
			boolean userAdded = UserDataBase.addUser(newUser);
			if (userAdded) {
				System.out.println("User account created successfully.");
			} else {
				System.out.println("Failed to create user account.");
			}
			break;
		case 2:
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
			break;
		case 3:
			System.out.print("Enter user ID to deactivate: ");
			String deactivateId = scanner.nextLine();
			boolean deactivated = UserDataBase.deactivate(deactivateId);
			if (deactivated) {
				System.out.println("User account deactivated successfully.");
			} else {
				System.out.println("Failed to deactivate user account.");
			}
			break;
		case 4:
			System.out.println("Approving new instructor registrations...");
			// Implement approval functionality
			break;
		case 5:
			System.out.println("Monitoring user activity statistics...");
			int activeClients = UserDataBase.getActiveUserCount("Client");
			int activeInstructors = UserDataBase.getActiveUserCount("Instructor");
			System.out.println("Active Clients: " + activeClients);
			System.out.println("Active Instructors: " + activeInstructors);
			break;
		case 6:
			System.out.println("Returning to Admin Menu...");
			return; // Exit back to Admin Menu
		default:
			System.out.println("Invalid option.");
			break;
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
			System.out.println("1. Approve a pending article.");
			System.out.println("2. Reject a pending article.");
			System.out.println("3. View approved articles.");
			System.out.println("4. View client feedback.");
			System.out.println("5. View client program reviews.");
			System.out.println("6. Back to Admin Menu");
			System.out.print("Please select an option: ");

			int choice = scanner.nextInt();
			scanner.nextLine(); // Consume newline character

			switch (choice) {
			case 1:
				approvePendingArticle();
				break;
			case 2:
				rejectPendingArticle();
				break;
			case 3:
				viewApprovedArticles();
				break;
			case 4:
				viewClientFeedback();
				break;
			case 5:
				viewClientProgramReviews();
				break;
			case 6:
				System.out.println("Returning to Admin Menu...");
				logged = false;
				break;
			default:
				System.out.println("Invalid option. Please try again.");
				break;
			}
		}
	}

	private static void approvePendingArticle() {
		Scanner scanner = new Scanner(System.in);

		// Display the list of pending articles
		ArrayList<Article> pendingArticles = ArticlesDataBase.getPendingArticles();
		if (pendingArticles != null && !pendingArticles.isEmpty()) {
			System.out.println("\n--- Pending Articles ---");
			for (Article article : pendingArticles) {
				System.out.println("Article ID: " + article.getID() + " | Title: " + article.getTitle());
			}

			System.out.print("Enter article ID to approve: ");
			String id = scanner.nextLine();

			if (ArticlesDataBase.approveArticle(id)) {
				System.out.println("Article approved successfully.");
			} else {
				System.out.println("Failed to approve the article.");
			}
		} else {
			System.out.println("No pending articles available.");
		}
	}

	private static void rejectPendingArticle() {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter article ID to reject: ");
		String id = scanner.nextLine();

		if (ArticlesDataBase.rejectArticle(id)) {
			System.out.println("Article rejected successfully.");
		} else {
			System.out.println("Failed to reject the article.");
		}
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
				System.out.println("Logging out...");
				logged = false;
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
		// TODO Auto-generated method stub

	}

	private static void InstructorProgressTracking(String instructorId) {
		// TODO Auto-generated method stub

	}

	private static void InstructorProgramManagement(String instructorId) {
		// TODO Auto-generated method stub

	}

	private static void InstructorClientInteraction(String instructorId) {
		// TODO Auto-generated method stub

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
			System.out.println("6. Logout");
			System.out.print("Please select an option: ");

			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1:
				ClientAccountManagement(userId);
				break;

			case 2:
				ClientProgramExploration();
				break;

			case 3:
				ClientProgressTracking();
				break;

			case 4:
				ClientProvideFeedback();
				break;

			case 5:
				ClientProgramReview();
				break;

			case 6:
				System.out.println("Logging out...");
				logged = false;
				break;

			default:
				System.out.println("Invalid option. Please try again.");
				break;
			}
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
				System.out.print("Enter your age: ");
				String age = scanner.nextLine();

				System.out.print("Enter your fitness goal (e.g., weight in kg): ");
				int goal = scanner.nextInt();
				scanner.nextLine(); // Consume newline

				System.out.print("Enter your dietary preference (e.g., LOW_FAT, HIGH_PROTEIN): ");
				String diet = scanner.nextLine();

				boolean profileCreated = ProfileDataBase.addProfile(userId, age, goal, diet);
				if (profileCreated) {
					System.out.println("Profile created successfully.");
				} else {
					System.out.println("Failed to create profile. Please try again.");
				}
				break;

			case 2:
				System.out.print("Enter your new age: ");
				String newAge = scanner.nextLine();

				System.out.print("Enter your new fitness goal (e.g., weight in kg): ");
				int newGoal = scanner.nextInt();
				scanner.nextLine(); // Consume newline

				System.out.print("Enter your new dietary preference (e.g., LOW_FAT, HIGH_PROTEIN): ");
				String newDiet = scanner.nextLine();

				boolean profileUpdated = ProfileDataBase.updateProfile(userId, newAge, newGoal, newDiet);
				if (profileUpdated) {
					System.out.println("Profile updated successfully.");
				} else {
					System.out.println("Failed to update profile. Please try again.");
				}
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
				ClientProfile profile = UserDataBase.getUser(userId).getProfile();
				if (profile != null) {
					System.out.println("\n--- Profile Details ---");
					System.out.println("Age: " + profile.getAge());
					System.out.println("Goal: " + profile.getGoal());
					System.out.println("Dietary Preference: " + profile.getDiet());
				} else {
					System.out.println("No profile found for the user.");
				}
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

	// Placeholder implementations for other client features
	private static void ClientProgramExploration() {
		System.out.println("\n--- Client Program Exploration ---");
	}

	private static void ClientProgressTracking() {
		System.out.println("\n--- Client Progress Tracking ---");
	}

	private static void ClientProvideFeedback() {
		System.out.println("\n--- Provide Feedback ---");
	}

	private static void ClientProgramReview() {
		System.out.println("\n--- Program Review ---");
	}

}
