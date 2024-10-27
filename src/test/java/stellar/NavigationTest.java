package stellar;

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.*;
import stellar.api.User;
import stellar.api.UserChecks;
import stellar.api.UserClient;
import stellar.pages.CabinetPage;
import stellar.pages.LoginPage;
import stellar.pages.MainPage;

public class NavigationTest {

    static User user;

    private static final UserClient client = new UserClient();
    private static final UserChecks check = new UserChecks();

    private static String accessToken;

    @ClassRule
    public static DriverFactory factory = new DriverFactory();

    @Before
    @Step("Создание пользователя")
    public void createUser(){
        user = User.random();
        ValidatableResponse response = client.create(user);
        accessToken = check.checkCreated(response);
        new MainPage(factory.getDriver())
                .open()
                .clickEnterButton();
        new LoginPage(factory.getDriver())
                .enterEmail(user.getEmail())
                .enterPassword(user.getPassword())
                .clickOnLoginButton();
    }

    @After
    @Step("Удаление пользователя")
    public void removeUser(){
        if(accessToken == null) return;
        ValidatableResponse deleteResponse = client.delete(accessToken);
        check.checkDeleted(deleteResponse);
    }

    @Test
    @DisplayName("Переход в личный кабинет")
    public void toCabinetTest(){
        new MainPage(factory.getDriver())
                .clickCabinetButton();
        new CabinetPage(factory.getDriver())
                .checkCabinetLoaded();
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор")
    public void toConstructorPageFromCabinetTest(){
        new MainPage(factory.getDriver())
                .clickCabinetButton();
        new CabinetPage(factory.getDriver())
                .clickConstructorButton();
        new MainPage(factory.getDriver())
                .checkConstructorLoaded();
    }

    @Test
    @DisplayName("Выход из аккаунта")
    public void exitFromAccountTest(){
        new MainPage(factory.getDriver())
                .clickCabinetButton();
        new CabinetPage(factory.getDriver())
                .clickExitButton();
        new LoginPage(factory.getDriver())
                .checkLoginLoaded();
    }

    @Test
    @DisplayName("Выбор категорий ингредиентов")
    public void switchTabsTest(){
        new MainPage(factory.getDriver())
                .swichTab(1)
                .checkTab(1)
                .swichTab(2)
                .checkTab(2)
                .swichTab(0)
                .checkTab(0);
    }

}
