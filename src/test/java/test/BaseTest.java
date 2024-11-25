package test;

import pages.FirstPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import java.nio.file.Paths;
import org.openqa.selenium.chrome.ChromeOptions;

public class BaseTest {
    private static ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();
    protected FirstPage page;
    protected WebDriver getDriver() {
        return driverThread.get();
    }

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", Paths.get("chromedriver.exe").toAbsolutePath().toString());
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driverThread.set(new ChromeDriver(options));
        page = new FirstPage(getDriver());
    }

    @AfterClass
    public void tearDown() {
        if (getDriver() != null) {
            getDriver().quit();
            driverThread.remove();
        }
    }
}
