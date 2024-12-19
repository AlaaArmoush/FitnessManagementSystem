package FeaturesMain;

import java.util.ArrayList;

public class ReviewsDataBase {
	private static ArrayList<Review> reviewsList = new ArrayList<>();
	private static ArrayList<Feedback> feedbackList = new ArrayList<>();

	static {
		// Adding sample reviews
		reviewsList.add(new Review("client123", "100000", "5", "Amazing program! Very helpful."));
		reviewsList.add(new Review("client456", "100001", "4", "Great content, but could use more examples."));

		// Adding sample feedbacks
		feedbackList.add(new Feedback("client123", "Please add more advanced topics."));
		feedbackList.add(new Feedback("client456", "Loved the interactive sessions!"));
	}

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

	public static ArrayList<Feedback> getFeedbackList() {
		return feedbackList;
	}

	public static ArrayList<Review> getReviewsList() {
		return reviewsList;
	}

}
