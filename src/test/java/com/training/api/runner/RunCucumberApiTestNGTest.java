package com.training.api.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.training.api.steps", "com.training.api.hooks"},
        plugin = {
                "pretty",
                "html:target/cucumber-api-report.html",
                "json:target/cucumber-api-report.json"
        },
        monochrome = true
)
public class RunCucumberApiTestNGTest extends AbstractTestNGCucumberTests {
}