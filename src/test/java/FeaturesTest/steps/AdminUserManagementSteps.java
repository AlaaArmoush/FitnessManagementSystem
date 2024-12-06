package FeaturesTest.steps;

import static org.junit.Assert.*;
import FeaturesMain.MyApplication;
import FeaturesMain.User;
import FeaturesMain.UserDataBase;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AdminUserManagementSteps {
	MyApplication app;

	public AdminUserManagementSteps() {
		app = new MyApplication();
	}

	@Given("I am logged in as an admin id {string} password {string}")
	public void i_am_logged_in_as_an_admin_id_password(String id, String password) {
		boolean loggedIn = UserDataBase.login(id, password);
		assertTrue("Test failed: Login was not successful.", loggedIn);
	}

	@When("I add a new user account with valid details")
	public void i_add_a_new_user_account_with_valid_details() {
		User testUser = new User("Alaa Armoush", "2342004", "Instructor", "Activated");
		boolean userExists = UserDataBase.addUser(testUser);
		assertTrue("Test failed: User was not added successfully.", userExists);
	}

	@Then("the user account should be created successfully")
	public void the_user_account_should_be_created_successfully() {
		String testID = "2342004";
		boolean userExists = UserDataBase.userExist(testID);
		assertTrue("Test failed: User was not added successfully.", userExists);
	}

	@Given("a user account exists")
	public void a_user_account_exists() {
		User testUser_2 = new User("Mohe lwa", "7654321", "Instructor", "Activated");
		boolean userAdded = UserDataBase.addUser(testUser_2);
		assertTrue("Test failed: User account not added.", userAdded);
	}

	@When("I update the users account details")
	public void i_update_the_users_account_details() {
		// Creating a new user object with updated details
		User updatedUser = new User("Mohie Halawa", "7654321", "Client", "Activated");
		boolean updated = UserDataBase.updateUserDetails(updatedUser); // Call the update method
		assertTrue("Test failed: User account not updated.", updated);
	}

	@Then("the account information should be updated successfully")
	public void the_account_information_should_be_updated_successfully() {
		User updatedUser = UserDataBase.getUser("7654321");
		assertNotNull("Test failed: User not found after update.", updatedUser);
		assertEquals("Test failed: Name not updated correctly.", "Mohie Halawa", updatedUser.getName());
		assertEquals("Test failed: Role not updated correctly.", "Client", updatedUser.getRole());
	}

	@When("I deactivate the users account")
	public void i_deactivate_the_users_account() {
		boolean deactivation = UserDataBase.deactivate("2342004");
		assertTrue("Test failed: User account not deactivated.", deactivation);
	}

	@Then("the account status should be updated to {string}")
	public void the_account_status_should_be_updated_to(String expectedStatus) {
		String actualStatus = UserDataBase.getUser("2342004").getStatus();
		assertEquals("Test failed: Status not updated correctly.", expectedStatus, actualStatus);
	}

	@Given("there are pending instructor registration requests")
	public void there_are_pending_instructor_registration_requests() {
		boolean pending = UserDataBase.addPendingInstructor("1234567", "Haya Samaana");
		assertTrue("Test Failed: No pending request", pending);
	}

	@When("I approve a request")
	public void i_approve_a_request() {
		boolean approved = UserDataBase.approveInstructorRequest("1234567");
		assertTrue("Test failed: Instructor request not approved.", approved);
	}

	@Then("the instructor account should be {string}")
	public void the_instructor_account_should_be_activated(String expectedStatus) {
		String actualStatus = UserDataBase.getUser("1234567").getStatus();
		assertEquals("Test failed: Status not updated correctly.", expectedStatus, actualStatus);
	}

	@When("I view user activity and engagement statistics")
	public void i_view_user_activity_and_engagement_statistics() {
		int activeClients = UserDataBase.getActiveUserCount("Client");
		int activeInstructors = UserDataBase.getActiveUserCount("Instructor");
		System.out.println("Active Clients: " + activeClients);
		System.out.println("Active Instructors: " + activeInstructors);
	}

	@Then("I should see detailed metrics for both clients and instructors")
	public void i_should_see_detailed_metrics_for_both_clients_and_instructors() {
		int activeClients = UserDataBase.getActiveUserCount("Client");
		int activeInstructors = UserDataBase.getActiveUserCount("Instructor");
		assertTrue("Test failed: Active clients not displayed correctly.", activeClients >= 0);
		assertTrue("Test failed: Active instructors not displayed correctly.", activeInstructors >= 0);
	}
}
