package autotests.prestaShop.pages;

import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

/**
 * looks like logIn functionality doesn't work properly on demo.prestashop.com. Existing account info is erased after leaving the site. Do not use this class.
 */

public class LogIn {
//    page elements/Paths: fields:
    private final By emailFieldPath = By.xpath("//input[@id='field-email']");
    private final By passwordFieldPath = By.xpath("//input[@id='field-password']");

    //    buttons:
    private final By signInButtonPath = By.xpath("//button[@id='submit-login']");

    public void signInWithDefaultAccount() {
        $(emailFieldPath).shouldBe(visible, Duration.ofSeconds(10)).setValue("aaaaa@gmail.com");
        $(passwordFieldPath).shouldBe(visible).setValue("weqrt123!");
        $(signInButtonPath).shouldBe(visible, Duration.ofSeconds(10)).click();
    }
}
