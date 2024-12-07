package FeaturesMain;

public enum DietaryPreferences {
	GLUTEN_FREE("Gluten-free diet restricts gluten-containing foods."),
	VEGETARIAN("Vegetarian diet avoids all forms of meat."), KETO("Keto diet emphasizes high-fat and low-carb foods."),
	VEGAN("Vegan diet avoids all animal products."),
	MEDITERRANEAN(
			"Mediterranean diet emphasizes fruits, vegetables, whole grains, legumes, and healthy fats like olive oil."),
	LOW_CARB("Low-carb diet limits carbohydrate intake."), LOW_FAT("Low-fat diet focuses on reducing fat intake.");

	private String description;

	DietaryPreferences(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}
