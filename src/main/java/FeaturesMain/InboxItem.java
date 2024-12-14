package FeaturesMain;

public class InboxItem {
	
	private InboxItemType type; //direct message, updates notification, recommendation or reminder
	private String senderId;
	private String message;
	private Program program;  //for attendance notification 
	
	public InboxItem(InboxItemType type, String senderId, String message) {
		new InboxItem(type, senderId, message, null);
		
	}
	
	public InboxItem(InboxItemType type, String message, Program program) {
		new InboxItem(type, program.getInstructorId(), message, program);
	}
	
	
	public InboxItem(InboxItemType type, String senderId, String message, Program program) {
		this.type = type;
		this.senderId = senderId;
		this.message = message;
		this.program = program;
	}
	
	

	public InboxItemType getType() {
		return type;
	}
	public void setType(InboxItemType type) {
		this.type = type;
	}
	public String getSenderId() {
		return senderId;
	}
	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Program getProgram() {
		return program;
	}
	public void setProgram(Program program) {
		this.program = program;
	}
	
	

}
