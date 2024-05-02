package autotests.prestaShop.pages;

import autotests.prestaShop.utils.IFrameSwitcher;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Condition.*;

public class HomePage {
    private IFrameSwitcher iFrameSwitcher = new IFrameSwitcher();

//    page elements/Paths: buttons:
    private final By createAccountButtonPath = By.xpath("//a[@title='Create account']");
    private final By signOutButtonPath = By.xpath("//a[@class='logout hidden-sm-down']");
    private final By accessoriesButtonPath = By.xpath("//li[@id='category-6']");


    public void openBrowserAndWebSitePage(String url){
        Selenide.open(url);
    }

    public void goToCreateAccountPage(){
        iFrameSwitcher.checkIfIFrameFrameliveExistsAndSwitchToIt();

        $(createAccountButtonPath).shouldBe(visible, Duration.ofSeconds(20)).click();

        iFrameSwitcher.switchToDefaultContent();
    }

    public void goToAccessoriesPage(){
        iFrameSwitcher.checkIfIFrameFrameliveExistsAndSwitchToIt();

        $(accessoriesButtonPath).shouldBe(visible, Duration.ofSeconds(5)).click();

        iFrameSwitcher.switchToDefaultContent();
    }

    public void checkThatLoggedInSuccessfully(){
        iFrameSwitcher.checkIfIFrameFrameliveExistsAndSwitchToIt();

        $(signOutButtonPath).shouldBe(visible, Duration.ofSeconds(20));

        iFrameSwitcher.switchToDefaultContent();
    }
}
