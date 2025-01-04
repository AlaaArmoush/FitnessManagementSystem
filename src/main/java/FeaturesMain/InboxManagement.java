package FeaturesMain;

/**
 * A utility class for managing inbox operations for clients and instructors.
 * Provides functionalities to send reminders, messages, notifications, feedback, and announcements.
 * 
 * @author: Mohie aldeen Halawa
 */

public class InboxManagement {

	private static boolean lastInteraction;

		
	private InboxManagement() {
		
	}
	/**
     * Sends a reminder message to all clients enrolled in a specific program.
     *
     * @param message The reminder message content to send.
     * @param programId The ID of the program.
     * @return {true} if the reminder was sent successfully, {false} otherwise.
     */
	public static boolean sendReminderToAllClients(String message, String programId) {
		boolean sent = false;

		Program program = ProgramDataBase.getProgram(programId);
		InboxItem item = new InboxItem(InboxItemType.Reminder, message, program);
		return sendToAllEnrolledClients(program, item);
	}
	
	
	/**
     * Sends a direct message from an instructor to a specific client.
     *
     * @param instructorId The ID of the instructor.	
     * @param message The message content.			
     * @param clientId The ID of the client.
     * @param programId The ID of the program.
     * @return {true} if the message was sent successfully, {false} otherwise.
     */
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
	
	
	/**
     * Creates a discussion forum for a specific program and sends it to all enrolled clients.
     *
     * @param instructorId The ID of the instructor.
     * @param topicName The topic of the discussion forum.
     * @param message The discussion forum content.
     * @param programId The ID of the program.
     * @return {true} if the forum was sent successfully, {false} otherwise.
     */
	public static boolean creatDicussionForum(String instructorId, String topicName, String message, String programId) {
		boolean sent = false;
		Program program = ProgramDataBase.getProgram(programId);
		InboxItem item = new InboxItem(InboxItemType.DiscussionForum, message, program);
		return sendToAllEnrolledClients(program, item);

	}
	
	/**
     * Sends feedback for a specific program to all enrolled clients.
     *
     * @param instructorId The ID of the instructor.
     * @param topicName The topic of the discussion forum.
     * @param message The discussion forum content.
     * @param programId The ID of the program.
     * @return {true} if the forum was sent successfully, {false} otherwise.
     */
	public static boolean sendFeedback(String instructorId, String topicName, String message, String programId) {
		boolean sent = false;
		Program program = ProgramDataBase.getProgram(programId);
		InboxItem item = new InboxItem(InboxItemType.Feedback, message, program);
		return sendToAllEnrolledClients(program, item);

	}
	
	/**
     * Sends progress report for a specific program to all enrolled clients.
     *
     * @param instructorId The ID of the instructor.
     * @param topicName The topic of the discussion forum.
     * @param message The discussion forum content.
     * @param programId The ID of the program.
     * @return {true} if the forum was sent successfully, {false} otherwise.
     */
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

	/**
     * Sends notification about new schedule for a specific program to all enrolled clients.
     *
     * @param instructorId The ID of the instructor.
     * @param topicName The topic of the discussion forum.
     * @param message The discussion forum content.
     * @param programId The ID of the program.
     * @return {true} if the forum was sent successfully, {false} otherwise.
     */
	public static boolean SendNotificationAboutNewSchedule(String programId, String sessionSchedule) {
		boolean sent = false;

		Program program = ProgramDataBase.getProgram(programId);
		InboxItem item;
		String message = sessionSchedule;
		item = new InboxItem(InboxItemType.UpdatesNotification, message, program);
		return sendToAllEnrolledClients(program, item);

	}

	/**
     * Sends announcement for a new program to all clients.
     *
     * @param instructorId The ID of the instructor.
     * @param topicName The topic of the discussion forum.
     * @param message The discussion forum content.
     * @param programId The ID of the program.
     * @return {true} if the forum was sent successfully, {false} otherwise.
     */
	public static boolean announceNewProgram(String programId) {
		InboxItem item;
		Program program = ProgramDataBase.getProgram(programId);
		String message = String.format("New program announcement: %n%s", program);
		item = new InboxItem(InboxItemType.Announcement, message, program);
		return sendToAllClientsInTheSystem(program, message, item);

	}
	
	/**
     * Sends announcement for a special offer to all clients.
     *
     * @param instructorId The ID of the instructor.
     * @param topicName The topic of the discussion forum.
     * @param message The discussion forum content.
     * @param programId The ID of the program.
     * @return {true} if the forum was sent successfully, {false} otherwise.
     */
	public static boolean announceSpecialOffer(String programId) {
		
		InboxItem item;
		Program program = ProgramDataBase.getProgram(programId);
		String message = String.format("special offer on program: %n%s", program);
		item = new InboxItem(InboxItemType.Announcement, message, program);
		return sendToAllClientsInTheSystem(program, message, item);

	}

	/**
	 * Sends an announcement to all clients in the system.
	 *
	 * @param program The program associated with the announcement.
	 * @param item The inbox item containing the announcement details.
	 * @return {true} if the announcement was sent successfully, {false} otherwise.
	 */
	private static boolean sendToAllClientsInTheSystem(Program program, String message, InboxItem item) {
		boolean sent = false;
		for (User client : UserDataBase.getUsersList()) {
			if ("Client".equals(client.getRole())) {
				client.addItemToInbox(item);
				sent = true;
				System.out.println(item);
			}
		}
		if (sent) {
			System.out.println("announcement has been sent to all clients");
			setLastInteraction(true);
			return true;
		} else {
			System.out.println("announcement could not be sent");
			setLastInteraction(false);
            return false;
		}

	}
	
	/**
     * Helper method to send an inbox item to all clients enrolled in a specific program.
     *
     * @param program The program whose clients will receive the message.
     * @param item The inbox item to send.
     * @param itemTypeDesc A description of the item type (e.g., "Reminder", "Feedback").
     * @return {true} if the message was sent successfully, {false} otherwise.
     */
	private static boolean sendToAllEnrolledClients(Program program, InboxItem item) {
		if (program.getEnrolledClientCount() > 0) {
			for (User client : program.getEnrolledClient()) {
				client.addItemToInbox(item);
			}
			System.out.println("remainder has been sent to all clients enrolled in this program");
			setLastInteraction(true);
			return true;
		} else {
			System.out.println("message could not be sent.");
			setLastInteraction(false);
            return false;
		}
		
	}
	
	/**
     * Retrieves the status of the last interaction.
     *
     * @return {true} if the last interaction was successful, {false} otherwise.
     */
    public static boolean getLastInteraction() {
        return lastInteraction;
    }

    /**
     * Sets the status of the last interaction.
     *
     * @param lastInteraction The status of the last interaction.
     */
    private static void setLastInteraction(boolean lastInteraction) {
        InboxManagement.lastInteraction = lastInteraction;
    }

}
