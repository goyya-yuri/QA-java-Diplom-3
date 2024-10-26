package stellar;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class DriverFactory extends ExternalResource {
    private WebDriver driver;

    public WebDriver getDriver(){
        return driver;
    }

    @Override
    protected void before(){
        initDriver();
    }
    @Override
    protected void after() {
        driver.quit();
    }

    public void initDriver(){
       if("yandex".equals(System.getProperty("browser"))){
            startYandex();
       }else{
            startChrome();
       }
    }

    public void startYandex(){
        WebDriverManager.chromedriver().driverVersion(System.getProperty("driver.version")).setup();
        var options = new ChromeOptions();
        options.setBinary(System.getProperty("webdriver.yandex.bin"));
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Config.IMPLICIT_WAIT));
    }

    public void startChrome(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Config.IMPLICIT_WAIT));
    }
}
