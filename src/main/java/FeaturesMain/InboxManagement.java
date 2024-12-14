package FeaturesMain;

public class InboxManagement {
	
	private static boolean lastInteraction;
	
	public static boolean sendReminderToAllClients(String message, String programId) {
		boolean sent = false;
		
		Program program = ProgramDataBase.getProgram(programId);
		InboxItem item = new InboxItem(InboxItemType.Reminder, message, program);
		if(program.getEnrolledClientCount()>0) {
			for (User client : program.getEnrolledClient()) {
				client.addItemToInbox(item);
			}
			System.out.println("remainder has been sent to all clients enrolled in this program");
			sent = true;
		}
		else {
			System.out.println("remainder has not been sent");
		}
		setLastInteraction(sent);
		return sent;
	}

	public static boolean getLastInteraction() {
		return lastInteraction;
	}

	private static void setLastInteraction(boolean lastInteraction) {
		InboxManagement.lastInteraction = lastInteraction;
	}

	public static boolean sendDirectMessage(String clientId2, String message, String clientId, String programId) {
		boolean sent = false;
				
				Program program = ProgramDataBase.getProgram(programId);
				InboxItem item = new InboxItem(InboxItemType.DirectMessage, message, program);
				if(program.getEnrolledClientCount()>0) {
					for (User client : program.getEnrolledClient()) {
						if(client.getID().equals(clientId)) {
							client.addItemToInbox(item);
							System.out.println("message has been sent");
							sent = true;
						}
					}
					
				}
				else {
					System.out.println("message has not been sent");
				}
				if(!sent) {
					System.out.println("message has not been sent");
				}
				setLastInteraction(sent);
				return sent;
	}

	public static boolean creatDicussionForum(String instructorId, String topicName, String message, String programId) {
		boolean sent = false;
				
				Program program = ProgramDataBase.getProgram(programId);
				InboxItem item = new InboxItem(InboxItemType.DiscussionForum, message, program);
				if(program.getEnrolledClientCount()>0) {
					for (User client : program.getEnrolledClient()) {
						client.addItemToInbox(item);
					}
					System.out.println("discussion forum has been created and sent to all clients enrolled in this program");
					sent = true;
				}
				else {
					System.out.println("discussion forum has not been sent");
				}
				setLastInteraction(sent);
				return sent;
	}

	public static boolean sendFeedback(String instructorId, String topicName, String message, String programId) {
		boolean sent = false;
		
				Program program = ProgramDataBase.getProgram(programId);
				InboxItem item = new InboxItem(InboxItemType.Feedback, message, program);
				if(program.getEnrolledClientCount()>0) {
					for (User client : program.getEnrolledClient()) {
						client.addItemToInbox(item);
					}
					System.out.println("feedback has been sent to all clients enrolled in this program");
					sent = true;
				}
				else {
					System.out.println("feedback has not been sent");
				}
				setLastInteraction(sent);
				return sent;
	}

	public static boolean sendProgressRepots(String programId) {
		boolean sent = false;
		
			Program program = ProgramDataBase.getProgram(programId);
			InboxItem item ;
			String message;
			if(program.getEnrolledClientCount()>0) {
				for (User client : program.getEnrolledClient()) {
					message = client.getReport(programId);
					item = new InboxItem(InboxItemType.ProgressReport, message, program);
					client.addItemToInbox(item);
				}
				System.out.println("feedback has been sent to all clients enrolled in this program");
				sent = true;
			}
			else {
				System.out.println("feedback has not been sent");
			}
			setLastInteraction(sent);
			return sent;
	}
	
	
	
	
}
