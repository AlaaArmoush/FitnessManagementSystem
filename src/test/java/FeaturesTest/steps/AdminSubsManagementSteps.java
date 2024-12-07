package FeaturesTest.steps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import FeaturesMain.MyApplication;
import FeaturesMain.SubsDataBase;
import FeaturesMain.Subscription;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AdminSubsManagementSteps {
	MyApplication app;

	public AdminSubsManagementSteps() {
		app = new MyApplication();

	}

	@When("I create a subscription plan with tier {string}, price {string}, discount {string}, and days per month {string}")
	public void i_create_a_subscription_plan_with_tier_price_discount_and_days_per_month(String tier, String price,
			String discount, String days) {
		Subscription newPlan = new Subscription(tier, price, discount, days);
		boolean planCreated = SubsDataBase.addPlan(newPlan);
		assertTrue("Test failed: plan was not created successfully", planCreated);
	}

	@Then("the subscription plan should be created successfully")
	public void the_subscription_plan_should_be_created_successfully() {
		String tierTest = "Premium";
		boolean planExists = SubsDataBase.planExist(tierTest);
		assertTrue("Test failed: plan was not created successfully.", planExists);
	}

	@Given("a subscription plan with tier {string} exists")
	public void a_subscription_plan_with_tier_exists(String string) {
		String tierTest = "Basic";
		boolean planExists = SubsDataBase.planExist(tierTest);
		// System.out.print(SubsDataBase.getPlan(tierTest) + "\n");
		assertTrue("Test failed: plan does not exist", planExists);
	}

	@When("I update the price to {string}, discount to {string}, and days per month to {string}")
	public void i_update_the_price_to_discount_to_and_days_per_month_to(String price, String discount, String days) {
		boolean updated = SubsDataBase.updatePlan("Basic", price, discount, days);
		assertTrue("Test failed: Plan not updated.", updated);
	}

	@Then("the subscription plan should be updated successfully")
	public void the_subscription_plan_should_be_updated_successfully() {
		Subscription updatedPlan = SubsDataBase.getPlan("Basic");
		assertNotNull("Test failed: Updated plan not found.", updatedPlan);

		assertEquals("Test failed: Price not updated correctly.", "220ILS", updatedPlan.getPrice());
		assertEquals("Test failed: Discount not updated correctly.", "30%", updatedPlan.getDiscount());
		assertEquals("Test failed: Days not updated correctly.", "26", updatedPlan.getDays());
	}

	@When("I view the list of subscription plans")
	public void i_view_the_list_of_subscription_plans() {
		ArrayList<Subscription> subscriptionPlans = SubsDataBase.getAllSubscriptionPlans();
		System.out.println("Available Subscription Plans:");
		for (Subscription plan : subscriptionPlans) {
			System.out.println(plan);
		}
	}

	@Then("I should see all subscription plans with their details")
	public void i_should_see_all_subscription_plans_with_their_details() {
		ArrayList<Subscription> subscriptionPlans = SubsDataBase.getAllSubscriptionPlans();
		assertNotNull("Test failed: Subscription plans list is null.", subscriptionPlans);
		assertFalse("Test failed: No subscription plans available.", subscriptionPlans.isEmpty());

		for (Subscription plan : subscriptionPlans) {
			assertNotNull("Test failed: Subscription plan details are incomplete.", plan.getTier());
			assertNotNull("Test failed: Subscription plan details are incomplete.", plan.getPrice());
			assertNotNull("Test failed: Subscription plan details are incomplete.", plan.getDiscount());
			assertNotNull("Test failed: Subscription plan details are incomplete.", plan.getDays());
		}
	}

	@When("I delete the subscription plan")
	public void i_delete_the_subscription_plan() {
		boolean deleted = SubsDataBase.deletePlan("Basic");
		assertTrue("Test Failed: plan not deleted", deleted);
	}

	@Then("the subscription plan should no longer be available")
	public void the_subscription_plan_should_no_longer_be_available() {
		boolean shouldntExist = SubsDataBase.planExist("Basic");
		assertFalse("Test Failed: plan not deleted", shouldntExist);
	}

}
