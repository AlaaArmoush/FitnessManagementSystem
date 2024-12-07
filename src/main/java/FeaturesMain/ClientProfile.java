package FeaturesMain;

public class ClientProfile {

	private String age;
	private String goal;
	private DietaryPreferences dietDescription;

	// Constructor
	public ClientProfile(String age, String goal, DietaryPreferences dietDescription) {
		this.age = age;
		this.goal = goal;
		this.dietDescription = dietDescription;
	}

	// Getters
	public String getAge() {
		return age;
	}

	public String getGoal() {
		return goal;
	}

	public DietaryPreferences getDiet() {
		return dietDescription;
	}

	// Setters
	public void setAge(String age) {
		this.age = age;
	}

	public void setGoal(String goal) {
		this.goal = goal;
	}

	public void setDiet(DietaryPreferences dietDescription) {
		this.dietDescription = dietDescription;
	}

	public String toString() {
		return "ClientProfile{" + "age = '" + age + '\'' + ", goal = '" + goal + '\'' + ", Diet description = "
				+ dietDescription.getDescription() + '}';
	}
}
