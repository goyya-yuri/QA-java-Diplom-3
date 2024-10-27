package stellar;

public class Config {
    public static String BASE_URL = "https://stellarburgers.nomoreparties.site/";
    public static String REGISTER_URL = "https://stellarburgers.nomoreparties.site/register";
    public static String FORGOT_URL = "https://stellarburgers.nomoreparties.site/forgot-password";

    public static final String REGISTER_PATH = "api/auth/register";
    public static final String LOGIN_PATH = "api/auth/login";
    public static final String USER_PATH = "api/auth/user";

    public static int IMPLICIT_WAIT = 5;
    public static int EXPLICIT_WAIT = 5;

    public static int NAME_LENGTH = 8;
    public static int PASSWORD_LENGTH = 12;
    public static int INVALID_PASSWORD_LENGTH = 5;
}
