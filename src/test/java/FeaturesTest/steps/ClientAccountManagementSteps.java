package FeaturesTest.steps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import FeaturesMain.ClientProfile;
import FeaturesMain.DietaryPreferences;
import FeaturesMain.MyApplication;
import FeaturesMain.ProfileDataBase;
import FeaturesMain.UserDataBase;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ClientAccountManagementSteps {
	MyApplication app;

	public ClientAccountManagementSteps() {
		app = new MyApplication();
	}

	@Given("Im logged in as a client id {string} password {string}")
	public void im_logged_in_as_a_client_id_password(String id, String password) {
		boolean loggedIn = UserDataBase.login(id, password);
		assertTrue("Test failed: Login was not successful.", loggedIn);
	}

	@When("I create a profile with age {string} goal {int} diet {string}")
	public void i_create_a_profile_with_age_goal_diet(String age, int goal, String diet) {
		boolean profileCreated = ProfileDataBase.addProfile("client123", age, goal, diet);
		assertTrue("Test Failed: profile not created", profileCreated);

	}

	@Then("a profile for that user should be created")
	public void a_profile_for_that_user_should_be_created() {
		ClientProfile existingProfile = UserDataBase.getUser("client123").getProfile();
		assertTrue("Test failed: Profile is not assigned to the user.", existingProfile != null);

		// Validate that the profile has been correctly set
		assertEquals("Test failed: Profile age does not match.",
				UserDataBase.getUser("client123").getProfile().getAge(), existingProfile.getAge());
		assertEquals("Test failed: Profile goal does not match.",
				UserDataBase.getUser("client123").getProfile().getGoal(), existingProfile.getGoal());
		assertEquals("Test failed: Profile diet does not match.",
				UserDataBase.getUser("client123").getProfile().getDiet(), existingProfile.getDiet());
	}

	@When("I update my profile with age {string} goal {int} diet {string}")
	public void i_update_my_profile_with_age_goal_diet(String age, int goal, String diet) {
		// Call the updateProfile method
		boolean profileUpdated = ProfileDataBase.updateProfile("client123", age, goal, diet);

		// Assert that the profile update was successful
		assertTrue("Test Failed: Profile not updated", profileUpdated);
	}

	@Then("a profile for that user should be updated")
	public void a_profile_for_that_user_should_be_updated() {
		// Retrieve the updated profile
		ClientProfile updatedProfile = UserDataBase.getUser("client123").getProfile();

		// Assert that the profile exists
		assertTrue("Test failed: Profile is not assigned to the user.", updatedProfile != null);

		// Validate that the profile has been correctly updated
		assertEquals("Test failed: Profile age does not match.", "21", updatedProfile.getAge());
		assertEquals("Test failed: Profile goal does not match.", 80, updatedProfile.getGoal());
		assertEquals("Test failed: Profile diet does not match.", DietaryPreferences.LOW_FAT, updatedProfile.getDiet());
	}

	@When("I delete the profile")
	public void i_delete_the_profile() {
		boolean profileDeleted = ProfileDataBase.deleteProfile("client123");
		assertTrue("Test failed: Profile not deleted.", profileDeleted);
	}

	@Then("the profile should be deleted")
	public void the_profile_should_be_deleted() {
		ClientProfile deletedProfile = UserDataBase.getUser("client123").getProfile();
		assertTrue("Test failed: Profile was not deleted.", deletedProfile == null);
	}
}
