package FeaturesTest.steps;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;

import FeaturesMain.MyApplication;
import FeaturesMain.User;
import FeaturesMain.UserDataBase;
import FeaturesMain.ProgramDataBase;
import FeaturesMain.Program;
import FeaturesMain.Attachment;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class InstructorProgramManagementSteps {
	
	
	MyApplication app;
	
	static ArrayList <Attachment> attachmentsTest;
	static Attachment attachmentTest;
	
	public InstructorProgramManagementSteps(){
		app = new MyApplication();
		attachmentsTest = new ArrayList <Attachment> ();
		attachmentTest = new Attachment("document", "this is a document");
		attachmentsTest.add(attachmentTest);
		attachmentTest = new Attachment("image", "this is an image");
		attachmentsTest.add(attachmentTest);
	}
	
	@BeforeClass
	public static void setUp() {
		
		
		
	}
	
	
	@Given("Im logged in as an instructor with id {string} password {string}")
	public void im_logged_in_as_an_instructor_with_id_password(String id, String password) {
		boolean loggedIn = UserDataBase.login(id, password);
		assertTrue("Test failed: Login was not successful.", loggedIn);
	}
	
	@When("the instructor wants to create new program with title as {string} the duration time as {string} the difficulty level as {string} the goals as {string}")
	public void the_instructor_wants_to_create_new_program_with_title_as_the_duration_time_as_the_difficulty_level_as_the_goals_as(String title, String durationTime, String difficultyLevel, String goals) {
		String instructorId = "instructor11";
		String programId = ProgramDataBase.generateProgramId();
		
	    boolean programExists = ProgramDataBase.addNewProgram(title, durationTime, difficultyLevel, goals, instructorId, programId, attachmentsTest, "100");
	    assertTrue("failed: the program was not created", programExists);
	    
	}

	@When("uploads video titorials, images, or documents about the program")
	public void uploads_video_titorials_images_or_documents_about_the_program() {
		String instructorId = "instructor12";
		String programId = ProgramDataBase.generateProgramId();
	    boolean programExists = ProgramDataBase.addNewProgram("flexibility workout", "three mounths", "beginners", "get more flexy", instructorId, programId, attachmentsTest, "100");
	    assertTrue("failed: the program was not created", programExists);
	}

	@When("sets the price as {string}")
	public void sets_the_price_as(String price) {
		String instructorId = "instructor11";
		String programId = ProgramDataBase.generateProgramId();
	    boolean programExists = ProgramDataBase.addNewProgram("flexibility workout", "three mounths", "beginners", "get more flexy", instructorId, programId, attachmentsTest, price);
	    assertTrue("failed: the program was not created", programExists);
	}

	@Then("the program chould be created and visible in the intructors program list")
	public void the_program_chould_be_created_and_visible_in_the_intructors_program_list() {
		String testId = "100001";
	    boolean programDoesntExits = ProgramDataBase.programExist(testId);
	    assertTrue("failed: the program was not created", programDoesntExits);
	}

	@Given("the instructor has a program with id {string}")
	public void the_instructor_has_a_program_with_id(String id) {
		boolean programExists = ProgramDataBase.programExist(id);
		assertTrue("failed: program doesnt exist", programExists);
	}
	



	@When("the instructor wants to update the program details with title to {string} the duration to {string} the difficulty level to {string} the goals to {string} to program with id {string}")
	public void the_instructor_wants_to_update_the_program_details_with_title_to_the_duration_to_the_difficulty_level_to_the_goals_to_to_program_with_id(String title, String durationTime, String difficultyLevel, String goals, String id) {
		boolean updated = ProgramDataBase.updateProgramDetails(title, durationTime, difficultyLevel, goals, id.toString());
	    assertTrue("failed: details couldn't be updated", updated);
	}

	@Then("the updated details should be visible in the program list.")
	public void the_updated_details_should_be_visible_in_the_program_list() {
		boolean updated = ProgramDataBase.getLastUpdate();
		assertTrue("failed: couldn't be updated (not visible)", updated);
	}
	
	@When("the instructor wants to update the attachments of the program with id {string}")
	public void the_instructor_wants_to_update_the_attachments_of_the_program_with_id(String programID) {
		System.out.println(attachmentsTest.get(0).getType()+"hello world");
		boolean testUpdateAttschments = ProgramDataBase.updateAttachments(attachmentsTest, programID);
		assertTrue("failed: attachments were not apdated", testUpdateAttschments);
	
	}


	@Then("the new attachments should be added to the program")
	public void the_new_attachments_should_be_added_to_the_program() {
		boolean updated = ProgramDataBase.getLastUpdate();
		assertTrue("failed: attachments couldn't be updated", updated);
	}
	
	@When("adds attachments to the program with id {string}")
	public void adds_attachments_to_the_program_with_id(String string) {
		boolean updated = ProgramDataBase.getLastUpdate();
		assertTrue("failed: couldn't be updated", updated);
	}

	@When("the instructor wants to update the price to applicable price of {string}")
	public void the_instructor_wants_to_update_the_price_to_applicable_price_of(String price) {
		boolean testUpdatePrice = ProgramDataBase.updatePrice(price, "100000");
		assertTrue("failed: price was not updated", testUpdatePrice);
	}

	@Then("the program price should be updated.")
	public void the_program_price_should_be_updated() {
		
		boolean updated = ProgramDataBase.getLastUpdate();
		assertTrue("failed: couldn't be updated", updated);
	}

	@When("the instructor wants to update the price to unapplicable price of {string}")
	public void the_instructor_wants_to_update_the_price_to_unapplicable_price_of(String price) {
		price = "-100";
		boolean testUpdatePrice = ProgramDataBase.updatePrice(price, "100000");
		assertFalse("failed: price was updated", testUpdatePrice);
	}

	@Then("the program price should remain unchanged.")
	public void the_program_price_should_remain_unchanged() {
		boolean updated = ProgramDataBase.getLastUpdate();
		assertFalse("failed: couldn't be updated", updated);
	}
	
	@When("the instructor wants to delete the program with id {string}")
	public void the_instructor_wants_to_delete_the_program_with_id(String string) {
		boolean programDeleted = ProgramDataBase.deleteProgram("100001");
	    assertTrue("failed: program was not deleted", programDeleted);
	}

	@Then("the program should no longer appear in the instructors program list")
	public void the_program_should_no_longer_appear_in_the_instructors_program_list() {
		boolean programExists = ProgramDataBase.programExist("100001");
		assertFalse("failed: program exists", programExists);
	}

	@When("the instructor wants to set a schedule for the program with id {string} chooses session type as {string} schedual as {string}")
	public void the_instructor_wants_to_set_a_schedule_for_the_program_with_id_chooses_session_type_as_schedual_as(String id, String type, String schedule) {
		boolean checkSession = ProgramDataBase.setSchedule(type, schedule, "100002");
	    assertTrue("failed: group session was not added", checkSession);
	}
	
	@When("the instructor wants to set a schedule and chooses session type as {string} schedual as {string} for the program with id {string}")
	public void the_instructor_wants_to_set_a_schedule_and_chooses_session_type_as_schedual_as_for_the_program_with_id(String type, String schedule, String id) {
		boolean checkSession = ProgramDataBase.setSchedule(type, schedule, id);
	    assertTrue("failed: group session was not added", checkSession);
	}

	
	@When("the instructor wants to set a schedule for the program")
	public void the_instructor_wants_to_set_a_schedule_for_the_program() {
		boolean checkSession = ProgramDataBase.setSchedule("online", "every sunday at 6:00 PM", "100001");
	    assertTrue("failed: group session was not added", checkSession);
	}



	@Then("the schedule should be created succesfully and visible to the instructor and clients.")
	public void the_schedule_should_be_created_succesfully_and_visible_to_the_instructor_and_clients() {
		boolean updated = ProgramDataBase.getLastUpdate();
		assertTrue("failed: couldn't be updated", updated);
	}



	
	

	
	


	
	


}
