package autotests.prestaShop.utils;

import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class IFrameSwitcher {
    //    page elements/Paths: iFrame:
    final By iFramePath = By.id("framelive");

    public void checkIfIFrameFrameliveExistsAndSwitchToIt(){
        if ($(iFramePath).exists()){
            Selenide.switchTo().frame($(iFramePath));
        }
    }

    public void switchToDefaultContent(){
            Selenide.switchTo().defaultContent();
    }

}
