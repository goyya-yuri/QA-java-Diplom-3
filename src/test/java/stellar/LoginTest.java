package stellar;

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.*;
import stellar.api.User;
import stellar.api.UserChecks;
import stellar.api.UserClient;
import stellar.pages.ForgotPasswordPage;
import stellar.pages.LoginPage;
import stellar.pages.MainPage;
import stellar.pages.RegisterPage;

public class LoginTest {
    static User user;

    private final UserClient client = new UserClient();
    private final UserChecks check = new UserChecks();

    private static String accessToken;

    @ClassRule
    public static DriverFactory factory = new DriverFactory();

    @Before
    @Step("Создание пользователя")
    public void createUser(){
        user = User.random();
        ValidatableResponse response = client.create(user);
        accessToken = check.checkCreated(response);
    }

    @After
    @Step("Удаление пользователя")
    public void removeUser(){
        if(accessToken == null) return;
        ValidatableResponse deleteResponse = client.delete(accessToken);
        check.checkDeleted(deleteResponse);
    }

    @Test
    @DisplayName("Авторизация пользователя по кнопке «Войти в аккаунт» на главной")
    public void loginFromHomeTest(){
        new MainPage(factory.getDriver())
                .open()
                .clickEnterButton();
        new LoginPage(factory.getDriver())
                .enterEmail(user.getEmail())
                .enterPassword(user.getPassword())
                .clickOnLoginButton();
        new MainPage(factory.getDriver())
                .checkUserLoggedIn();
    }
    @Test
    @DisplayName("Авторизация пользователя через кнопку «Личный кабинет»")
    public void loginFromCabinetTest(){
        new MainPage(factory.getDriver())
                .open()
                .clickCabinetButton();
        new LoginPage(factory.getDriver())
                .enterEmail(user.getEmail())
                .enterPassword(user.getPassword())
                .clickOnLoginButton();
        new MainPage(factory.getDriver())
                .checkUserLoggedIn();
    }
    @Test
    @DisplayName("Авторизация пользователя через кнопку в форме регистрации")
    public void loginFromRegisterFormTest(){
        new RegisterPage(factory.getDriver())
                .open()
                .clickEnterButton();
        new LoginPage(factory.getDriver())
                .enterEmail(user.getEmail())
                .enterPassword(user.getPassword())
                .clickOnLoginButton();
        new MainPage(factory.getDriver())
                .checkUserLoggedIn();
    }
    @Test
    @DisplayName("Авторизация пользователя через кнопку в форме восстановления пароля")
    public void loginFromForgotPasswordFormTest(){
        new ForgotPasswordPage(factory.getDriver())
                .open()
                .clickEnterButton();
        new LoginPage(factory.getDriver())
                .enterEmail(user.getEmail())
                .enterPassword(user.getPassword())
                .clickOnLoginButton();
        new MainPage(factory.getDriver())
                .checkUserLoggedIn();
    }
}
