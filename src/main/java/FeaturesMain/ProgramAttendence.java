package FeaturesMain;

public class ProgramAttendence {

	private Program program;
	private Integer attendedSessions;
	private Double completionRate;
	private Integer absent;

	public ProgramAttendence(Program program) {
		this.program = program;
		this.attendedSessions = 0;
		this.absent = 0;
	}

	public Program getProgram() {
		return program;
	}

	public void setProgram(Program program) {
		this.program = program;
	}

	public Integer getAttendedSessions() {
		return attendedSessions;
	}

	public void setAttendedSessions(Integer attendedSessions) {
		this.attendedSessions = attendedSessions;
		this.updateCompletionRate();
	}

	public boolean checkLowAttendency() {
		boolean lowAttendency = true;
		int absentCount = program.getSessionsCount() - attendedSessions;
		if (absentCount < 6) {
			lowAttendency = false;
		}

		return lowAttendency;

	}

	public Double getCompletionRate() {
		return completionRate;
	}

	public void setCompletionRate(Double completionRate) {
		this.completionRate = completionRate;
	}

	private void updateCompletionRate() {
		double completedHours = (double) this.attendedSessions * 1.5;
		this.completionRate = (completedHours / this.program.getTargetedHours()) * 100;
	}

	public void updateAbsent() {
		this.absent++;
		this.updateAttendedSessions();
		this.updateCompletionRate();

	}

	public Integer getAbsent() {
		return absent;
	}

	public void setAbsent(Integer absent) {
		this.absent = absent;
	}

	private void updateAttendedSessions() {
		this.attendedSessions = program.getSessionsCount() - this.absent;
	}

	public String createProgressReport() {
		return String.format("absents: " + this.getAbsent() + " completion rate: " + this.getCompletionRate());
	}
}
