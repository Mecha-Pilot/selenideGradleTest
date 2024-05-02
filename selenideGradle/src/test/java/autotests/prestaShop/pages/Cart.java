package autotests.prestaShop.pages;

import autotests.prestaShop.utils.IFrameSwitcher;
import org.openqa.selenium.By;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

/**
 * Cart page. Does not have iFrame "framelive"
 */
public class Cart {
    //    page elements/Paths: buttons:
    private final By proceedToCheckoutButtonPath = By.xpath("//div[@class='checkout cart-detailed-actions js-cart-detailed-actions card-block']");

    //    information fields:
    private final By totalTaxIncludedPriceFieldPath = By.xpath("//div[@class='cart-summary-line cart-total']");

    public void checkThatTotalPriceInCartIsCorrect(float expectedTotalProductsPriceWithTax) {
        float actualTotalPriceOnTheCartPage = 0f;
        switchTo().window(1);

        actualTotalPriceOnTheCartPage = Float.parseFloat($(totalTaxIncludedPriceFieldPath).findElement(By.className("value")).getText().substring(1));

        Assert.assertEquals(actualTotalPriceOnTheCartPage, expectedTotalProductsPriceWithTax);
    }

    public void proceedToCheckout() {
        $(proceedToCheckoutButtonPath).shouldBe(visible).click();
    }
}
