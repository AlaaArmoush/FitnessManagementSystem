package FeaturesMain;

public class Review {
	private String clientID;
	private String programID;
	private String rating;
	private String review;

	public Review(String clientID, String programID, String rating, String review) {
		this.clientID = clientID;
		this.programID = programID;
		this.rating = rating;
		this.review = review;
	}

	public String getClientID() {
		return clientID;
	}

	public String getProgramID() {
		return programID;
	}

	public String getRating() {
		return rating;
	}

	public String getReview() {
		return review;
	}

	public void setClientID(String clientID) {
		this.clientID = clientID;
	}

	public void setProgramID(String programID) {
		this.programID = programID;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public void setReview(String review) {
		this.review = review;
	}

	@Override
	public String toString() {
		return String.format("Review by Client ID: %s | Program ID: %s | Rating: %s/5 | Feedback: %s", clientID,
				programID, rating, review);
	}

}
