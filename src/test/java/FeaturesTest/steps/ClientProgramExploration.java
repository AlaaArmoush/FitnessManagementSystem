package FeaturesTest.steps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import FeaturesMain.MyApplication;
import FeaturesMain.Program;
import FeaturesMain.ProgramDataBase;
import FeaturesMain.UserDataBase;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ClientProgramExploration {
	MyApplication app;
	ArrayList<Program> difficultyList;
	ArrayList<Program> durationList;
	ArrayList<String> schedulesList;

	public ClientProgramExploration() {
		app = new MyApplication();
	}

	@When("I want to see programs for difficulty {string}")
	public void i_want_to_see_programs_for(String difficulty) {
		difficultyList = ProgramDataBase.getListDifficulty(difficulty);
		assertTrue("list should not be null", difficultyList != null);
	}

	@Then("All programs of the difficulty {string} should be shown")
	public void all_programs_of_the_difficulty_should_be_shown(String difficulty) {
		System.out.println("***Filtered By Difficulty***");
		for (Program p : difficultyList) {
			assertEquals("Program difficulty level does not match the expected difficulty", difficulty,
					p.getdifficultyLevel());
			System.out.println(p.getTitle() + " - Difficulty : " + p.getdifficultyLevel());

		}
		System.out.println("****************************\n");
	}

	@When("I want to see programs that go up to {int} hours")
	public void i_want_to_see_programs_that_go_up_to_hours(Integer duration) {
		durationList = ProgramDataBase.getListDuration(duration);
		assertTrue("list should not be null", durationList != null);

	}

	@Then("All programs of that duration {int} should be shown")
	public void all_programs_of_that_duration_should_be_shown(Integer duration) {
		System.out.println("***Filtered By Duration Up To " + duration + " Hours***");
		for (Program p : durationList) {
			assertTrue("Program duration isn't in range", p.getTargetedHours() <= duration);
			System.out.println(p.getTitle() + " - Duration: " + p.getTargetedHours());
		}
	}

	@When("I want to entroll in program id {string}")
	public void i_want_to_entroll_in_program_id(String id) {
		System.out.println("***************************************\n");
		ProgramDataBase.getProgram(id).addClient("client123");
	}

	@Then("I should be added to that program {string}")
	public void i_should_be_added_to_that_program(String id) {
		System.out.println();
		System.out.println("client123 enrolled in " + ProgramDataBase.getProgram(id).getTitle());
		assertTrue("Client Added to Program", ProgramDataBase.getProgram(id).hasClient("client123"));
		System.out.println();
	}

	@When("I want to see my programs schedule")
	public void i_want_to_see_my_programs_schedule() {
		schedulesList = UserDataBase.getUser("client123").getSchedules();
		assertTrue("list should not be null", schedulesList != null);
	}

	@Then("I should see the scheduled session for each program im enrolled in")
	public void i_should_see_the_scheduled_session_for_each_program_im_enrolled_in() {
		System.out.println("***Schedules For user: client123 ***");
		for (String schedule : schedulesList) {
			assertTrue("shouldn't be null", schedule != null);
			System.out.println(schedule);
		}
		System.out.println("****************************************\n");

	}

}
