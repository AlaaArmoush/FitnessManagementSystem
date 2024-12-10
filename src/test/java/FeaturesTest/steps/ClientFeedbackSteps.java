package FeaturesTest.steps;

import static org.junit.Assert.assertTrue;

import FeaturesMain.MyApplication;
import FeaturesMain.ReviewsDataBase;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ClientFeedbackSteps {
	MyApplication app;

	public ClientFeedbackSteps() {
		app = new MyApplication();
	}

	@When("I rate program {string} with rating {string} and review {string}")
	public void i_rate_program_with_rating_and_review(String programID, String rating, String review) {
		boolean reivewCreated = ReviewsDataBase.addReview("client123", programID, rating, review);
		assertTrue("Test Faild: review was not created", reivewCreated);
	}

	@Then("that review should be created and added to the reviews data base")
	public void that_review_should_be_created_and_added_to_the_reviews_data_base() {
		boolean reviewAdded = ReviewsDataBase.reviewExist("client123", "program123");
		assertTrue("Test Failed: review was not added to data base", reviewAdded);
	}

	@When("I write the feedback {string}")
	public void i_write_the_feedback(String feedback) {
		boolean feedbackCreated = ReviewsDataBase.addFeedback("client123", feedback);
		assertTrue("Test failed: feedback was not created", feedbackCreated);
	}

	@Then("that feedback should be created and added to the feedbacks data base")
	public void that_feedback_should_be_created_and_added_to_the_feedbacks_data_base() {
		boolean feedbackAdded = ReviewsDataBase.feedbackExist("client123");
		assertTrue("Test failed: feedback was not added to data base", feedbackAdded);

	}

}
