package stellar.pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import stellar.Config;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class MainPage {
    private final WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    By cabinetButton = By.xpath("//a[@href='/account']");
    By enterButton = By.className("button_button__33qZ0");
    By constructorTitle = By.xpath("//h1[contains(text(),'Соберите бургер')]");

    By[] constructorTabs = {By.xpath("//section[@class='BurgerIngredients_ingredients__1N8v2']/div/div[1]"),
            By.xpath("//section[@class='BurgerIngredients_ingredients__1N8v2']/div/div[2]"),
            By.xpath("//section[@class='BurgerIngredients_ingredients__1N8v2']/div/div[3]")};

    By selectedTab = By.xpath("//div[contains(@class, 'tab_tab_type_current__2BEPc')]");

    @Step("Переход на главную страницу")
    public MainPage open() {
        driver.get(Config.BASE_URL);
        return this;
    }

    @Step("Нажатие на кнопку 'Войти'")
    public MainPage clickEnterButton() {
        driver.findElement(enterButton).click();
        return this;
    }

    @Step("Нажатие на кнопку 'Личный кабинет'")
    public MainPage clickCabinetButton() {
        driver.findElement(cabinetButton).click();
        return this;
    }

    @Step("Проверка на успешность авторизации пользователя")
    public MainPage checkUserLoggedIn() {
        new WebDriverWait(driver, Duration.ofSeconds(Config.EXPLICIT_WAIT))
                .until(ExpectedConditions.textToBePresentInElementLocated(enterButton, "Оформить заказ"));
        return this;
    }

    @Step("Проверка загрузки страницы")
    public MainPage checkConstructorLoaded() {
        new WebDriverWait(driver, Duration.ofSeconds(Config.EXPLICIT_WAIT))
                .until(ExpectedConditions.presenceOfElementLocated(constructorTitle));
        return this;
    }

    @Step("Смена вкладки")
    public MainPage swichTab(int tabIndex) {
        driver.findElement(constructorTabs[tabIndex]).click();
        return this;
    }

    @Step("Сверка открытой вкладки")
    public MainPage checkTab(int tabIndex) {
        Assert.assertEquals(driver.findElement(constructorTabs[tabIndex]), driver.findElement(selectedTab));
        return this;
    }
}
