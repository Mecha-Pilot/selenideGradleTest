package autotests.prestaShop.pages;

import autotests.prestaShop.utils.IFrameSwitcher;
import autotests.utils.Randomizer;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.sleep;

public class CreateAnAccount {
    Randomizer randomizer = new Randomizer();
    private IFrameSwitcher iFrameSwitcher = new IFrameSwitcher();

    //    page elements/Paths: fields:
    private final By firstNameFieldPath = By.xpath("//input[@id='field-firstname']");
    private final By lastNameFieldPath = By.xpath("//input[@id='field-lastname']");
    private final By emailFieldPath = By.xpath("//input[@id='field-email']"); //value must be unique ; first account: aaaaa@gmail.com
    private final By passwordFieldPath = By.xpath("//input[@id='field-password']"); // legit password example: aesq1234!

    //    checkboxes:
    private final By termsAndConditionsCheckboxPath = By.xpath("//input[@name='psgdpr']");
    private final By customerDataPrivacyCheckboxPath = By.xpath("//input[@name='customer_privacy']");

    //    buttons:
    private final By saveButtonPath = By.xpath("//button[@type='submit']");

    public void prepareAndFillMandatoryAccountData() {
        iFrameSwitcher.checkIfIFrameFrameliveExistsAndSwitchToIt();

        $(firstNameFieldPath).shouldBe(visible, Duration.ofSeconds(10)).setValue("aaaaa");
        $(lastNameFieldPath).shouldBe(visible).setValue("aaaaa");
        $(emailFieldPath).shouldBe(visible).setValue(randomizer.generatingEmailAddress(10));
        $(passwordFieldPath).shouldBe(visible).setValue("aesq1234!");
        $(termsAndConditionsCheckboxPath).shouldBe(clickable, Duration.ofSeconds(10)).click();
        $(customerDataPrivacyCheckboxPath).shouldBe(clickable, Duration.ofSeconds(10)).click();

        iFrameSwitcher.switchToDefaultContent();
    }

    public void saveAccountDataAndRegisterAnAccount() {
        iFrameSwitcher.checkIfIFrameFrameliveExistsAndSwitchToIt();

        $(saveButtonPath).shouldBe(visible, Duration.ofSeconds(10)).click();

        iFrameSwitcher.switchToDefaultContent();
    }
}
