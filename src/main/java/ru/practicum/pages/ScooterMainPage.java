package ru.practicum.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import java.util.Random;

public class ScooterMainPage {
    private final WebDriver driver;

    public ScooterMainPage(WebDriver driver) {
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

    public void SelectingAndClickingOnTheOrderButton(int testNumber) {
        By buttonLocator = testNumber == 0 ? By.className("Button_Button__ra12g") : By.cssSelector(".Button_Button__ra12g.Button_Middle__1CSJM");
        if (buttonLocator.equals(By.className("Button_Button__ra12g"))) {
            System.out.println("Нажата основная кнопка Заказать.");
        } else if (buttonLocator.equals(By.cssSelector(".Button_Button__ra12g.Button_Middle__1CSJM"))) {
            System.out.println("Нажата альтернативная кнопка Заказать.");
        }
        driver.findElement(buttonLocator).click();
    }

    public  void fillOrderForm( PersonData data) {
        driver.findElement(By.cssSelector("input[placeholder='* Имя']")).sendKeys(data.getName());
        driver.findElement(By.cssSelector("input[placeholder='* Фамилия']")).sendKeys(data.getSurname());
        driver.findElement(By.cssSelector("input[placeholder='* Адрес: куда привезти заказ']")).sendKeys(data.getAddress());
        driver.findElement(By.cssSelector(".select-search__input")).sendKeys(data.getMetroStation());
        driver.findElement(By.className("select-search__select")).click();
        driver.findElement(By.cssSelector("input[placeholder='* Телефон: на него позвонит курьер']")).sendKeys(data.getPhone());
    }

    public void clickNextButton() {
        driver.findElement(By.cssSelector(".Button_Button__ra12g.Button_Middle__1CSJM")).click();
    }

    public void fillSecondPartOfForm(OrderData order) {
        Actions actions = new Actions(driver);

        driver.findElement(By.className("react-datepicker__input-container")).click();
        WebElement dateInputContainer = driver.findElement(By.className("react-datepicker__input-container"));
        dateInputContainer.click();

        WebElement inputField = dateInputContainer.findElement(By.tagName("input"));
        inputField.sendKeys(order.getDeliveryDate());
        actions.sendKeys(Keys.ESCAPE).perform();

        driver.findElement(By.className("Dropdown-control")).click();
        List<WebElement> elements = driver.findElements(By.className("Dropdown-option"));
        Random random = new Random();
        int randomIndex = random.nextInt(elements.size());
        WebElement randomElement = elements.get(randomIndex);
        randomElement.click();
        WebElement colorOption = null;
        switch (order.getScooterColour()) {
            case "чёрный жемчуг":
                colorOption = driver.findElement(By.xpath("//label[@for='black']"));
                break;
            case "серая безысходность":
                colorOption = driver.findElement(By.xpath("//label[@for='grey']"));
                break;
            default:
                throw new IllegalArgumentException("Неподдерживаемый цвет: " + order.getScooterColour());
        }
        colorOption.click();

        driver.findElement(By.cssSelector(".Input_Input__1iN_Z.Input_Responsible__1jDKN[placeholder='Комментарий для курьера']")).sendKeys(order.getComment());
    }

    public  void clickOrderConfirmation() {
        WebElement element = driver.findElement(By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and contains(text(), 'Да')]"));
        element.click();
    }

    public void clicksOrderScooter() {
        driver.findElement(By.cssSelector(".Button_Button__ra12g.Button_Middle__1CSJM:not(.Button_Inverted__3IF-i)")).click();
    }

    public   void checkError() {
        WebElement modalWindow = driver.findElement(By.cssSelector(".Order_Modal__YZ-d3"));
        String fullModalText = modalWindow.getText().trim();
        String expectedTitle = "Заказ оформлен";
        Assert.assertTrue("Ожидался заголовок '" + expectedTitle + "', но не найден.", fullModalText.contains(expectedTitle));
    }
}

