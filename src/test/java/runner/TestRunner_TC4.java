package runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions
        (
                features = "src/test/resources/features/pet_TC4.feature",
                glue = {"steps"},
                tags = "@TC4",
                plugin = {"pretty", "html:target/cucumber-reports-tc4.html"},
                monochrome = true
        )
public class TestRunner_TC4 {
}
