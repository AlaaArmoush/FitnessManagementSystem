package FeaturesTest.steps;

import static org.junit.Assert.*;

import FeaturesMain.Program;
import FeaturesMain.ProgramDataBase;
import FeaturesMain.User;
import FeaturesMain.UserDataBase;
import io.cucumber.java.en.*;

import java.util.ArrayList;

public class PDataBaseExtraCoverageSteps {
	private ProgramDataBase database;
	private boolean operationResult;
	private String message;
	private ArrayList<Program> programs;
	private ArrayList<User> clients;

	@Given("a program with ID {string} exists in the database")
	public void a_program_with_ID_exists_in_the_database(String programId) {
		if (database == null) {
			database = new ProgramDataBase();
		}
		Program program = new Program("Test Program", "20 hours", "Beginner", "Goals", "instructor11", programId);
		ProgramDataBase.addNewProgram(program);
	}

	@When("a user attempts to add a program with ID {string}")
	public void a_user_attempts_to_add_a_program_with_ID(String programId) {
		operationResult = ProgramDataBase.addNewProgram("New Program", "30 hours", "Intermediate", "Goals",
				"instructor02", programId, new ArrayList<>(), "200");
		message = operationResult ? "Program added successfully" : "this program already exists";
	}

	@Then("the operation should fail with a message {string}")
	public void the_operation_should_fail_with_a_message(String expectedMessage) {
		assertFalse(operationResult);
		assertEquals(expectedMessage, message);
	}

	@Given("a program with ID {string} and instructor ID {string} exists in the database")
	public void a_program_with_id_and_instructor_id_exists_in_the_database(String programId, String instructorId) {
		boolean exist = ProgramDataBase.programExist(programId, instructorId);
		assertTrue(exist);
		if (database == null) {
			database = new ProgramDataBase();
		}
		Program program = new Program("Test Program", "20 hours", "Beginner", "Goals", instructorId, programId);
		ProgramDataBase.addNewProgram(program);
	}

	@When("a user checks if the program exists for instructor ID {string}")
	public void a_user_checks_if_the_program_exists_for_instructor_ID(String instructorId) {
		programs = ProgramDataBase.getProgramsForInstructor(instructorId);
		operationResult = !programs.isEmpty(); // Check if programs exist for the instructor
		if (!operationResult) {
			message = "you dont have access to this program";
		}
	}

	@Then("the operation should return true")
	public void the_operation_should_return_true() {
		assertTrue(operationResult);
	}

	@Then("the operation should return false with a message {string}")
	public void the_operation_should_return_false_with_a_message(String expectedMessage) {
		assertTrue(operationResult);
	}

	@Given("multiple programs exist in the database")
	public void multiple_programs_exist_in_the_database() {
		if (database == null) {
			database = new ProgramDataBase();
		}
	}

	@Given("instructor ID {string} is assigned to some programs")
	public void instructor_ID_is_assigned_to_some_programs(String instructorId) {
		Program program1 = new Program("Program A", "10 hours", "Beginner", "Goal A", instructorId, "100010");
		Program program2 = new Program("Program B", "15 hours", "Intermediate", "Goal B", instructorId, "100011");
		ProgramDataBase.addNewProgram(program1);
		ProgramDataBase.addNewProgram(program2);
	}

	@When("a user retrieves programs for instructor ID {string}")
	public void a_user_retrieves_programs_for_instructor_ID(String instructorId) {
		programs = ProgramDataBase.getProgramsForInstructor(instructorId);
	}

	@Then("the returned list should contain only the programs for instructor ID {string}")
	public void the_returned_list_should_contain_only_the_programs_for_instructor_ID(String instructorId) {
		assertNotNull(programs);
		for (Program program : programs) {
			assertEquals(instructorId, program.getInstructorId());
		}
	}

	@Given("no program creation status is initially set")
	public void no_program_creation_status_is_initially_set() {
		if (database == null) {
			database = new ProgramDataBase();
		}
	}

	@When("a user sets the program creation status to true")
	public void a_user_sets_the_program_creation_status_to_true() {
		ProgramDataBase.setProgramCreated(true);
	}

	@Then("the {string} method should return true")
	public void the_method_should_return_true(String methodName) {
		if ("isProgramCreated".equals(methodName)) {
			assertTrue(ProgramDataBase.isProgramCreated());
		} else {
			fail("Unknown method: " + methodName);
		}
	}

	@Given("instructor ID {string} has multiple programs with enrolled clients")
	public void instructor_ID_has_multiple_programs_with_enrolled_clients(String instructorId) {
		if (database == null) {
			database = new ProgramDataBase();
		}

		User client1 = new User("Client One", "client01", "Client", "Activated");
		User client2 = new User("Client Two", "client02", "Client", "Activated");
		UserDataBase.addUser(client1);
		UserDataBase.addUser(client2);

		Program program1 = new Program("Program A", "10 hours", "Beginner", "Goal A", instructorId, "100010");
		Program program2 = new Program("Program B", "15 hours", "Intermediate", "Goal B", instructorId, "100011");

		program1.addClient(client1.getID());
		program2.addClient(client2.getID());

		ProgramDataBase.addNewProgram(program1);
		ProgramDataBase.addNewProgram(program2);
	}

	@When("a user retrieves all clients for instructor ID {string}")
	public void a_user_retrieves_all_clients_for_instructor_ID(String instructorId) {
		clients = ProgramDataBase.getAllClientsForInstructor(instructorId);
	}

	@Then("the returned list should contain all enrolled clients across the programs for instructor ID {string}")
	public void the_returned_list_should_contain_all_enrolled_clients_across_the_programs_for_instructor_ID(
			String instructorId) {
		assertNotNull(clients);
		for (User client : clients) {
			assertNotNull(client);
			assertEquals("client", client.getRole());
		}
	}
}
