package stellar.api;

import static io.restassured.RestAssured.given;
import static stellar.Config.*;

import com.google.gson.Gson;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

public class UserClient {
    static Gson gson = new Gson();

    @Step("Регистрация пользователя")
    public ValidatableResponse create(User user) {
        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URL)
                .body(gson.toJson(user))
                .when()
                .post(REGISTER_PATH)
                .then().log().all();
    }

    @Step("Авторизация пользователя")
    public static ValidatableResponse logIn(UserCredentials user) {
        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URL)
                .body(gson.toJson(user))
                .when()
                .post(LOGIN_PATH)
                .then().log().all();
    }

    @Step("Удаление пользователя")
    public ValidatableResponse delete(String token) {
        return given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .contentType(ContentType.JSON)
                .baseUri(BASE_URL)
                .when()
                .delete(USER_PATH)
                .then().log().all();

    }
}
