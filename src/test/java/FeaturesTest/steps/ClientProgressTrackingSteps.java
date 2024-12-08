package FeaturesTest.steps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import FeaturesMain.AchievementBadge;
import FeaturesMain.MyApplication;
import FeaturesMain.UserDataBase;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ClientProgressTrackingSteps {
	MyApplication app;
	String TestId, TestPass;

	public ClientProgressTrackingSteps() {
		app = new MyApplication();
	}

	@Given("I am logged in as a client with ID {string} and password {string}")
	public void i_am_logged_in_as_a_client_with_id_and_password(String id, String password) {
		TestId = id;
		TestPass = password;
		boolean loggedIn = UserDataBase.login(id, password);
		assertTrue("Test failed: Login was not successful.", loggedIn);
	}

	@When("I enter my weight {string} and height {string}")
	public void i_enter_my_weight_and_height(String weight, String height) {
		System.out.println("Heigh of:" + height + " and Weight of:" + weight);

		UserDataBase.getUser(TestId).setWeight(Integer.parseInt(weight));
		UserDataBase.getUser(TestId).setHeight(Integer.parseInt(height));

		assertEquals("Test failed: Weight not set correctly.", Integer.parseInt(weight),
				UserDataBase.getUser(TestId).getWeight());
		assertEquals("Test failed: Height not set correctly.", Integer.parseInt(height),
				UserDataBase.getUser(TestId).getHeight());
	}

	@Then("I should get a BMI {string}, {string} and be told to {string}")
	public void i_should_get_a_bmi_and_be_told_to(String BMI, String BodyStatus, String Action) {
		double actualBMI = UserDataBase.getUser(TestId).getBMI();
		System.out.println("Your BMI is: " + actualBMI);
		String actualStatus = UserDataBase.getUser(TestId).getBodyStatus();
		String actualAction = UserDataBase.getUser(TestId).getAction();
		System.out.println();

		assertEquals("Test failed: BMI does not match.", Double.parseDouble(BMI), actualBMI, 0.1);
		assertEquals("Test failed: Body status does not match.", BodyStatus, actualStatus);
		assertEquals("Test failed: Action does not match.", Action, actualAction);
	}

	@When("I finish my second program")
	public void i_finish_my_second_program() {
		UserDataBase.getUser("client123").incNumOfPro(); // assume only finished one program
		int programsDone = UserDataBase.getUser("client123").getNumOfPro();
		assertEquals("Test failed: 2nd program not finished.", programsDone, 2);
	}

	@Then("I should gain the {string} badge")
	public void i_should_gain_the(String badge) {
		ArrayList<AchievementBadge> actualBadges = UserDataBase.getUser("client123").getEarnedBadges();

		boolean hasBadge = actualBadges.contains(AchievementBadge.valueOf(badge));
		if (hasBadge) {

			AchievementBadge earnedBadge = AchievementBadge.valueOf(badge);
			String badgeDescription = earnedBadge.getDescription();
			System.out.println("Badge Earned: " + badgeDescription);

		}

		assertTrue("Test failed: Badge not earned.", hasBadge);

	}

	@When("i try to see my badges")
	public void i_try_to_see_my_badges() {
		ArrayList<AchievementBadge> earnedBadges = UserDataBase.getUser("client123").getEarnedBadges();
		assertTrue("Test failed: No badges have been earned.", !earnedBadges.isEmpty());

	}

	@Then("my badges should be showcased")
	public void my_badges_should_be_showcased() {
		UserDataBase.getUser("client123").showBadges();
	}

}
