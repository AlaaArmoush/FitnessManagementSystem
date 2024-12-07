package FeaturesMain;

import java.util.ArrayList;

public class ProfileDataBase {

	public static boolean addProfile(String clientID, String age, String goal, String diet) {
		// Retrieve the user from UserDataBase
		User user = UserDataBase.getUser(clientID);

		if (user != null && user.getProfile() == null) {
			try {
				// Convert the diet string to the DietaryPreferences enum
				DietaryPreferences dietPreference = DietaryPreferences.valueOf(diet);

				// Create a new ClientProfile and assign it to the user
				ClientProfile newProfile = new ClientProfile(age, goal, dietPreference);
				user.setProfile(newProfile);
				System.out.println("Created Profile: " + user.getProfile());
				return true;
			} catch (IllegalArgumentException e) {
				// Handle invalid diet strings
				System.out.println("Error: Invalid diet preference provided - " + diet);
			}
		}
		return false;
	}

	public static boolean updateProfile(String clientID, String age, String goal, String diet) {
		// Retrieve the user from UserDataBase
		User user = UserDataBase.getUser(clientID);

		if (user != null && user.getProfile() != null) {
			try {
				// Convert the diet string to the DietaryPreferences enum
				DietaryPreferences dietPreference = DietaryPreferences.valueOf(diet);

				// Update the existing profile
				ClientProfile existingProfile = user.getProfile();
				existingProfile.setAge(age);
				existingProfile.setGoal(goal);
				existingProfile.setDiet(dietPreference);
				System.out.println("Updated Profile: " + user.getProfile());

				return true;
			} catch (IllegalArgumentException e) {
				// Handle invalid diet strings
				System.out.println("Error: Invalid diet preference provided - " + diet);
			}
		}
		return false; // Return false if the user or profile doesn't exist
	}

	public static boolean deleteProfile(String clientID) {
		User user = UserDataBase.getUser(clientID);
		user.setProfile(null);
		System.out.println("Deleted Profile for: " + user + " Successfully");
		return true;
	}
}
