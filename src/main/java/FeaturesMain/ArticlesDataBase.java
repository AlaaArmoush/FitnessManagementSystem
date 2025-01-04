package FeaturesMain;

import java.awt.Desktop;
import java.net.URL;
import java.util.ArrayList;

/**
 * A utility class for managing the storage and approval of articles. Handles
 * adding, approving, rejecting, and retrieving articles.
 * 
 * @author: Alaa Armoush
 */
public class ArticlesDataBase {
	private static ArrayList<Article> pendingArticles = new ArrayList<>();
	private static ArrayList<Article> approvedArticles = new ArrayList<>();

	private ArticlesDataBase() {

	}

	/**
	 * Adds an article to the pending list.
	 * 
	 * @param id    The article's unique identifier.
	 * @param title The title of the article.
	 * @param link  The URL link of the article.
	 */
	public static void addArticle(String id, String title, URL link) {
		Article a = new Article(id, title, link);
		pendingArticles.add(a);
		System.out.println(title + ": article wating for approval from admin");
	}

	/**
	 * Checks if an article is pending approval.
	 * 
	 * @param id The article's ID.
	 * @return true if the article is pending, false otherwise.
	 */
	public static boolean articlePended(String id) {
		for (Article a : pendingArticles)
			if (a.getID().equals(id)) {
				System.out.println("Article waiting for approval");
				return true;
			}
		return false;
	}

	/**
	 * Checks if an article is approved.
	 * 
	 * @param id The article's ID.
	 * @return true if the article is approved, false otherwise.
	 */
	public static boolean articleApproved(String id) {
		for (Article a : approvedArticles)
			if (a.getID().equals(id))
				return true;
		return false;
	}

	/**
	 * Approves an article and moves it from the pending list to the approved list.
	 * 
	 * @param id The article's ID.
	 * @return true if the article was approved, false otherwise.
	 */
	public static boolean approveArticle(String id) {
		for (Article a : pendingArticles) {
			if (a.getID().equals(id)) {
				approvedArticles.add(a);
				pendingArticles.remove(a);
				return true;
			}
		}

		System.out.println("article not found");
		return false;
	}

	/**
	 * Opens the URL of an approved article in the default web browser.
	 * 
	 * @param id The article's ID.
	 */
	public static void openURL(String id) {
		try {
			System.out.println("opening url....");
			Article article = getArticle(id);
			if (article == null) {
				System.err.println("No Article Found");
				return;
			}
			Desktop.getDesktop().browse(article.getURL().toURI());
		} catch (Exception e) {
			System.err.println("Error opening URL in browser: " + e.getMessage());
		}
	}

	/**
	 * Retrieves an approved article by ID.
	 * 
	 * @param id The article's ID.
	 * @return The article if found, or null if not found.
	 */
	public static Article getArticle(String id) {
		for (Article a : approvedArticles)
			if (a.getID().equals(id))
				return a;
		return null;
	}

	/**
	 * Rejects an article and removes it from the pending list.
	 * 
	 * @param id The article's ID.
	 * @return true if the article was rejected, false otherwise.
	 */
	public static boolean rejectArticle(String id) {
		for (Article a : pendingArticles) {
			if (a.getID().equals(id)) {
				System.out.println("Article rejected: " + a.getTitle());
				pendingArticles.remove(a);
				return true;
			}
		}

		return false;
	}

	/**
	 * Retrieves the list of approved articles.
	 * 
	 * @return An unmodifiable list of approved articles.
	 */
	public static ArrayList<Article> getApprovedList() {
		return approvedArticles;
	}

	/**
	 * Retrieves the list of pending articles.
	 * 
	 * @return An unmodifiable list of pending articles.
	 */
	public static ArrayList<Article> getPendingArticles() {
		return pendingArticles;
	}

}
