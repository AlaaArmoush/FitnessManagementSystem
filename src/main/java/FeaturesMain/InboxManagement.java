package FeaturesMain;

public class InboxManagement {

	private static boolean lastInteraction;

	public static boolean sendReminderToAllClients(String message, String programId) {
		boolean sent = false;

		Program program = ProgramDataBase.getProgram(programId);
		InboxItem item = new InboxItem(InboxItemType.Reminder, message, program);
		if (program.getEnrolledClientCount() > 0) {
			for (User client : program.getEnrolledClient()) {
				client.addItemToInbox(item);
			}
			System.out.println("remainder has been sent to all clients enrolled in this program");
			sent = true;
		} else {
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

	public static boolean sendDirectMessage(String instructorId, String message, String clientId, String programId) {
		boolean sent = false;
		Program program = ProgramDataBase.getProgram(programId);
		InboxItem item = new InboxItem(InboxItemType.DirectMessage, instructorId, message);
		if (program.getEnrolledClientCount() > 0) {
			for (User client : program.getEnrolledClient()) {
				if (client.getID().equals(clientId)) {
					client.addItemToInbox(item);
					System.out.println("message has been sent");
					sent = true;
				}
			}
		} else {
			System.out.println("message has not been sent");
		}
		if (!sent) {
			System.out.println("message has not been sent");
		}
		setLastInteraction(sent);
		return sent;

	}

	public static boolean creatDicussionForum(String instructorId, String topicName, String message, String programId) {
		boolean sent = false;
		Program program = ProgramDataBase.getProgram(programId);
		InboxItem item = new InboxItem(InboxItemType.DiscussionForum, message, program);
		if (program.getEnrolledClientCount() > 0) {
			for (User client : program.getEnrolledClient()) {
				client.addItemToInbox(item);
			}
			System.out.println("discussion forum has been created and sent to all clients enrolled in this program");
			sent = true;
		} else {
			System.out.println("discussion forum has not been sent");
		}
		setLastInteraction(sent);
		return sent;

	}

	public static boolean sendFeedback(String instructorId, String topicName, String message, String programId) {
		boolean sent = false;
		Program program = ProgramDataBase.getProgram(programId);
		InboxItem item = new InboxItem(InboxItemType.Feedback, message, program);
		if (program.getEnrolledClientCount() > 0) {
			for (User client : program.getEnrolledClient()) {
				client.addItemToInbox(item);
			}
			System.out.println("feedback has been sent to all clients enrolled in this program");
			sent = true;
		} else {
			System.out.println("feedback has not been sent");
		}
		setLastInteraction(sent);
		return sent;

	}

	public static boolean sendProgressRepots(String programId) {
		boolean sent = false;
		Program program = ProgramDataBase.getProgram(programId);
		InboxItem item;
		String message;
		if (program.getEnrolledClientCount() > 0) {
			for (User client : program.getEnrolledClient()) {
				message = client.getReport(programId);
				item = new InboxItem(InboxItemType.ProgressReport, message, program);
				client.addItemToInbox(item);
			}
			System.out.println("feedback has been sent to all clients enrolled in this program");
			sent = true;
		} else {
			System.out.println("feedback has not been sent");
		}
		setLastInteraction(sent);
		return sent;

	}

	public static boolean SendNotificationAboutNewSchedule(String programId, String sessionSchedule) {
		boolean sent = false;

		Program program = ProgramDataBase.getProgram(programId);
		InboxItem item;
		String message = sessionSchedule;
		if (program.getEnrolledClientCount() > 0 && program != null) {
			for (User client : program.getEnrolledClient()) {
				item = new InboxItem(InboxItemType.UpdatesNotification, message, program);
				client.addItemToInbox(item);
			}
			System.out.println("notification has been sent to all clients enrolled in this program");
			sent = true;
		} else {
			System.out.println("notification has not been sent");
		}
		setLastInteraction(sent);
		return sent;

	}

	public static boolean announceNewProgram(String programId) {
		boolean sent = false;
		InboxItem item;
		Program program = ProgramDataBase.getProgram(programId);
		String message = String.format("new program announcment: \n" + program);

		for (User client : UserDataBase.getUsersList()) {
			if (client.getRole().equals("Client")) {
				item = new InboxItem(InboxItemType.Announcement, message, program);
				client.addItemToInbox(item);
				sent = true;
				System.out.println(item);
			}
		}
		if (sent) {
			System.out.println("announcement has been sent to all clients");
		} else {
			System.out.println("announcement has not been sent");
		}

		setLastInteraction(sent);
		return sent;

	}

	public static boolean announceSpecialOffer(String programId) {
		boolean sent = false;
		InboxItem item;
		Program program = ProgramDataBase.getProgram(programId);
		String message = String.format("special offer on program: \n" + program);

		for (User client : UserDataBase.getUsersList()) {
			if (client.getRole().equals("Client")) {
				item = new InboxItem(InboxItemType.Announcement, message, program);
				client.addItemToInbox(item);
				sent = true;
				System.out.println(item);
			}
		}
		if (sent) {
			System.out.println("announcement has been sent to all clients");
		} else {
			System.out.println("announcement has not been sent");
		}

		setLastInteraction(sent);
		return sent;

	}

}
