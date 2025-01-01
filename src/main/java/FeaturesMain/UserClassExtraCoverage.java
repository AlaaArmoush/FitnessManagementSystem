package FeaturesMain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;

class UserClassExtraCoverage {

	static User user;
	static Program program;

	@BeforeEach
	void setup() {
		user = new User("Test User", "T001", "client", "active", "password123", 0);
		// Using an existing program instead of creating a new one
		program = ProgramDataBase.getProgram("100000"); // Using testProgram1 (ID "100000")
	}

	@AfterAll
	static void teardown() {
		user = null;
		program = null;
	}

	@Test
	void testSetID() {
		user.setID("T002");
		assertEquals("T002", user.getID());
	}

	@Test
	void testGetBodyStatus() {
		user.setHeight(170);
		user.setWeight(90);
		assertEquals("Overweight", user.getBodyStatus());

		user.setWeight(50);
		assertEquals("Underweight", user.getBodyStatus());

		user.setWeight(70);
		assertEquals("Healthy", user.getBodyStatus());
	}

	@Test
	void testShowInbox() {
		InboxItem item1 = new InboxItem(InboxItemType.DirectMessage, "Instructor", "Hello, join the class!", program);
		InboxItem item2 = new InboxItem(InboxItemType.UpdatesNotification, "System", "New schedule available", program);
		user.addItemToInbox(item1);
		user.addItemToInbox(item2);

		assertFalse(user.getInbox().isEmpty());

		user.showInbox();
	}

	@Test
	void testEarnedBadges() {
		assertTrue(user.getEarnedBadges().isEmpty());

		user.incNumOfPro();
		ArrayList<AchievementBadge> badges = user.getEarnedBadges();
		assertTrue(badges.contains(AchievementBadge.NEWBIE_CHAMP));

		user.incNumOfPro();
		badges = user.getEarnedBadges();
		assertTrue(badges.contains(AchievementBadge.RISING_STAR));

		user.showBadges();
	}
}
