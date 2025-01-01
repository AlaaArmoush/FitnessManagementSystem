package FeaturesMain;

import java.awt.Desktop;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class ArticlesDataBase {
	private static ArrayList<Article> pendingArticles = new ArrayList<>();
	private static ArrayList<Article> approvedArticles = new ArrayList<>();

	public static void addArticle(String id, String title, URL link) {
		Article a = new Article(id, title, link);
		pendingArticles.add(a);
		System.out.println(title + ": article wating for approval from admin");
	}

	public static boolean articlePended(String id) {
		for (Article a : pendingArticles)
			if (a.getID().equals(id)) {
				System.out.println("Article waiting for approval");
				return true;
			}
		return false;
	}

	public static boolean articleApproved(String id) {
		for (Article a : approvedArticles)
			if (a.getID().equals(id))
				return true;
		return false;
	}

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

	public static void openURL(String id) {
		try {
			System.out.println("opening url....");
			Article article = getArticle(id);
			if (article == null) {
				System.err.println("No Article Found");
				return;
			} else
				Desktop.getDesktop().browse(getArticle(id).getURL().toURI());
		} catch (Exception e) {
			System.err.println("Error opening URL in browser: " + e.getMessage());
		}
	}

	public static Article getArticle(String id) {
		for (Article a : approvedArticles)
			if (a.getID().equals(id))
				return a;
		return null;
	}

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

	public static ArrayList<Article> getApprovedList() {
		return approvedArticles;
	}

	public static ArrayList<Article> getPendingArticles() {
		return pendingArticles;
	}

}
