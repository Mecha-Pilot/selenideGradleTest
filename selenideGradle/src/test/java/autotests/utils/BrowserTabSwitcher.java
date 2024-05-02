package autotests.utils;

import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Selenide.*;

public class BrowserTabSwitcher {
    public void switchToBrowserTab(int tabIndex){
        switchTo().window(tabIndex);
    }

    public void closeBrowserTab(){
        closeWindow();
    }

    public void switchToDefaultContent(){
        switchTo().defaultContent();
    }

}
