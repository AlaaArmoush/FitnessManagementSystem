package FeaturesTest.steps;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import FeaturesMain.Article;
import FeaturesMain.ArticlesDataBase;
import FeaturesMain.Feedback;
import FeaturesMain.MyApplication;
import FeaturesMain.Review;
import FeaturesMain.ReviewsDataBase;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AdminContentManagementSteps {
	MyApplication app;
	ArrayList<Article> articlesList;
	ArrayList<Review> reviewsList;
	ArrayList<Feedback> feedbackList;

	public AdminContentManagementSteps() {
		app = new MyApplication();
	}

	@Given("a new article was added id {string} link {string}")
	public void a_new_article_was_added_link(String id, String link) {
		try {
			URL a = new URL(link);
			String title = "Defining a Healthy Diet";
			ArticlesDataBase.addArticle(id, title, a);
			assertTrue("Article was added", ArticlesDataBase.articlePended(id));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	@When("I approve a pending article")
	public void i_approve_a_pending_article() {
		ArticlesDataBase.approveArticle("a00");
		assertFalse("Article was removed from pending", ArticlesDataBase.articlePended("a00"));
	}

	@Then("that article should be moved from the pending list to the articles list")
	public void that_article_should_be_moved_from_the_pending_list_to_the_articles_list() {
		String id = "a00";
		assertTrue("Article was not approved", ArticlesDataBase.articleApproved(id));

		// testing the url opener
		// ArticlesDataBase.openURL(id);
	}

	@Given("an article with id {string} link {string} pending")
	public void an_article_with_id_link_pending(String id, String link) {
		try {
			URL a = new URL(link);
			String title = "The Supplements Doctors Actually Think You Should Take";
			ArticlesDataBase.addArticle(id, title, a);
			assertTrue("Article was added", ArticlesDataBase.articlePended(id));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	@When("I reject a pending article")
	public void i_reject_a_pending_article() {
		String id = "d00";
		ArticlesDataBase.rejectArticle(id);
		assertFalse("Article was not approved", ArticlesDataBase.articleApproved(id));
	}

	@Then("that article should be removed from the pending list")
	public void that_article_should_be_removed_from_the_pending_list() {
		String id = "d00";
		assertFalse("Article was not approved", ArticlesDataBase.articleApproved(id));
	}

	@When("I want to see the available articles")
	public void i_want_to_see_the_available_articles() {
		articlesList = ArticlesDataBase.getApprovedList();
		assertTrue("The approved articles list should not be null", articlesList != null);
	}

	@Then("all approved articles linked should be shown")
	public void all_approved_articles_linked_should_be_shown() {
		System.out.println("******Approved Articles******");
		for (Article article : articlesList) {
			assertTrue("Article ID should not be null or empty", article.getID() != null && !article.getID().isEmpty());
			assertTrue("Article title should not be null or empty",
					article.getTitle() != null && !article.getTitle().isEmpty());
			assertTrue("Article URL should not be null", article.getURL() != null);

			// Optionally, print details for verification
			System.out.println(article);
		}
		System.out.println("*****************************\n");

	}

	@When("I want to see all clients feedback")
	public void i_want_to_see_all_clients_feedback() {
		feedbackList = ReviewsDataBase.getFeedbackList();
		assertTrue("The list should not be null", feedbackList != null);
	}

	@Then("all clients feedback should be shown")
	public void all_clients_feedback_should_be_shown() {
		System.out.println("******Clients Feedback******");
		for (Feedback feedback : feedbackList) {
			assertTrue("can't have empty feedback",
					feedback.getFeedbackDescription() != null && !feedback.getFeedbackDescription().isEmpty());
			System.out.println(feedback);
		}
		System.out.println("*****************************\n");

	}

	@When("I want to see all clients programs reviews")
	public void i_want_to_see_all_clients_programs_reviews() {
		reviewsList = ReviewsDataBase.getReviewsList();
		assertTrue("The list shouldn't be null", reviewsList != null);
	}

	@Then("all clients reviews should be shown")
	public void all_clients_reviews_should_be_shown() {
		System.out.println("******Clients Reviews******");
		for (Review review : reviewsList) {
			assertTrue("can't have empty review", review.getReview() != null && !review.getReview().isEmpty());
			assertTrue("can't have rating-less review", review.getRating() != null && !review.getRating().isEmpty());

			System.out.println(review);
		}
		System.out.println("*****************************\n");

	}

}
