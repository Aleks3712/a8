package test;

import org.testng.annotations.Test;
import org.testng.annotations.Listeners;

@Listeners(TestListener.class)
public class YandexTest extends BaseTest {

    @Test
    public void yandexSearchTest() throws InterruptedException {
        getDriver().get("https://www.ya.ru");
        page.inputSecondPage();
        System.out.println("Тест завершен успешно.");
    }
}