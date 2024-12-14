package FeaturesMain;

public class ProgressTracking {
	
	private static boolean lastInteraction;
	
	public static boolean displayClientsProgress(String programId) {
		boolean displayed = false;
		Program temp = ProgramDataBase.getProgram(programId);
		if (temp.getEnrolledClientCount()>0) {
			for (User u : temp.getEnrolledClient()) {
				System.out.println("name: " + u.getName() + " Attended session(s): " + u.getAttendence(temp)+ " completion rate: "+ u.getCompletionRate(temp));
				displayed = true;
			}
		}
		
		lastInteraction = displayed;
		return displayed;
		
	}
	
	public static boolean checkLastInteraction() {
		return lastInteraction;
	}

}
