package stellar.pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import stellar.Config;

public class RegisterPage {
    private final WebDriver driver;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    By nameInput = By.xpath("//fieldset[1]/div/div/input");
    By emailInput = By.xpath("//fieldset[2]/div/div/input");
    By passwordInput = By.xpath("//fieldset[3]/div/div/input");
    By passwordErrorMessage = By.xpath("//fieldset[3]/div/p");
    By registerButton = By.className("button_button__33qZ0");

    By enterButton = By.className("Auth_link__1fOlj");

    @Step("Переход на страницу регистрации")
    public RegisterPage open() {
        driver.get(Config.REGISTER_URL);
        return this;
    }

    @Step("Водд имени в поле регистрации")
    public RegisterPage enterName(String name) {
        driver.findElement(nameInput).sendKeys(name);
        return this;
    }

    @Step("Водд почты в поле регистрации")
    public RegisterPage enterEmail(String email) {
        driver.findElement(emailInput).sendKeys(email);
        return this;
    }

    @Step("Водд пароля в поле регистрации")
    public RegisterPage enterPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
        return this;
    }

    @Step("Нажатие на кнопку регистрации")
    public RegisterPage clickRegisterButton() {
        driver.findElement(registerButton).click();
        return this;
    }

    @Step("Проверка отображения сообщения о некоректном пароле")
    public RegisterPage checkErrorMessageIsVisible() {
        Assert.assertTrue(driver.findElement(passwordErrorMessage).isDisplayed());
        return this;
    }

    @Step("Нажатие на кнопку 'Вход'")
    public RegisterPage clickEnterButton() {
        driver.findElement(enterButton).click();
        return this;
    }
}
