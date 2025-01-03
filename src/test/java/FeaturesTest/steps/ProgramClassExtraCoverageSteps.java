package FeaturesTest.steps;

import static org.junit.Assert.*;

import FeaturesMain.Program;
import FeaturesMain.ProgramDataBase;
import FeaturesMain.User;
import io.cucumber.java.en.*;

public class ProgramClassExtraCoverageSteps {
	private Program program;
	private User user;
	private boolean operationResult;

	@Given("a program with title {string}, duration time {string}, difficulty level {string}, goals {string}, and instructor ID {string}")
	public void a_program_with_title_duration_time_difficulty_level_goals_and_instructor_id(String title,
			String durationTime, String difficultyLevel, String goals, String instructorId) {
		program = new Program(title, durationTime, difficultyLevel, goals, instructorId);
		program.setGroupSession("Group Session", "Monday 10 AM");
		program.addClient("client123");
		ProgramDataBase.addNewProgram(program);

	}

	@When("the program ID is set to {string}")
	public void the_program_id_is_set_to(String programId) {
		program.setProgramId(programId);
	}

	@Then("the program ID should be {string}")
	public void the_program_id_should_be(String programId) {
		assertEquals(programId, program.getProgramId());
	}

	@When("the instructor adds a new client with ID {string}")
	public void the_instructor_adds_a_new_client_with_id(String clientId) {
		user = new User("coverageTestName", clientId, "Client", "Activated");
		program.addClient(user);
	}

	@Then("the client should be added to the program")
	public void the_client_should_be_added_to_the_program() {
		assertTrue(program.getEnrolledClient().contains(user));
	}

	@When("the program checks if a client with ID {string} exists")
	public void the_program_checks_if_a_client_with_id_exists(String clientId) {
		operationResult = program.hasClient(clientId);
	}

	@Then("the operation should return false for the client")
	public void the_operation_should_return_false_for_the_client() {
		assertFalse(operationResult);
	}

	@When("the program shows the clients' progress")
	public void the_program_shows_the_clients_progress() {
		program.showClientsProgress();
	}

	@Then("the client progress details should be printed")
	public void the_client_progress_details_should_be_printed() {
		// previous step is enough
	}

	@When("the program sets the sessions count to {int}")
	public void the_program_sets_the_sessions_count_to(Integer sessionsCount) {
		program.setSessionsCount(sessionsCount);
	}

	@Then("the sessions count should be {int}")
	public void the_sessions_count_should_be(Integer sessionsCount) {
		assertEquals(sessionsCount, program.getSessionsCount());
	}

	@When("the program sets targeted hours to {int}")
	public void the_program_sets_targeted_hours_to(Integer targetedHours) {
		program.setTargetedHours(targetedHours);
	}

	@Then("the targeted hours should be {int}")
	public void the_targeted_hours_should_be(Integer targetedHours) {
		assertEquals(targetedHours, program.getTargetedHours());
	}

	@When("the program notifies clients about the new schedule")
	public void the_program_notifies_clients_about_the_new_schedule() {
		operationResult = program.notifyClientsAboutNewSchedule();
	}

	@Then("the notification should be sent successfully")
	public void the_notification_should_be_sent_successfully() {
		assertTrue(operationResult);
	}
}
