package com.cucumbercraft.stepdefinitions;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.aventstack.extentreports.model.Category;
import com.aventstack.extentreports.model.context.NamedAttributeContext;
import com.cucumbercraft.framework.DriverManager;
import com.cucumbercraft.framework.Settings;
import com.cucumbercraft.framework.TestHarness;
import com.cucumbercraft.framework.Util;
import com.cucumbercraft.runners.RunCucumberTests;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class CukeHooks extends MasterStepDefs {

    static Logger log = LogManager.getLogger(CukeHooks.class);

    public static Properties propertiesFile = Settings.getInstance();


    private TestHarness testHarness;
    public static String[] arr;


    @Before
    public void setUp(Scenario scenario) {

        testHarness = new TestHarness();
        currentScenario = scenario;
        properties = propertiesFile;
        DriverManager.getTestParameters().setScenarioName(scenario.getName());
//		driver=WebDriverFactory.getWebDriver(Browser.CHROME);
//		DriverManager.setWebDriver(driver);
        testHarness.invokeDriver(scenario);
    }





	/*@BeforeStep
	public static void beforeStep() throws IOException {
//		ExtentCucumberAdapter.getCurrentStep().log(Status.FAIL,MediaEntityBuilder.createScreenCaptureFromBase64String(Objects.requireNonNull(Util.takeScreenshotasFile(DriverManager.getWebDriver()))).build());
	}


	@AfterStep
	public static void afterStep() throws IOException {
//		ExtentCucumberAdapter.getCurrentStep().log(Status.FAIL,MediaEntityBuilder.createScreenCaptureFromBase64String(Objects.requireNonNull(Util.takeScreenshotasFile(DriverManager.getWebDriver()))).build());
	}*/

    @After
    public void tearDown(Scenario scenario) {


//		testHarness.updateDefectInJira(scenario);
//		testHarness.downloadAddtionalReports(scenario);
        String testName = Pattern.compile("\\b[Tt][Cc]\\d+\\b")
                .matcher(scenario.getName())
                .results()
                .map(MatchResult::group)
                .findFirst()
                .orElse(scenario.getName());
        //		String testName=scenario.getName().substring(scenario.getName().lastIndexOf(" ")+1).replaceAll("_"," ");


        if (currentScenario.isFailed()) {
            scenario.attach(Util.takeScreenshot(DriverManager.getWebDriver()), "image/png", scenario.getStatus() + " screenshot : " + scenario.getName());


        }

        RunCucumberTests.statusMap.add(Map.of(testName, ExtentCucumberAdapter.getCurrentScenario().getStatus().getName()));
        testHarness.closeRespestiveDriver(scenario);
        Set<NamedAttributeContext<Category>> ex = ExtentCucumberAdapter.getCurrentScenario().getExtent().getReport().getCategoryCtx().getSet();
        ex.clear();


    }


}