package stellar.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import stellar.Config;

public class ForgotPasswordPage {
    private final WebDriver driver;

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    By enterButton = By.className("Auth_link__1fOlj");

    @Step("Переход на страницу востановления пароля")
    public ForgotPasswordPage open() {
        driver.get(Config.FORGOT_URL);
        return this;
    }

    @Step("Нажатие на кнопку 'Войти'")
    public ForgotPasswordPage clickEnterButton() {
        driver.findElement(enterButton).click();
        return this;
    }
}
