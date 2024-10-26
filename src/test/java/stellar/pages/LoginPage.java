package stellar.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import stellar.Config;

import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    By emailInput = By.xpath("//fieldset[1]/div/div/input");
    By passwordInput = By.xpath("//fieldset[2]/div/div/input");

    By loginButton = By.className("button_button__33qZ0");

    By loginTitle = By.xpath("//h2[contains(text(),'Вход')]");

    public LoginPage enterEmail(String email) {
        driver.findElement(emailInput).sendKeys(email);
        return this;
    }

    public LoginPage enterPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
        return this;
    }

    public LoginPage clickOnLoginButton() {
        driver.findElement(loginButton).click();
        return this;
    }

    public LoginPage checkLoginLoaded() {
        new WebDriverWait(driver, Duration.ofSeconds(Config.EXPLICIT_WAIT))
                .until(ExpectedConditions.presenceOfElementLocated(loginTitle));
        return this;
    }
}
