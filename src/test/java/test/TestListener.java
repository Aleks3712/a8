package test;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.imageio.ImageIO;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        // Папка для сохранения скриншотов
        String screenshotDir = Paths.get("screenshots").toAbsolutePath().toString();
        File dir = new File(screenshotDir);
        if (!dir.exists() && !dir.mkdirs()) {
            System.err.println("Не удалось создать папку для скриншотов: " + screenshotDir);
            return;
        }

        // Формирование имени файла
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String screenshotName = result.getName() + "_" + timestamp + ".png";
        String screenshotPath = Paths.get(screenshotDir, screenshotName).toString();

        Object testInstance = result.getInstance();
        WebDriver driver = null;

        if (testInstance instanceof BaseTest) {
            driver = ((BaseTest) testInstance).getDriver();
        }

        if (driver != null) {
            // Скриншот для UI тестов
            takeWebDriverScreenshot(driver, screenshotPath);
        } else {
            // Скриншот всего экрана для не UI тестов
            takeFullScreenScreenshot(screenshotPath);
        }
    }

    private void takeWebDriverScreenshot(WebDriver driver, String screenshotPath) {
        try {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileHandler.copy(screenshot, new File(screenshotPath));
            System.out.println("Скриншот сохранен: " + screenshotPath);
        } catch (IOException e) {
            System.err.println("Ошибка при сохранении скриншота WebDriver: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void takeFullScreenScreenshot(String screenshotPath) {
        try {
            Robot robot = new Robot();
            Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage screenCapture = robot.createScreenCapture(screenRect);
            File screenFile = new File(screenshotPath);
            ImageIO.write(screenCapture, "png", screenFile);
            System.out.println("Скриншот экрана сохранен: " + screenFile.getPath());
        } catch (AWTException | IOException e) {
            System.err.println("Ошибка при создании скриншота всего экрана: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Остальные методы можно оставить пустыми или использовать для логгирования
    @Override
    public void onTestStart(ITestResult result) {}
    @Override
    public void onTestSuccess(ITestResult result) {}
    @Override
    public void onTestSkipped(ITestResult result) {}
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}
    @Override
    public void onTestFailedWithTimeout(ITestResult result) {}
    @Override
    public void onStart(ITestContext context) {}
    @Override
    public void onFinish(ITestContext context) {}
}
