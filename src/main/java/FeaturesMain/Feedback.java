package FeaturesMain;

public class Feedback {
	private String clientID;
	private String feedback;

	public Feedback(String clientID, String feedback) {
		this.clientID = clientID;
		this.feedback = feedback;
	}

	public String getClientID() {
		return clientID;
	}

	public String getFeedbackDescription() {
		return feedback;
	}

	public String toString() {
		return "Feedback from " + clientID + " says: " + feedback;
	}

}
