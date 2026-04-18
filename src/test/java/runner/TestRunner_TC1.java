package runner;

import base.BaseTest;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions
        (
                features = "src/test/resources/features/petLifecycle_TC1.feature",
                glue = {"steps"},
                tags = "@TC1",
                plugin = {"pretty", "html:target/cucumber-tc1.html"},
                monochrome = true
        )
public class TestRunner_TC1 extends BaseTest {
}
