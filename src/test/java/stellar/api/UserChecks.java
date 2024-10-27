package stellar.api;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;

import static java.net.HttpURLConnection.HTTP_ACCEPTED;
import static java.net.HttpURLConnection.HTTP_OK;

public class UserChecks {

    @Step("Успешная регистрация пользователя")
    public String checkCreated(ValidatableResponse response) {
        String accessToken = response
                .assertThat()
                .statusCode(HTTP_OK)
                .extract()
                .path("accessToken");
        Assert.assertNotNull(accessToken);
        return accessToken;
    }

    @Step("Успешная авторизация пользователя")
    public static String checkAuth(ValidatableResponse response) {
        String accessToken = response
                .assertThat()
                .statusCode(HTTP_OK)
                .extract()
                .path("accessToken");
        Assert.assertNotNull(accessToken);
        return accessToken;
    }

    @Step("Успешное удаление пользователя")
    public void checkDeleted(ValidatableResponse response) {
        boolean removed = response
                .assertThat()
                .statusCode(HTTP_ACCEPTED)
                .extract()
                .path("success");
        Assert.assertTrue(removed);
    }
}
