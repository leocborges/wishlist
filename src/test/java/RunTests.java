import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Run Cucumber tests.
 */
@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/steps")
public class RunTests {

    @Test
    public void cucumber() {
    }

}
