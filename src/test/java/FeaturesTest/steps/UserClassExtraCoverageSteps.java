package FeaturesTest.steps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import FeaturesMain.AchievementBadge;
import FeaturesMain.InboxItem;
import FeaturesMain.InboxItemType;
import FeaturesMain.Program;
import FeaturesMain.ProgramDataBase;
import FeaturesMain.User;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UserClassExtraCoverageSteps {

	static User user;
	static Program program;

	@Given("a user is created with ID {string}")
	public void givenAUserIsCreatedWithID(String id) {
		user = new User("Test User", id, "client", "active", "password123", 0);
		program = ProgramDataBase.getProgram("100000"); // Using testProgram1
	}

	@When("the user sets their ID to {string}")
	public void whenTheUserSetsTheirIDTo(String newId) {
		user.setID(newId);
	}

	@Then("the user ID should be {string}")
	public void thenTheUserIDShouldBe(String expectedId) {
		assertEquals(expectedId, user.getID());
	}

	@Given("a user has a height of {int} and weight of {int}")
	public void givenAUserHasHeightAndWeight(int height, int weight) {
		user.setHeight(height);
		user.setWeight(weight);
	}

	@Then("the user body status should be {string}")
	public void thenTheUserBodyStatusShouldBe(String expectedStatus) {
		assertEquals(expectedStatus, user.getBodyStatus());
	}

	@Given("a user has inbox items")
	public void givenAUserHasInboxItems() {
		InboxItem item1 = new InboxItem(InboxItemType.DirectMessage, "Instructor", "Hello, join the class!", program);
		InboxItem item2 = new InboxItem(InboxItemType.UpdatesNotification, "System", "New schedule available", program);
		user.addItemToInbox(item1);
		user.addItemToInbox(item2);
	}

	@When("the user shows their inbox")
	public void whenTheUserShowsTheirInbox() {
		user.showInbox();
	}

	@Then("the inbox should not be empty")
	public void thenTheInboxShouldNotBeEmpty() {
		assertFalse(user.getInbox().isEmpty());
	}

	@Given("a user has earned no badges")
	public void givenAUserHasEarnedNoBadges() {
		assertTrue(user.getEarnedBadges().isEmpty());
	}

	@When("the user increments their pro count")
	public void whenTheUserIncrementsTheirProCount() {
		user.incNumOfPro();
	}

	@Then("the user should have earned the {string} badge")
	public void thenTheUserShouldHaveEarnedTheBadge(String badge) {
		ArrayList<AchievementBadge> badges = user.getEarnedBadges();
		assertTrue(badges.contains(AchievementBadge.valueOf(badge)));
	}

	@When("the user increments their pro count again")
	public void the_user_increments_their_pro_count_again() {
		user.incNumOfPro();
	}

	@Then("the user should have earned the next badge")
	public void the_user_should_have_earned_the_next_badge() {
		ArrayList<AchievementBadge> badges = user.getEarnedBadges();
		assertTrue(badges.contains(AchievementBadge.RISING_STAR));
	}

}