package autotests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

abstract public class BaseTest {
    public void setUp(){
        System.out.println("Setting up a test environment \n");
        WebDriverManager.chromedriver().setup();
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.headless = false;
        System.out.println("Test environment setup is complete");
    }

    @BeforeTest
    public void init(){
        setUp();
    }

    @AfterTest
    public void teardown(){
        Selenide.closeWebDriver();
    }
}
