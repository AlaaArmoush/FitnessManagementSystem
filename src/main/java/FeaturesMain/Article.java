package FeaturesMain;

import java.net.MalformedURLException;
import java.net.URL;

public class Article {
	private String id;
	private String title;
	private URL link;

	public Article(String id, String t, URL a) {
		this.id = id;
		this.title = t;
		this.link = a;
	}

	public String getID() {
		return this.id;
	}

	public void setID(String id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String t) {
		this.title = t;
	}

	public URL getURL() {
		return this.link;
	}

	public void setURL(String url) {
		try {
			this.link = new URL(url);
		} catch (MalformedURLException e) {
			System.err.println("URL not accepeted");
		}
	}

	public String toString() {
		return "Article " + id + " :" + title + ": " + link;
	}
}
