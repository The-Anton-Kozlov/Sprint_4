package ru.practicum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HomePage {

    private static final By COOKIE_CONSENT_BUTTON_LOCATOR = By.className("App_CookieButton__3cvqF");
    private static final By MAIN_ORDER_BUTTON_LOCATOR = By.className("Button_Button__ra12g");
    private static final By ALTERNATIVE_ORDER_BUTTON_LOCATOR = By.cssSelector(".Button_Button__ra12g.Button_Middle__1CSJM");

    private final WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void closeCookieConsent() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(COOKIE_CONSENT_BUTTON_LOCATOR)).click();
    }

    public void openMainPage() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    public void selectAndClickOrderButton(int testNumber) {
        By buttonLocator = testNumber == 0 ? MAIN_ORDER_BUTTON_LOCATOR : ALTERNATIVE_ORDER_BUTTON_LOCATOR;

        if (buttonLocator.equals(MAIN_ORDER_BUTTON_LOCATOR)) {
            System.out.println("Нажата основная кнопка Заказать.");
        } else if (buttonLocator.equals(ALTERNATIVE_ORDER_BUTTON_LOCATOR)) {
            System.out.println("Нажата альтернативная кнопка Заказать.");
        }
        driver.findElement(buttonLocator).click();
    }
}