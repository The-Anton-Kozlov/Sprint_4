package ru.practicum;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import ru.practicum.pages.FaqMainPage;

@RunWith(Parameterized.class)
public class FaqWebTest {
    @Rule
    public DriverFactory factory = new DriverFactory();
    private String expectedText;
    private boolean expectedResult;


    @Parameterized.Parameters(name = "Тестовые данные: {index}: '{0}' => {1}")
    public static Object[][] getTestData() {
        return new Object[][]{
                {"Сутки — 400 рублей. Оплата курьеру — наличными или картой.", true},
                {"Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.", true},
                {"Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.", true},
                {"Только начиная с завтрашнего дня. Но скоро станем расторопнее.", true},
                {"Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.", true},
                {"Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.", true},
                {"Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.", true},
                {"Да, обязательно. Всем самокатов! И Москве, и Московской области.", true}
        };
    }
    public FaqWebTest(String expectedText, boolean expectedResult) {
        this.expectedText = expectedText;
        this.expectedResult = expectedResult;
    }
    @Test
    public void testDropdown(){
        WebDriver driver = factory.getDriver();
        var mainPage = new FaqMainPage(driver);

        mainPage.openMainPage();
        mainPage.closeCookieConsent();
        mainPage.comparisonOfExpectedAndActualResults(expectedText);
    }
}

