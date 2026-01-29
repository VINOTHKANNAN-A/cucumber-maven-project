package com.training.api.hooks;
 
import com.training.api.context.ApiContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
 
public class Hooks {
 
    private final ApiContext context;
 
    public Hooks(ApiContext context) {
        this.context = context;
    }
 
    @Before
    public void beforeScenario(Scenario scenario) {
        System.out.println("\n==============================");
        System.out.println("START: " + scenario.getName());
        System.out.println("==============================");
        // context is new per scenario (PicoContainer), nothing else needed here
    }
 
    @After
    public void afterScenario(Scenario scenario) {
        System.out.println("==============================");
        System.out.println("END: " + scenario.getName() + " | Status: " + scenario.getStatus());
        System.out.println("==============================\n");
    }
}
 