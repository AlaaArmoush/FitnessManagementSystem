package FeaturesTest.steps;

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

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

public class InstructorClientInterationSteps {
	
MyApplication app;
	
	
	
	public InstructorClientInterationSteps() {
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
	
	@When("the instructor with id {string} wants to send a message {string} to client with id {string} enrolled in program with id {string}")
	public void the_instructor_with_id_wants_to_send_a_message_to_client_with_id_enrolled_in_program_with_id(String senderId, String message, String clientId, String programId) {
	    boolean sendTest = InboxManagement.sendDirectMessage(senderId, message, clientId, programId);
	    assertTrue("failed: message was not sent", sendTest);
	}
	

	@Then("the client with id should receive the message in their inbox")
	public void the_client_with_id_should_receive_the_message_in_their_inbox() {
		boolean sentTest = InboxManagement.getLastInteraction();
	    assertTrue("failed: reminder message was not sent", sentTest);
	}

	@When("the instructor with id {string} wants to create a new topic {string} with a message {string} for the program with id {string}")
	public void the_instructor_with_id_wants_to_create_a_new_topic_with_a_message_for_the_program_with_id(String instructorId, String topicName, String message, String programId) {
	    boolean forumTest = InboxManagement.creatDicussionForum(instructorId, topicName, message, programId);
	    assertTrue("failed: discussion forum was not created", forumTest);
	}

	@Then("the topic should be visible to all enrolled clients.")
	public void the_topic_should_be_visible_to_all_enrolled_clients() {
		boolean sentTest = InboxManagement.getLastInteraction();
	    assertTrue("failed: reminder message was not sent", sentTest);
	}

	@When("the instructor with id {string} wants to send a feedback {string} to client with id {string}  enrolled in program with id {string}")
	public void the_instructor_with_id_wants_to_send_a_feedback_to_client_with_id_enrolled_in_program_with_id(String instructorId, String topicName, String message, String programId) {
	    boolean feedbackTest = InboxManagement.sendFeedback(instructorId, topicName, message, programId);
	    assertTrue("failed: feedback was not sent", feedbackTest);
	}

	@Then("the client with id should receive the feedback in their inbox")
	public void the_client_with_id_should_receive_the_feedback_in_their_inbox() {
		boolean sentTest = InboxManagement.getLastInteraction();
	    assertTrue("failed: feedback was not sent", sentTest);
	}
	
	@When("the instructor with id {string} wants to send a progress report to all clients enrolled in program with id {string} enrolled in program with id {string}")
	public void the_instructor_with_id_wants_to_send_a_progress_report_to_all_clients_enrolled_in_program_with_id_enrolled_in_program_with_id(String string, String string2, String programId) {
	    boolean reportTest = InboxManagement.sendProgressRepots(programId);
	    assertTrue("failed: report was not sent", reportTest);
	}


	@Then("the client with id should receive the report in their inbox")
	public void the_client_with_id_should_receive_the_report_in_their_inbox() {
		boolean sentTest = InboxManagement.getLastInteraction();
	    assertTrue("failed: report was not sent", sentTest);
	}


}
