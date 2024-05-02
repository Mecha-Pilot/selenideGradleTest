package autotests.prestaShop.pages;

import autotests.prestaShop.utils.IFrameSwitcher;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class ProductPage {
    private IFrameSwitcher iFrameSwitcher = new IFrameSwitcher();

    //    page elements/Paths: buttons:
    private final By increaseProductQuantityButtonPath = By.xpath("//i[@class='material-icons touchspin-up']");
    private final By addToCartButtonPath = By.xpath("//button[@data-button-action='add-to-cart']");

    //    fields:
    private final By productQuantityFieldPath = By.xpath("//input[@id='quantity_wanted']");

    //    information fields:
    private final By currentProductPriceFieldPath = By.xpath("//span[@class='current-price-value']");
    private final By currentProductTotalPriceInCartFieldPath = By.xpath("//p[@class='product-total']/span");

    public void increaseProductQuantityByOne() {
        switchTo().window(1);
        iFrameSwitcher.checkIfIFrameFrameliveExistsAndSwitchToIt();

        $(increaseProductQuantityButtonPath).shouldBe(visible).click();

        iFrameSwitcher.switchToDefaultContent();
    }

    public int setProductQuantity(int productQuantity) {
        switchTo().window(1);
        iFrameSwitcher.checkIfIFrameFrameliveExistsAndSwitchToIt();

        $(productQuantityFieldPath).shouldBe(visible).setValue("" + 0 + "");
        $(productQuantityFieldPath).shouldBe(visible).click();
        $(productQuantityFieldPath).shouldBe(visible).sendKeys(Keys.BACK_SPACE);
        $(productQuantityFieldPath).shouldBe(visible).sendKeys(Keys.BACK_SPACE);
        $(productQuantityFieldPath).shouldBe(visible).setValue("" + productQuantity + "");

        iFrameSwitcher.switchToDefaultContent();
        return productQuantity;
    }

    public void addProductToCart() {
        switchTo().window(1);
        iFrameSwitcher.checkIfIFrameFrameliveExistsAndSwitchToIt();

        $(addToCartButtonPath).shouldBe(visible).click();
        sleep(5000);

        iFrameSwitcher.switchToDefaultContent();
    }

    public float saveProductPriceFromProductPage() {
        iFrameSwitcher.checkIfIFrameFrameliveExistsAndSwitchToIt();

        float productPrice = 0;

        switchTo().window(1);
        productPrice = Float.parseFloat($(currentProductPriceFieldPath).shouldBe(visible).getText().substring(1));

        iFrameSwitcher.switchToDefaultContent();
        return productPrice;
    }

    public void checkIfThePriceIsCorrect(float productPrice, int productQuantity) {
        float expectedPriceForMultipleProductCopies;
        float actualPriceForMultipleProductCopies;

        switchTo().window(1);
        iFrameSwitcher.checkIfIFrameFrameliveExistsAndSwitchToIt();

        expectedPriceForMultipleProductCopies = productPrice * productQuantity;
        actualPriceForMultipleProductCopies = Float.parseFloat($$(currentProductTotalPriceInCartFieldPath).get(1).getText().substring(1));
        Assert.assertEquals(actualPriceForMultipleProductCopies, expectedPriceForMultipleProductCopies);

        iFrameSwitcher.switchToDefaultContent();
    }
}
