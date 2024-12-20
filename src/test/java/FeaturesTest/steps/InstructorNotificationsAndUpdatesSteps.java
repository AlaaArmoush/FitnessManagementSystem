package FeaturesTest.steps;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import FeaturesMain.Attachment;
import FeaturesMain.InboxManagement;
import FeaturesMain.MyApplication;
import FeaturesMain.UserDataBase;
import FeaturesMain.ProgramDataBase;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class InstructorNotificationsAndUpdatesSteps {

	MyApplication app;

	static ArrayList<Attachment> attachmentsTest;
	static Attachment attachmentTest;

	public InstructorNotificationsAndUpdatesSteps() {
		app = new MyApplication();
		attachmentsTest = new ArrayList<Attachment>();
		attachmentTest = new Attachment("document", "this is a document");
		attachmentsTest.add(attachmentTest);
		attachmentTest = new Attachment("image", "this is an image");
		attachmentsTest.add(attachmentTest);
		ProgramDataBase.getProgram("100001").setSessionsCount(5);
		ProgramDataBase.getProgram("100001").addClient("client790");
		ProgramDataBase.getProgram("100001").addClient("client791");
		ProgramDataBase.getProgram("100001").addClient("client792");
		ProgramDataBase.getProgram("100001").addClient("client793");
		UserDataBase.getUser("client790").setAttendence("100001", 2);
		UserDataBase.getUser("client791").setAttendence("100001", 3);
		UserDataBase.getUser("client792").setAttendence("100001", 4);
		UserDataBase.getUser("client793").setAttendence("100001", 1);

	}

	@Given("the instructor has a program with id {string} with enrolled clients")
	public void the_instructor_has_a_program_with_id_with_enrolled_clients(String id) {
		boolean programExists = ProgramDataBase.programExist(id);
		assertTrue("failed: program doesnt exist", programExists);
	}

	@When("the instructor has updated the schedule of the program with id {string}")
	public void the_instructor_has_updated_the_schedule_of_the_program_with_id(String id) {
		boolean update = ProgramDataBase.setSchedule("online", "new schedule", id);
		assertTrue("failed: schedule was not updated", update);
	}

	@Then("all the clients enrolled in the program with id {string} should be notified with the new schedule in their inbox.")
	public void all_the_clients_enrolled_in_the_program_with_id_should_be_notified_with_the_new_schedule_in_their_inbox(
			String id) {
		boolean notify = InboxManagement.SendNotificationAboutNewSchedule(id, "new schedule");
		assertTrue("failed: notification was not sent", notify);
	}

	@When("the instructor creates a new program")
	public void the_instructor_creates_a_new_program() {
		boolean programCreated = ProgramDataBase.addNewProgram("flexibilty", "20 hours", "Beginners", "goal",
				"instructor11", "100004", attachmentsTest, "100");
		assertTrue("fail: program was not created", programCreated);
	}

	@Then("the clients should be notified about the new program in their inbox.")
	public void the_clients_should_be_notified_about_the_new_program_in_their_inbox() {
		boolean announce = InboxManagement.announceNewProgram("100002");
		assertTrue("failed: clients were not announced", announce);
	}

	@When("the instructor has updated the price of the program with id {string}")
	public void the_instructor_has_updated_the_price_of_the_program_with_id(String id) {
		boolean update = ProgramDataBase.updatePrice("100", id);
		assertTrue("failed: price was not updated", update);
	}

	@Then("all the clients should be notified with the new offer in their inbox.")
	public void all_the_clients_should_be_notified_with_the_new_offer_in_their_inbox() {
		boolean announce = InboxManagement.announceSpecialOffer("100002");
		assertTrue("failed: offer was not announced", announce);
	}

}
