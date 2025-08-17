package ru.practicum;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import ru.practicum.pages.OrderData;
import ru.practicum.pages.PersonData;
import ru.practicum.pages.ScooterMainPage;

import java.util.Arrays;
import java.util.Collection;


@RunWith(Parameterized.class)
public class ScooterRentalOrderTest {

    @Rule
    public DriverFactory factory = new DriverFactory();

    @Parameterized.Parameters(name = "{index}: Test with {0}, {1}, using button #{2}")
    public static Collection<Object[]> dataProvider() {
        return Arrays.asList(new Object[][]{
                {new PersonData("Иван", "Иванов", "Москва, Ленинский проспект, д. 62", "Аэропорт", "+71111111111"),
                        new OrderData("04.09.2025", "чёрный жемчуг", ""),
                        0}, // Первую кнопку нажмем первым набором данных
                {new PersonData("Петр", "Петров", "Москва, улица Бажова, дом 30", "Ростокино", "89999999999"),
                        new OrderData("10.09.2025", "серая безысходность", "Нужен быстрый самокат!"),
                        1} // Вторую кнопку нажмем вторым набором данных
        });
    }

    private final PersonData data;
    private final OrderData orderData;
    private final int testNumber; // Индекс теста

    public ScooterRentalOrderTest(PersonData data, OrderData order, int testNum) {
        this.data = data;
        this.orderData = order;
        this.testNumber = testNum;
    }

    @Test
    public void FillingOrderForm() {
        WebDriver driver = factory.getDriver();
        var mainPage = new ScooterMainPage(driver);

        mainPage.openMainPage();
        mainPage.closeCookieConsent();
        mainPage.SelectingAndClickingOnTheOrderButton(testNumber);
        mainPage.fillOrderForm(data);
        mainPage.clickNextButton();
        mainPage.fillSecondPartOfForm(orderData);
        mainPage.clicksOrderScooter();
        mainPage.clickOrderConfirmation();
        mainPage.checkError();
    }
}


