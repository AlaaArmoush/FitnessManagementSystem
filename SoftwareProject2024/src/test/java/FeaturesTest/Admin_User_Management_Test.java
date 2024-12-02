package FeaturesTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import FeaturesMain.User;
import FeaturesMain.UserDataBase;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Admin_User_Management_Test {

	@Given("I am logged in as an admin")
	public void i_am_logged_in_as_an_admin() {
		String id = "admin123";
		String password = "1001";
		boolean loggedIn = UserDataBase.login(id, password);
		assertTrue("Test failed: Login was not successful.", loggedIn);
	}

	@When("I add a new user account with valid details")
	public void i_add_a_new_user_account_with_valid_details() {
		User testUser = new User("Alaa Armoush", "2342004", "Instructor", "Activated");
		boolean userExists = UserDataBase.addUser(testUser);
		assert userExists : "Test failed: User was not added successfully.";
		System.out.println("User added successfully: " + testUser);
	}

	@Then("the user account should be created successfully")
	public void the_user_account_should_be_created_successfully() {
		String TestID = "2342004";
		boolean userExists = UserDataBase.userExist(TestID);
		assert userExists : "Test failed: User was not added successfully.";
		System.out.println("User found successfully: " + UserDataBase.getUser(TestID));
	}

	@Given("a user account exists")
	public void a_user_account_exists() {
		// user with wrong info that need updating
		User testUser_2 = new User("Mohe lwa", "7654321", "Instructor", "Activated");
		boolean userAdded = UserDataBase.addUser(testUser_2);
		assertTrue("Test failed: User account not added.", userAdded);
		System.out.println("exists but need updating(name and role are wrong): " + testUser_2);
	}

	@When("I update the users account details")
	public void i_update_the_users_account_details() {
		// the user already exist so the addUser method updates
		boolean updated = UserDataBase.addUser("Mohie Halawa", "7654321", "Client");
		assertTrue("Test failed: User account not added.", updated);
		System.out.println("info updated successfully:" + UserDataBase.getUser("7654321"));

	}

	@Then("the account information should be updated successfully")
	public void the_account_information_should_be_updated_successfully() {
		// Retrieve the updated user
		User updatedUser = UserDataBase.getUser("7654321");

		// Check if the user's name and role have been updated correctly
		assertNotNull("Test failed: User not found after update.", updatedUser);
		assertEquals("Test failed: Name not updated correctly.", "Mohie Halawa", updatedUser.getName());
		assertEquals("Test failed: Role not updated correctly.", "Client", updatedUser.getRole());

		// Print the updated user information
		System.out.println("User account successfully updated: " + updatedUser);
	}

	@When("I deactivate the users account")
	public void i_deactivate_the_users_account() {
		boolean deactivation = UserDataBase.deactivate("2342004");
		assertTrue("Test failed: User account not added.", deactivation);
		System.out.println("status updated successfully");

	}

	@Then("the account status should be updated to {string}")
	public void the_account_status_should_be_updated_to(String string) {
		String expected = "Deactivated";
		assertEquals("Test failed: Name not updated correctly.", expected, UserDataBase.getUser("2342004").getStatus());
		System.out.println("User account successfully deactivated: " + UserDataBase.getUser("2342004"));

	}

	@Given("there are pending instructor registration requests")
	public void there_are_pending_instructor_registration_requests() {
		boolean pending = UserDataBase.addPendingInstructor("1234567", "Haya Samaana");
		assertTrue("Test Failed: no pending request", pending);
		System.out.println("Instructor registration request added to pending.");
	}

	@When("I approve a request")
	public void i_approve_a_request() {
		boolean approved = UserDataBase.approveInstructorRequest("1234567");
		assertTrue("Test failed: Instructor request not approved.", approved);
		System.out.println("Instructor request approved and account activated.");
	}

	@Then("the instructor account should be activated")
	public void the_instructor_account_should_be_activated() {
		String status = UserDataBase.getUser("1234567").getStatus();
		assertTrue("Test failed: Instructor account not activated.", (status == "Activated"));
		System.out.println("Instructor account status: " + UserDataBase.getUser("1234567").getStatus());
	}

	@When("I view user activity and engagement statistics")
	public void i_view_user_activity_and_engagement_statistics() {
		// Fetch statistics from the system
		int activeClients = UserDataBase.getActiveUserCount("Client");
		int activeInstructors = UserDataBase.getActiveUserCount("Instructor");

		System.out.println("Active Clients: " + activeClients);
		System.out.println("Active Instructors: " + activeInstructors);
	}

	@Then("I should see detailed metrics for both clients and instructors")
	public void i_should_see_detailed_metrics_for_both_clients_and_instructors() {
		// Fetch specific statistics for clients and instructors
		int activeClients = UserDataBase.getActiveUserCount("Client");
		int activeInstructors = UserDataBase.getActiveUserCount("Instructor");

		// Assertions to ensure the metrics are being tracked correctly
		assertTrue("Test failed: Active clients not displayed correctly", activeClients >= 0);
		assertTrue("Test failed: Active instructors not displayed correctly", activeInstructors >= 0);

		// Display the statistics
		System.out.println("Active Clients: " + activeClients);
		System.out.println("Active Instructors: " + activeInstructors);
	}

}
