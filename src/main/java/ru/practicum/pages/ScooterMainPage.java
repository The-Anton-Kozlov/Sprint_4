package ru.practicum.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import java.util.List;
import java.util.Random;

public class ScooterMainPage {

    private static final By NAME_INPUT_FIELD_LOCATOR = By.cssSelector("input[placeholder='* Имя']");
    private static final By SURNAME_INPUT_FIELD_LOCATOR = By.cssSelector("input[placeholder='* Фамилия']");
    private static final By ADDRESS_INPUT_FIELD_LOCATOR = By.cssSelector("input[placeholder='* Адрес: куда привезти заказ']");
    private static final By METRO_STATION_SELECTOR_LOCATOR = By.className("select-search__input");
    private static final By METRO_STATION_OPTION_LOCATOR = By.className("select-search__select");
    private static final By PHONE_INPUT_FIELD_LOCATOR = By.cssSelector("input[placeholder='* Телефон: на него позвонит курьер']");

    private static final By NEXT_BUTTON_LOCATOR = By.cssSelector(".Button_Button__ra12g.Button_Middle__1CSJM");

    private static final By DATE_PICKER_LOCATOR = By.className("react-datepicker__input-container");
    private static final By DROPDOWN_CONTROL_LOCATOR = By.className("Dropdown-control");
    private static final By DROPDOWN_OPTIONS_LOCATOR = By.className("Dropdown-option");
    private static final By BLACK_SCOOTER_COLOR_LOCATOR = By.xpath("//label[@for='black']");
    private static final By GREY_SCOOTER_COLOR_LOCATOR = By.xpath("//label[@for='grey']");
    private static final By COMMENT_INPUT_FIELD_LOCATOR = By.cssSelector(".Input_Input__1iN_Z.Input_Responsible__1jDKN[placeholder='Комментарий для курьера']");

    private static final By SCOOTER_ORDER_BUTTON_LOCATOR = By.cssSelector(".Button_Button__ra12g.Button_Middle__1CSJM:not(.Button_Inverted__3IF-i)");
    private static final By ORDER_CONFIRMATION_BUTTON_LOCATOR = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and contains(text(), 'Да')]");
    private static final By MODAL_WINDOW_LOCATOR = By.cssSelector(".Order_Modal__YZ-d3");

    private final WebDriver driver;

    public ScooterMainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillOrderForm(PersonData data) {

        driver.findElement(NAME_INPUT_FIELD_LOCATOR).sendKeys(data.getName());
        driver.findElement(SURNAME_INPUT_FIELD_LOCATOR).sendKeys(data.getSurname());
        driver.findElement(ADDRESS_INPUT_FIELD_LOCATOR).sendKeys(data.getAddress());
        driver.findElement(METRO_STATION_SELECTOR_LOCATOR).sendKeys(data.getMetroStation());
        driver.findElement(METRO_STATION_OPTION_LOCATOR).click();
        driver.findElement(PHONE_INPUT_FIELD_LOCATOR).sendKeys(data.getPhone());
    }

    public void clickNextButton() {
        driver.findElement(NEXT_BUTTON_LOCATOR).click();
    }

    public void fillSecondPartOfForm(OrderData order) {
        Actions actions = new Actions(driver);

        driver.findElement(DATE_PICKER_LOCATOR).click();
        WebElement dateInputContainer = driver.findElement(DATE_PICKER_LOCATOR);
        dateInputContainer.click();

        WebElement inputField = dateInputContainer.findElement(By.tagName("input"));
        inputField.sendKeys(order.getDeliveryDate());
        actions.sendKeys(Keys.ESCAPE).perform();

        driver.findElement(DROPDOWN_CONTROL_LOCATOR).click();
        List<WebElement> elements = driver.findElements(DROPDOWN_OPTIONS_LOCATOR);
        Random random = new Random();
        int randomIndex = random.nextInt(elements.size());
        WebElement randomElement = elements.get(randomIndex);
        randomElement.click();

        WebElement colorOption = null;
        switch (order.getScooterColour()) {
            case "чёрный жемчуг":
                colorOption = driver.findElement(BLACK_SCOOTER_COLOR_LOCATOR);
                break;
            case "серая безысходность":
                colorOption = driver.findElement(GREY_SCOOTER_COLOR_LOCATOR);
                break;
            default:
                throw new IllegalArgumentException("Неподдерживаемый цвет: " + order.getScooterColour());
        }
        colorOption.click();

        driver.findElement(COMMENT_INPUT_FIELD_LOCATOR).sendKeys(order.getComment());
    }

    public void clickOrderConfirmation() {
        driver.findElement(SCOOTER_ORDER_BUTTON_LOCATOR).click();
    }

    public void clicksOrderScooter() {
        WebElement element = driver.findElement(ORDER_CONFIRMATION_BUTTON_LOCATOR);
        element.click();
    }


    public void checkError() {
        WebElement modalWindow = driver.findElement(MODAL_WINDOW_LOCATOR);
        String fullModalText = modalWindow.getText().trim();
        String expectedTitle = "Заказ оформлен";
        Assert.assertTrue("Ошибка: ожидалось наличие текста '" + expectedTitle + "'", fullModalText.contains(expectedTitle));
    }
}