package FeaturesMain;

import java.util.ArrayList;

public class SubsDataBase {
	private static ArrayList<Subscription> subsList = new ArrayList<>();

	static {
		Subscription testSub = new Subscription("Basic", "100ILS", "10%", "20");
		Subscription testSub2 = new Subscription("Student", "100", "0%", "20");
		subsList.add(testSub);
		subsList.add(testSub2);

	}

	public static boolean addPlan(Subscription newPlan) {
		// check if plan already exists
		planExist(newPlan.getTier());

		subsList.add(newPlan);
		System.out.println("New plan added: " + newPlan);
		return true;
	}

	public static boolean planExist(String tierTest) {
		for (Subscription s : subsList)
			if (s.getTier().equals(tierTest))
				return true;
		return false;

	}

	public static boolean updatePlan(String tier, String price, String discount, String days) {
		System.out.print("Plan details pre-update: " + getPlan(tier) + "\n");
		for (Subscription s : subsList) {
			if (s.getTier().equals(tier)) {
				boolean updated = false;
				if (!s.getPrice().equals(price)) {
					s.setPrice(price);
					updated = true;
				}

				if (!s.getDiscount().equals(discount)) {
					s.setDiscount(discount);
					updated = true;
				}

				if (!s.getDays().equals(days)) {
					s.setDays(days);
					updated = true;
				}

				if (updated) {
					System.out.println("Plan details updated: " + s);
				}
				return true; // plan found and updated
			}
		}

		return false; // plan not found
	}

	public static Subscription getPlan(String tier) {
		for (Subscription s : subsList)
			if (s.getTier().equals(tier))
				return s;
		return null;
	}

	public static ArrayList<Subscription> getAllSubscriptionPlans() {
		return subsList;
	}

	public static boolean deletePlan(String tier) {
		for (Subscription s : subsList)
			if (s.getTier().equals(tier)) {
				subsList.remove(s);
				System.out.println("The " + tier + " Plan got deleted");
				return true;
			}

		return false;
	}

}
