package stellar.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import stellar.Config;

public class ForgotPasswordPage {
    private final WebDriver driver;

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    By enterButton = By.className("Auth_link__1fOlj");

    public ForgotPasswordPage open() {
        driver.get(Config.FORGOT_URL);
        return this;
    }

    public ForgotPasswordPage clickEnterButton() {
        driver.findElement(enterButton).click();
        return this;
    }
}
