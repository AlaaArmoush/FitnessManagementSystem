package FeaturesTest;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)

@CucumberOptions(features = "FeaturesCucumber//InstructorClientInteraction.feature", glue = "FeaturesTest.steps")
public class TestRunner {

}
