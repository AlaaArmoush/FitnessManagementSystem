package FeaturesTest.steps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import FeaturesMain.MyApplication;
import FeaturesMain.Program;
import FeaturesMain.ProgramDataBase;
import FeaturesMain.UserDataBase;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AdminProgramMonitoringSteps {
	MyApplication app;
	ArrayList<Program> rankedPrograms = new ArrayList<>();
	String report1, report2;

	public AdminProgramMonitoringSteps() {
		app = new MyApplication();
	}

	@Given("there are programs with enrolled users")
	public void there_are_programs_with_enrolled_users() {
		for (Program P : ProgramDataBase.getProgramsList()) {
			if (P.getProgramId().equals("100000")) {
				P.setStaus(1);
				P.addClient(UserDataBase.getUser("client789"));
				P.addClient(UserDataBase.getUser("client101"));
			} else if (P.getProgramId().equals("100001")) {
				P.setStaus(1);
				P.addClient(UserDataBase.getUser("client102"));
			} else if (P.getProgramId().equals("100002")) {
				P.setStaus(1);
				P.addClient(UserDataBase.getUser("client103"));
				P.addClient(UserDataBase.getUser("client104"));
				P.addClient(UserDataBase.getUser("client105"));
				P.addClient(UserDataBase.getUser("client106"));
				P.addClient(UserDataBase.getUser("client107"));
			}
		}

		for (Program P : ProgramDataBase.getProgramsList()) {
			if (P.getProgramId().equals("100000")) {
				assertEquals("Program 100000 should have 2 enrolled clients", 2, P.getEnrolledClientCount());
			} else if (P.getProgramId().equals("100001")) {
				assertEquals("Program 100001 should have 1 enrolled client", 1, P.getEnrolledClientCount());
			} else if (P.getProgramId().equals("100002")) {
				assertEquals("Program 100002 should have 5 enrolled clients", 5, P.getEnrolledClientCount());
			}
		}
	}

	@When("I ask for the programs report")
	public void i_ask_for_the_programs_report() {
		rankedPrograms = ProgramDataBase.getRankedPrograms();

		for (int i = 0; i < rankedPrograms.size() - 1; i++) {
			int currentClientCount = rankedPrograms.get(i).getEnrolledClientCount();
			int nextClientCount = rankedPrograms.get(i + 1).getEnrolledClientCount();
			assertTrue("Programs should be ranked by enrolled client count in descending order",
					currentClientCount >= nextClientCount);
		}
	}

	@Then("a ranking of programs by popularity and their current status should be displayed")
	public void a_ranking_of_programs_by_popularity_and_their_current_status_should_be_displayed() {
		System.out.println();
		ProgramDataBase.showRankedPrograms();
	}

	@When("I ask for the monthly revenue report")
	public void i_ask_for_the_monthly_report() {
		report1 = UserDataBase.generateRevenueEstimate();

		assertTrue("The report should contain 'Gross Revenue'", report1.contains("Gross Revenue"));
		assertTrue("The report should contain 'Net Revenue'", report1.contains("Net Revenue"));
		assertTrue("The report should contain 'Active Clients'", report1.contains("Active Clients"));
		assertTrue("The report should contain 'Active Instructors'", report1.contains("Active Instructors"));
	}

	@Then("an estitame of this months revenue, should be created.")
	public void a_report_of_revenue_attendance_and_client_progress() {
		assertNotNull("The report should not be null", report1);
		System.out.println("\n" + report1);
	}

	@When("I ask for the monthly client progress report")
	public void i_ask_for_the_monthly_client_progress_report() {
		report2 = UserDataBase.generateClientProgressReport();
	}

	@Then("a report should be created.")
	public void a_report_should_be_created() {
		assertNotNull("The report should not be null", report2);
		System.out.println("\n" + report2);
	}

}
