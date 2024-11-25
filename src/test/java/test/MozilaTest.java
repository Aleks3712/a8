package test;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class MozilaTest extends BaseTest {

    @Test
    public void openMozillaAndCauseError() {
        getDriver().get("https://www.mozilla.org");
        if (getDriver().getTitle().equals("Title")) {
            System.out.println();
        } else {
            throw new AssertionError("Тест завершен с ошибкой! Ожидаемое название страницы не совпадает.");
        }
    }
}