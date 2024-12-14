package FeaturesMain;

public class Session {
	
	private String sessionType; 
	private String sessionSchedule;
	
	
	public Session(String sessionType, String sessionSchedule) {
		this.sessionType = sessionType;
		this.sessionSchedule = sessionSchedule;
	}
	
	
	public String getSessionType() {
		return sessionType;
	}
	public void setSessionType(String sessionType) {
		this.sessionType = sessionType;
	}
	public String getSessionSchedule() {
		return sessionSchedule;
	}
	public void setSessionSchedule(String sessionSchedule) {
		this.sessionSchedule = sessionSchedule;
	}
	
	public String toString() {
		return String.format("session type: "+ sessionType +" schedule: "+sessionSchedule);
		
	}
	
	

}
