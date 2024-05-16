package runner;

import com.automation.lac.qa.listener.TestListener;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;

@CucumberOptions(
  features = "src/test/resources/features/fanapp",
  glue = {"com/automation/lac/qa/fanapp/mobile/hooks",
    "com/automation/lac/qa/fanapp/mobile/stepsdefinitions"},
  snippets = CucumberOptions.SnippetType.CAMELCASE,
  plugin = {"pretty", "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"}
)

@Listeners(TestListener.class)
public class FanAppRunnerTest extends AbstractTestNGCucumberTests {

  @Override
  @DataProvider(parallel = true)
  public Object[][] scenarios() {
    return super.scenarios();
  }

}