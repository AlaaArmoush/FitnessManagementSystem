package FeaturesMain;

public class Feedback {
	private String clientID;
	private String feedback;

	public Feedback(String clientID, String feedback) {
		this.clientID = clientID;
		this.feedback = feedback;
	}

	public Object getClientID() {
		return clientID;
	}

	public String toString() {
		return "Feedback from " + clientID + " says:" + feedback;
	}

}
