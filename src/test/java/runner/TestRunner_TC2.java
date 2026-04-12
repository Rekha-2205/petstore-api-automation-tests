package runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "steps",
        tags = "@TC2",
        plugin = {"pretty", "html:target/cucumber-tc2.html"},
        monochrome = true
)

public class TestRunner_TC2 {
}