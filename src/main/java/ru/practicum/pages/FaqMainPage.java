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
    private final WebDriver driver;

    public FaqMainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void closeCookieConsent() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("App_CookieConsent__1yUIN")));
        WebElement button = driver.findElement(By.className("App_CookieButton__3cvqF"));
        button.click();
    }
    public void openMainPage() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    public boolean findAnswer(String expectedText) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".accordion__button")));

        List<WebElement> elements = driver.findElements(By.cssSelector(".accordion__button"));
        for (WebElement element : elements) {
            if (element.isDisplayed()) {
                element.click();

                List<WebElement> responseTexts = driver.findElements(By.className("accordion__panel"));
                for (WebElement responseText : responseTexts) {
                    String actualResponse = responseText.getText();
                    if (actualResponse.contains(expectedText)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public void ComparisonOfExpectedAndActualResults(String expectedText) {
        boolean actualResult = findAnswer(expectedText);
        Assert.assertTrue(
                "Результат не верен! Ожидается: " + expectedText,
                actualResult
        );
    }
}

