package FeaturesTest.steps;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.BeforeClass;
import FeaturesMain.ProgressTracking;
import FeaturesMain.InboxManagement;
import FeaturesMain.MyApplication;
import FeaturesMain.Program;
import FeaturesMain.UserDataBase;
import FeaturesMain.ProgramDataBase;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class InstructorProgressTrackingSteps {
	
	MyApplication app;
	
	
	
	public InstructorProgressTrackingSteps() {
		app = new MyApplication();
		ProgramDataBase.getProgram("100001").setSessionsCount(5);
		ProgramDataBase.getProgram("100001").addClient("client790");
		ProgramDataBase.getProgram("100001").addClient("client791");
		ProgramDataBase.getProgram("100001").addClient("client792");
		ProgramDataBase.getProgram("100001").addClient("client793");
		UserDataBase.getUser("client790").setAttendence("100001",2);
		UserDataBase.getUser("client791").setAttendence("100001",3);
		UserDataBase.getUser("client792").setAttendence("100001",4);
		UserDataBase.getUser("client793").setAttendence("100001",1);
		
	}
	
	@Given("the the instructor has a program with id {string}")
	public void the_the_instructor_has_a_program_with_id(String id) {
		boolean programExists = ProgramDataBase.programExist(id);
		assertTrue("failed: program doesnt exist", programExists);
	}

	@When("the instructor wants to see the progress of his clients enrolled in the program with id {string}")
	public void the_instructor_wants_to_see_the_progress_of_his_clients_enrolled_in_the_program_with_id(String programId) {
	    boolean displayedTest = ProgressTracking.displayClientsProgress(programId);
	    assertTrue("failed: clients progresses couldn't be displayed", displayedTest );
	}

	@Then("a list of the clients with their progress should be displayed.")
	public void a_list_of_the_clients_with_their_progress_should_be_displayed() {
	    boolean checkIfDisplayedTest = ProgressTracking.checkLastInteraction();
	    assertTrue("failed: system didn't display the progresses", checkIfDisplayedTest);
	}

	@Given("Im logged in  as an instructor with id {string} password {string}")
	public void im_logged_in_as_an_instructor_with_id_password(String id, String password) {
		boolean loggedIn = UserDataBase.login(id, password);
		assertTrue("Test failed: Login was not successful.", loggedIn);
	}

	@When("the instructor wants to send a motivational reminder {string} to all their clients enrolled in program with id {string}")
	public void the_instructor_wants_to_send_a_motivational_reminder_to_all_their_clients_enrolled_in_program_with_id(String message, String programId) {
	    boolean sendReminderTest = InboxManagement.sendReminderToAllClients(message, programId);
	    assertTrue("failed: couldn't send reminder", sendReminderTest);
	}


	@Then("the clients should recieve the message in their inbox.")
	public void the_clients_should_recieve_the_message_in_their_inbox() {
	    boolean sentTest = InboxManagement.getLastInteraction();
	    assertTrue("failed: reminder message was not sent", sentTest);
	}
	
	

	




}
