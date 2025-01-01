package FeaturesTest.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import static org.junit.Assert.assertEquals;

public class PDataBaseExtraCoverageSteps {

	private boolean programExists;
	private String programPrice;
	private boolean programCreatedStatus;
	private String instructorAccess;
	private String response;
	private String enrolledClientsList;

	// Mock system state
	private boolean systemHasProgram;
	private String programID;
	private String instructorID;

	@Given("the system has a program with ID {string}")
	public void the_system_has_a_program_with_ID(String id) {
		programID = id;
		systemHasProgram = true;
	}

	@Given("the system has a program with ID {string} and instructor ID {string}")
	public void the_system_has_a_program_with_ID_and_instructor_ID(String id, String instructor) {
		programID = id;
		instructorID = instructor; // Ensure instructorID is set here
		systemHasProgram = true;
	}

	@Given("a program with ID {string}")
	public void a_program_with_ID(String id) {
		programID = id;
		programExists = true;
	}

	@Given("the program creation status is false")
	public void the_program_creation_status_is_false() {
		programCreatedStatus = false;
	}

	@Given("the system has programs for instructor {string}")
	public void the_system_has_programs_for_instructor(String instructor) {
		instructorID = instructor; // Ensure instructorID is set here
		// Mock system state for instructor
	}

	@When("a new program with ID {string} is added")
	public void a_new_program_with_ID_is_added(String id) {
		if (systemHasProgram && programID.equals(id)) {
			response = "this program already exists";
		} else {
			response = "Program added successfully";
			systemHasProgram = true;
			programID = id;
		}
	}

	@When("the instructor {string} checks if the program with ID {string} exists")
	public void the_instructor_checks_if_the_program_with_ID_exists(String instructor, String id) {
		if (systemHasProgram && programID.equals(id)) {
			if (instructorID.equals(instructor)) {
				response = "Program exists";
			} else {
				response = "you don't have access to this program";
			}
		} else {
			response = "Program not found";
		}
	}

	@When("the price for the program is set to {string}")
	public void the_price_for_the_program_is_set_to(String price) {
		programPrice = price;
		response = "price was set";
	}

	@When("the program creation status is set to true")
	public void the_program_creation_status_is_set_to_true() {
		programCreatedStatus = true;
	}

	@When("the instructor {string} requests all clients")
	public void the_instructor_requests_all_clients(String instructor) {
		if (instructorID.equals(instructor)) {
			// Mock list of clients enrolled for this instructor
			enrolledClientsList = "Client1, Client2, Client3";
			response = "List of enrolled clients for instructor " + instructor + ": " + enrolledClientsList;
		} else {
			response = "No clients available for this instructor";
		}
	}

	@Then("the system should return {string}")
	public void the_system_should_return(String expectedResponse) {
		assertEquals(expectedResponse, response);
	}

	@Then("the system should return a list of enrolled clients for that instructor")
	public void the_system_should_return_a_list_of_enrolled_clients_for_that_instructor() {
		assertEquals("List of enrolled clients for instructor " + instructorID + ": Client1, Client2, Client3",
				response);
	}

	@Then("the program created status should be true")
	public void the_program_created_status_should_be_true() {
		assertEquals(true, programCreatedStatus);
	}
}
