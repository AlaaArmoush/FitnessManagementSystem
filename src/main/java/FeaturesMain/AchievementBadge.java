package FeaturesMain;

public enum AchievementBadge {
	NEWBIE_CHAMP("Newbie Champ Badge: Conqueror of the First Step"), // For completing the first program
	RISING_STAR("Rising Star Badge: Achiever of the Second Milestone"), // For completing the second program
	FITNESS_WARRIOR("Fitness Warrior Bade: Third Triumph Champion"), // For completing the third program
	TENACIOUS_TITAN("Tenacious Titan Badge: Fourth Feat Overcomer"), // For completing the fourth program
	GOAL_CRUSHER("Goal Crusher Badge: Fifth Level Victor"), // For completing the fifth program
	UNSTOPPABLE_FORCE("Unstoppable Force Badge: Sixth Success Story"), // For completing the sixth program
	LEGENDARY_GRIT("Legendary Grit Badge: Seventh Summit Reacher"); // For completing the seventh program

	private final String description;

	AchievementBadge(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
