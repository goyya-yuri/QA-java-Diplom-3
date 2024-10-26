package stellar;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.ClassRule;
import org.junit.Test;
import stellar.api.User;
import stellar.api.UserChecks;
import stellar.api.UserClient;
import stellar.api.UserCredentials;
import stellar.pages.RegisterPage;

import static stellar.Config.INVALID_PASSWORD_LENGTH;

public class RegisterTest {
    private final UserClient client = new UserClient();
    private final UserChecks check = new UserChecks();

    private String accessToken;

    @ClassRule
    public static DriverFactory factory = new DriverFactory();

    @After
    @DisplayName("Удаление пользователя")
    public void removeUser(){
        if(accessToken == null) return;
        ValidatableResponse deleteResponse = client.delete(accessToken);
        check.checkDeleted(deleteResponse);
    }

    @Test
    @DisplayName("Регистрация пользователя")
    public void userRegisterTest(){
        User user = User.random();
        new RegisterPage(factory.getDriver())
                .open()
                .enterName(user.getName())
                .enterEmail(user.getEmail())
                .enterPassword(user.getPassword())
                .clickRegisterButton();
        ValidatableResponse response = UserClient.logIn(UserCredentials.fromUser(user));
        accessToken =  UserChecks.checkAuth(response);
    }
    @Test
    @DisplayName("Регистрация пользователя с коротким паролем")
    public void userRegisterWithShortPasswordTest(){
        User user = User.random();
        user.newPassword(INVALID_PASSWORD_LENGTH);
        new RegisterPage(factory.getDriver())
                .open()
                .enterName(user.getName())
                .enterEmail(user.getEmail())
                .enterPassword(user.getPassword())
                .clickRegisterButton()
                .checkErrorMessageIsVisible();
    }
}
