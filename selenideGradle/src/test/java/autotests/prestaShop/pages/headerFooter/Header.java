package autotests.prestaShop.pages.headerFooter;

import autotests.prestaShop.utils.IFrameSwitcher;
import org.openqa.selenium.By;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.*;

public class Header {
    private final IFrameSwitcher iFrameSwitcher = new IFrameSwitcher();
    //    page elements/Paths: buttons:
    private final By cartActiveButtonPath = By.xpath("//div[@class='blockcart cart-preview active']"); // path to the active(*not empty) cart button
    private final By userInfoButtonPath = By.xpath("//div[@class='user-info']");
    private final By signOutButtonPath = By.xpath("//a[@class='logout hidden-sm-down']");

    public void goToCartIfItIsNotEmpty() {
        iFrameSwitcher.checkIfIFrameFrameliveExistsAndSwitchToIt();

        $(cartActiveButtonPath).click();

        iFrameSwitcher.switchToDefaultContent();
    }

    public void signOutFromDemoPrestashop() {
        iFrameSwitcher.checkIfIFrameFrameliveExistsAndSwitchToIt();

        $(signOutButtonPath).click();

        iFrameSwitcher.switchToDefaultContent();
    }

    public void makingSureThatTheUserIsNotLoggedIn() {
        iFrameSwitcher.checkIfIFrameFrameliveExistsAndSwitchToIt();

        String userInfoText = $(userInfoButtonPath).findElement(By.className("hidden-sm-down")).getText();
        Assert.assertEquals(userInfoText, "Sign in");

        iFrameSwitcher.switchToDefaultContent();
    }
}
