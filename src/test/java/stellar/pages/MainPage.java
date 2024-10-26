package stellar.pages;

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

    public MainPage open() {
        driver.get(Config.BASE_URL);
        return this;
    }

    public MainPage clickEnterButton() {
        driver.findElement(enterButton).click();
        return this;
    }

    public MainPage clickCabinetButton() {
        driver.findElement(cabinetButton).click();
        return this;
    }

    public MainPage checkUserLoggedIn() {
        new WebDriverWait(driver, Duration.ofSeconds(Config.EXPLICIT_WAIT))
                .until(ExpectedConditions.textToBePresentInElementLocated(enterButton, "Оформить заказ"));
        return this;
    }

    public MainPage checkConstructorLoaded() {
        new WebDriverWait(driver, Duration.ofSeconds(Config.EXPLICIT_WAIT))
                .until(ExpectedConditions.presenceOfElementLocated(constructorTitle));
        return this;
    }

    public MainPage swichTab(int i) {
        driver.findElement(constructorTabs[i]).click();
        return this;
    }

    public MainPage checkTab(int i) {
        Assert.assertEquals(driver.findElement(constructorTabs[i]), driver.findElement(selectedTab));
        return this;
    }
}
