package runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions
        (
                features = "src/test/resources/features/user_TC3.feature",
                glue = {"steps"},
                tags = "@TC3",
                plugin = {"pretty", "html:target/cucumber-reports-tc3.html"},
                monochrome = true
        )

public class TestRunner_TC3 {
}
