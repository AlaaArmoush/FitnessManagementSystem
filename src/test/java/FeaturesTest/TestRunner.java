package FeaturesTest;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "FeaturesCucumber\\AdminSubsManagement.feature", glue = "FeaturesTest.steps")
public class TestRunner {
	// No step definitions here. This class is solely for running tests.
}
