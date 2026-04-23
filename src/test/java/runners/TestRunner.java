package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"steps", "base"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports.html",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        },
        monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }

    @AfterSuite
    public void executeJMeterCLI() {
        System.out.println("=====================================================");
        System.out.println("UI & API COMPLETE. INITIATING JMETER NON-GUI MODE...");
        System.out.println("=====================================================");

        try {
            // 1. Generate a timestamp so JMeter doesn't crash from overwriting old files
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

            // 2. Map the exact paths in your framework
            String jmxPath = "src\\test\\resources\\jmeter\\LoadTest.jmx";
            String jtlPath = "target\\jmeter_results_" + timestamp + ".jtl";
            String htmlReportDir = "target\\HTML_Dashboard_" + timestamp;

            // 3. Construct the exact industry-standard command you requested
            String command = String.format("jmeter -n -t %s -l %s -e -o %s", jmxPath, jtlPath, htmlReportDir);

            System.out.println("Executing Command: " + command);

            // 4. Fire the command in an invisible CMD terminal
            ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", command);
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();

            // 5. Print the JMeter terminal output directly into IntelliJ
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            process.waitFor();
            System.out.println("=====================================================");
            System.out.println("SUCCESS! HTML Report generated at: " + htmlReportDir);
            System.out.println("=====================================================");

        } catch (Exception e) {
            System.err.println("Failed to execute native JMeter command: " + e.getMessage());
        }
    }
}