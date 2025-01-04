package FeaturesTest.steps;

import static org.junit.Assert.assertEquals;

import FeaturesMain.Article;
import FeaturesMain.Attachment;
import FeaturesMain.InboxItem;
import FeaturesMain.InboxItemType;
import FeaturesMain.Program;
import FeaturesMain.Review;
import FeaturesMain.Session;
import io.cucumber.java.en.*;

public class LeftOversExtraCoverageSteps {
	private Article article;
	private Session session;
	private Review review;
	private Program program;
	private Attachment attachment;
	private InboxItem inboxItem;

	@Given("an article")
	public void an_article() {
		article = new Article(null, null, null);
	}

	@When("the ID is set to {string}")
	public void the_id_is_set_to(String id) {
		article.setID(id);
	}

	@Then("the article ID should be {string}")
	public void the_article_id_should_be(String expectedId) {
		assertEquals(expectedId, article.getID());
	}

	@When("the title is set to {string}")
	public void the_title_is_set_to(String title) {
		article.setTitle(title);
	}

	@Then("the article title should be {string}")
	public void the_article_title_should_be(String expectedTitle) {
		assertEquals(expectedTitle, article.getTitle());
	}

	@When("the URL is set to {string}")
	public void the_url_is_set_to(String url) {
		article.setURL(url);
	}

	@Then("the article URL should be {string}")
	public void the_article_url_should_be(String expectedURL) {
		assertEquals(expectedURL, article.getURL().toString());
	}

	@Then("the system should print {string}")
	public void the_system_should_print(String expectedMessage) {
		// should be printed
	}

	@Given("a session")
	public void a_session() {
		session = new Session("", "");
	}

	@When("the session type is set to {string}")
	public void the_session_type_is_set_to(String sessionType) {
		session.setSessionType(sessionType);
	}

	@Then("the session type should be {string}")
	public void the_session_type_should_be(String expectedSessionType) {
		assertEquals(expectedSessionType, session.getSessionType());
	}

	@When("the session schedule is set to {string}")
	public void the_session_schedule_is_set_to(String sessionSchedule) {
		session.setSessionSchedule(sessionSchedule);
	}

	@Then("the session schedule should be {string}")
	public void the_session_schedule_should_be(String expectedSessionSchedule) {
		assertEquals(expectedSessionSchedule, session.getSessionSchedule());
	}

	@Given("a review")
	public void a_review() {
		review = new Review("", "", "", "");
		program = new Program(); // Ensure the program object is initialized
		review.setProgramID(""); // Link review to the program ID initially
	}

	@When("the client ID is set to {string}")
	public void the_client_id_is_set_to(String clientID) {
		review.setClientID(clientID);
	}

	@When("the program ID set to {string}")
	public void the_program_id_is_set_to(String programID) {
		program.setProgramId(programID); // Set the program ID on the program object
		review.setProgramID(programID); // Update the review with the program ID
	}

	@When("the rating is set to {string}")
	public void the_rating_is_set_to(String rating) {
		review.setRating(rating);
	}

	@When("the review text is set to {string}")
	public void the_review_text_is_set_to(String reviewText) {
		review.setReview(reviewText);
	}

	@Then("the review should have client ID {string} and program ID {string}")
	public void the_review_should_have_client_id_and_program_id(String expectedClientID, String expectedProgramID) {
		assertEquals(expectedClientID, review.getClientID());
		assertEquals(expectedProgramID, review.getProgramID());
	}

	@Then("the rating should be {string}")
	public void the_rating_should_be(String expectedRating) {
		assertEquals(expectedRating, review.getRating());
	}

	@Then("the review text should be {string}")
	public void the_review_text_should_be(String expectedReviewText) {
		assertEquals(expectedReviewText, review.getReview());
	}

	@Given("an attachment")
	public void an_attachment() {
		attachment = new Attachment("image", "C:\\Users\\user\\Downloads\\Test.png");
	}

	@When("the type is set to {string}")
	public void the_type_is_set_to(String string) {
		attachment.setType(string);
	}

	@When("the path is set to {string}")
	public void the_path_is_set_to(String string) {
		attachment.setPath(string);
	}

	@Then("the attachment type should be {string}")
	public void the_attachment_type_should_be(String string) {
		assertEquals(string, attachment.getType());
	}

	@Then("the attachment path should be {string}")
	public void the_attachment_path_should_be(String string) {
		assertEquals(string, attachment.getPath());
	}

}
