package FeaturesMain;

public class ClientProfile {

	private String age;
	private int goal;
	private DietaryPreferences dietDescription;

	// Constructor
	public ClientProfile(String age, int goal, DietaryPreferences dietDescription) {
		this.age = age;
		this.goal = goal;
		this.dietDescription = dietDescription;
	}

	// Getters
	public String getAge() {
		return age;
	}

	public int getGoal() {
		return goal;
	}

	public DietaryPreferences getDiet() {
		return dietDescription;
	}

	// Setters
	public void setAge(String age) {
		this.age = age;
	}

	public void setGoal(int goal) {
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
