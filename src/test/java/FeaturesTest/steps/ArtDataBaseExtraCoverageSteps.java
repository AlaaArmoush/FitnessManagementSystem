package FeaturesTest.steps;

import static org.junit.Assert.*;

import FeaturesMain.Article;
import FeaturesMain.ArticlesDataBase;
import java.net.URL;
import java.net.MalformedURLException;
import io.cucumber.java.en.*;

import java.util.ArrayList;

public class ArtDataBaseExtraCoverageSteps {
	private Article article;
	private boolean operationResult;
	private ArrayList<Article> articles;

	@Given("an article with ID {string} and title {string} is pending approval")
	public void an_article_with_id_and_title_is_pending_approval(String id, String title) throws MalformedURLException {
		article = new Article(id, title, new URL("http://example.com"));
		ArticlesDataBase.addArticle(id, title, new URL("http://example.com"));
	}

	@When("the admin approves the article with ID {string}")
	public void the_admin_approves_the_article_with_id(String id) {
		operationResult = ArticlesDataBase.approveArticle(id);
	}

	@Then("the article should be moved to the approved list")
	public void the_article_should_be_moved_to_the_approved_list() {
		assertTrue(operationResult);
		assertTrue(ArticlesDataBase.articleApproved(article.getID()));
	}

	@When("the admin rejects the article with ID {string}")
	public void the_admin_rejects_the_article_with_id(String id) {
		operationResult = ArticlesDataBase.rejectArticle(id);
	}

	@Then("the article should be removed from the pending list")
	public void the_article_should_be_removed_from_the_pending_list() {
		assertTrue(operationResult);
		assertFalse(ArticlesDataBase.articlePended(article.getID()));
	}

	@Given("an article with ID {string} and title {string} is approved with a valid URL")
	public void an_article_with_id_and_title_is_approved_with_a_valid_url(String id, String title)
			throws MalformedURLException {
		article = new Article(id, title, new URL("http://example.com"));
		ArticlesDataBase.addArticle(id, title, new URL("http://example.com"));
		ArticlesDataBase.approveArticle(id);
	}

	@When("a user opens the URL for the article with ID {string}")
	public void a_user_opens_the_url_for_the_article_with_id(String id) {
		try {
			ArticlesDataBase.openURL(id);
			operationResult = true; // Assuming the URL opens successfully
		} catch (Exception e) {
			operationResult = false;
		}
	}

	@Then("the URL should open in the browser")
	public void the_url_should_open_in_the_browser() {
		assertTrue(operationResult);
	}

	@Given("an article with ID {string} does not exist")
	public void an_article_with_id_does_not_exist(String id) {
		article = null; // Ensure the article is not in the database
	}

	@When("a user attempts to open the URL for the article with ID {string}")
	public void a_user_attempts_to_open_the_url_for_the_article_with_id(String id) {
		try {
			ArticlesDataBase.openURL(id);
			operationResult = false; // If URL opens, something went wrong
		} catch (Exception e) {
			operationResult = true; // Error message was shown
		}
	}

	@Then("an error message {string} should be shown")
	public void an_error_message_should_be_shown(String expectedMessage) {
		assertFalse(operationResult);
	}

	@When("the admin retrieves the article with ID {string}")
	public void the_admin_retrieves_the_article_with_id(String id) {
		article = ArticlesDataBase.getArticle(id);
	}

	@Then("the article should be returned")
	public void the_article_should_be_returned() {
		assertNotNull(article);
	}

	@Then("the operation should return null")
	public void the_operation_should_return_null() {
		assertNull(article);
	}

	@When("the admin checks if the article with ID {string} exists in the pending list")
	public void the_admin_checks_if_the_article_with_id_exists_in_the_pending_list(String id) {
		operationResult = ArticlesDataBase.articlePended(id);
	}

	@Then("the operation should return true for the pending list")
	public void the_operation_should_return_true_for_the_pending_list() {
		assertTrue(operationResult);
	}

	@When("the admin checks if the article with ID {string} exists in the approved list")
	public void the_admin_checks_if_the_article_with_id_exists_in_the_approved_list(String id) {
		operationResult = ArticlesDataBase.articleApproved(id);
	}

	@Then("the operation should return true for the approved list")
	public void the_operation_should_return_true_for_the_approved_list() {
		assertTrue(operationResult);
	}
}
