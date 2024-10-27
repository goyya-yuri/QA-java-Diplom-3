package stellar.api;
import org.apache.commons.lang3.RandomStringUtils;

import static stellar.Config.NAME_LENGTH;
import static stellar.Config.PASSWORD_LENGTH;

public class User {
    private String email;
    private String password;
    private String name;

    public User(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public User(){}

    public static User random() {
        return new User(RandomStringUtils.randomAlphabetic(NAME_LENGTH) +"@random.com",
                RandomStringUtils.randomAlphabetic(PASSWORD_LENGTH),
                RandomStringUtils.randomAlphabetic(NAME_LENGTH));
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void newPassword(int shortPasswordLength) {
        this.password = RandomStringUtils.randomAlphabetic(shortPasswordLength);
    }
}
