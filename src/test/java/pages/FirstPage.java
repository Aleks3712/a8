package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class FirstPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Локаторы
    private By InputgoogleLocator = By.xpath("//textarea[@title='Поиск']");
    private By InputyandexLocator = By.id("text");
    private By ButtonLocator = By.xpath("//button[@type='submit']");

    public FirstPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Методы
    public void inputFirstPage() {
        WebElement Inputgoogle = wait.until(ExpectedConditions.presenceOfElementLocated(InputgoogleLocator));
        Assert.assertNotNull(Inputgoogle, "Поле ввода поиска Google не найдено!");
        Assert.assertTrue(Inputgoogle.isDisplayed(), "Поле ввода поиска Google не отображается!");
        Assert.assertTrue(Inputgoogle.isEnabled(), "Поле ввода поиска Google отключено!");
        Inputgoogle.click();
        Inputgoogle.sendKeys("yandex");
        Assert.assertEquals(Inputgoogle.getAttribute("value"), "yandex",
                "Текст 'yandex' не был введен в поле ввода поиска Google!");
    }

    public void inputSecondPage() {
        WebElement Inputyandex = wait.until(ExpectedConditions.presenceOfElementLocated(InputyandexLocator));
        Assert.assertNotNull(Inputyandex, "Поле ввода поиска Yandex не найдено!");
        Assert.assertTrue(Inputyandex.isDisplayed(), "Поле ввода поиска Yandex не отображается!");
        Assert.assertTrue(Inputyandex.isEnabled(), "Поле ввода поиска Yandex отключено!");
        Inputyandex.click();
        Inputyandex.sendKeys("yandex");
        Assert.assertEquals(Inputyandex.getAttribute("value"), "yandex",
                "Текст 'yandex' не был введен в поле ввода поиска Yandex!");
        WebElement Button = wait.until(ExpectedConditions.elementToBeClickable(ButtonLocator));
        Assert.assertNotNull(Button, "Кнопка отправки не найдена!");
        Assert.assertTrue(Button.isDisplayed(), "Кнопка отправки не отображается!");
        Assert.assertTrue(Button.isEnabled(), "Кнопка отправки отключена!");
        Button.click();
    }
}
