package stellar.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import stellar.Config;

import java.time.Duration;

public class CabinetPage {
    private final WebDriver driver;

    public CabinetPage(WebDriver driver) {
        this.driver = driver;
    }

    By constructorButton = By.xpath("//li/a[@href='/']");
    By exitButton = By.xpath("//button[contains(text(),'Выход')]");
    By profileButton = By.xpath("//button[contains(text(),'Выход')]");

    @Step("Нажатие на кнопку перехода в конструктор")
    public CabinetPage clickConstructorButton() {
        driver.findElement(constructorButton).click();
        return this;
    }

    @Step("Нажатие на кнопку выхода")
    public CabinetPage clickExitButton() {
        driver.findElement(exitButton).click();
        return this;
    }

    @Step("Проверка загрузки страницы, личного кабинета")
    public CabinetPage checkCabinetLoaded() {
        new WebDriverWait(driver, Duration.ofSeconds(Config.EXPLICIT_WAIT))
                .until(ExpectedConditions.presenceOfElementLocated(profileButton));
        return this;
    }
}
