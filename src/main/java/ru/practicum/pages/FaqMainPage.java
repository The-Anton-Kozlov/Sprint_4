package ru.practicum.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class FaqMainPage {
    private static final By COOKIE_CONSENT_LOCATOR = By.className("App_CookieConsent__1yUIN");
    private static final By CLOSE_COOKIE_BUTTON_LOCATOR = By.className("App_CookieButton__3cvqF");
    private static final By FAQ_QUESTIONS_LOCATOR = By.cssSelector(".accordion__button");
    private static final By FAQ_RESPONSES_LOCATOR = By.className("accordion__panel");

    private final WebDriver driver;

    public FaqMainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void closeCookieConsent() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(COOKIE_CONSENT_LOCATOR));
        WebElement cookieButton = driver.findElement(CLOSE_COOKIE_BUTTON_LOCATOR);
        cookieButton.click();
    }
    public void openMainPage() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    public boolean findAnswer(String expectedText) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(FAQ_QUESTIONS_LOCATOR));

        List<WebElement> questions = driver.findElements(FAQ_QUESTIONS_LOCATOR);
        for (WebElement question : questions) {
            if (question.isDisplayed()) {
                question.click();

                List<WebElement> responses = driver.findElements(FAQ_RESPONSES_LOCATOR);
                for (WebElement response : responses) {
                    String actualResponse = response.getText();
                    if (actualResponse.contains(expectedText)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public void comparisonOfExpectedAndActualResults(String expectedText) {
        boolean actualResult = findAnswer(expectedText);
        Assert.assertTrue(
                "Результат не верен! Ожидается: " + expectedText,
                actualResult
        );
    }
}

