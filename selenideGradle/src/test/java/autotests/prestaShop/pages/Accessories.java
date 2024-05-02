package autotests.prestaShop.pages;

import autotests.prestaShop.utils.IFrameSwitcher;
import com.codeborne.selenide.*;
import org.openqa.selenium.By;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;


public class Accessories {
    IFrameSwitcher iFrameSwitcher = new IFrameSwitcher();

    //    page elements/Paths: iFrame:
    final By iFramePath = By.id("framelive");

    //    checkboxes:
    private final By filterCheckboxsPath = By.xpath(
            "//a[@class='_gray-darker search-link js-search-link']"); //works

    //    sliders:
    private final SelenideElement filterPriceRangePath = $x("//ul[@class='faceted-slider collapse in']/li/p"); //partly worked
    private final SelenideElement filterPriceLowerLimitSliderButtonDefault = $x("//ul[@class='faceted-slider collapse']/li/div/a");
    private final SelenideElement filterPriceLowerLimitSliderButtonModified = $x("//ul[@class='faceted-slider collapse in']/li/div/a");
    private final SelenideElement filterPriceUpperLimitSliderButtonModified = $x("//ul[@class='faceted-slider collapse in']/li/div").lastChild();

    //    buttons:
    private final By sortByButtonPath = By.xpath("//button[@aria-label='Sort by selection']");

    //    dropdown's:
    private final By sortByDropdownPath = By.xpath("//div[@class='dropdown-menu']/a");

    //    products:
    private final By productsRowCollectionPath = By.xpath("//div[@class='products row']/div");

    //    other fields/elements:
    private final By activeFiltersPath = By.xpath("//section[@class='active_filters']/ul/li");


    public void applySpecifiedPriceRangeAndBlackColorFilters() {
        iFrameSwitcher.checkIfIFrameFrameliveExistsAndSwitchToIt();

//        applying the price filter
        $(filterPriceLowerLimitSliderButtonDefault).click();
        actions().moveToElement(filterPriceLowerLimitSliderButtonModified).clickAndHold().moveByOffset(25, 0).release().perform(); //works. from EUR 18
        sleep(3000);
        actions().moveToElement(filterPriceUpperLimitSliderButtonModified).clickAndHold().moveByOffset(-145, 0).release().perform(); //works. to EUR 23
        sleep(3000);
        Assert.assertEquals($(filterPriceRangePath).getText(), "€18.00 - €23.00");

//        filter's checkboxes are dynamical. - selecting some, - some might disappear and checkbox's indexes changes
//        applying the filter color:black
        $$(filterCheckboxsPath).get(1).click();
        sleep(3000);

        iFrameSwitcher.switchToDefaultContent();
    }

    public void checkPriceAndBlackColorFilter(float minPrice, float maxPrice) {
        iFrameSwitcher.checkIfIFrameFrameliveExistsAndSwitchToIt();

//        validating that the 1st and the last element price is within range
//        Changing sorting method to sort by price descending:
        $(sortByButtonPath).click();
        Assert.assertNotNull($$(sortByDropdownPath).get(5).getAttribute("href"));
        Assert.assertTrue($$(sortByDropdownPath).get(5).getAttribute("href").contains("product.price.desc"));
        $$(sortByDropdownPath).get(5).click();
        sleep(2000);

//        validating 1st product price
        float price = Float.parseFloat($$(productsRowCollectionPath)
                .get(0).findElement(By.className("price")).getText().substring(1));
        Assert.assertTrue(price <= maxPrice && price >= minPrice,
                "product price: " + price + "; \"min price\": " + minPrice + "; \"max price\": " + maxPrice);

        //        validating last product price
        price = Float.parseFloat($$(productsRowCollectionPath)
                .get(2).findElement(By.className("price")).getText().substring(1));
        Assert.assertTrue(price <= maxPrice && price >= minPrice,
                "product price: " + price + "; \"min price\": " + minPrice + "; \"max price\": " + maxPrice);

        iFrameSwitcher.switchToDefaultContent();
    }

    /**
     * checkColorFilter for now checks only black color filter
     */
    public void checkColorFilter() {
        iFrameSwitcher.checkIfIFrameFrameliveExistsAndSwitchToIt();

        $$(activeFiltersPath).find(Condition.text("Color: Black")).shouldBe(visible);

        iFrameSwitcher.switchToDefaultContent();
    }

    public void chooseItemByIndexAndOpenInANewTab(int productIndexOnPage) {
        iFrameSwitcher.checkIfIFrameFrameliveExistsAndSwitchToIt();

        String href = $$(productsRowCollectionPath).get(productIndexOnPage).findElement(By.cssSelector(".thumbnail.product-thumbnail")).getAttribute("href");
        System.out.println("href = " + href);
        String variable = "window.open(\"" + href + "\")";
        System.out.println("variable: " + variable);
        executeJavaScript(variable);

        iFrameSwitcher.switchToDefaultContent();
        switchTo().window(1);
    }
}
