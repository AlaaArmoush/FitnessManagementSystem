package FeaturesMain;

import java.util.ArrayList;

public class ReviewsDataBase {
	private static ArrayList<Review> reviewsList = new ArrayList<>();
	private static ArrayList<Feedback> feedbackList = new ArrayList<>();

	public static boolean addReview(String clientID, String programID, String rating, String review) {
		Review testReview = new Review(clientID, programID, rating, review);
		reviewsList.add(testReview);
		System.out.println(testReview + "\n");
		return true;
	}

	public static boolean reviewExist(String clientID, String programID) {
		for (Review u : reviewsList)
			if (u.getClientID().equals(clientID) && u.getProgramID().equals(programID)) {
				System.out.println(u.getClientID() + " has a review for: " + u.getProgramID());
				return true;
			}
		return false;
	}

	public static boolean addFeedback(String clientID, String feedback) {
		Feedback testFeedback = new Feedback(clientID, feedback);
		feedbackList.add(testFeedback);
		System.out.println(testFeedback + "\n");
		return true;
	}

	public static boolean feedbackExist(String clientID) {
		for (Feedback f : feedbackList)
			if (f.getClientID().equals(clientID)) {
				System.out.println(f.getClientID() + " has provided feedback");
				return true;
			}
		return false;
	}

}
