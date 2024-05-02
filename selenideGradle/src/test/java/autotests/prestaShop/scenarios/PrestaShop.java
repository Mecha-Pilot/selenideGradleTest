package autotests.prestaShop.scenarios;

import autotests.BaseTest;
import autotests.prestaShop.enums.PaymentMethods;
import autotests.prestaShop.enums.ShippingMethods;
import autotests.prestaShop.pages.*;
import autotests.prestaShop.pages.headerFooter.Header;
import autotests.prestaShop.utils.Calculations;
import autotests.utils.BrowserTabSwitcher;
import com.codeborne.selenide.conditions.Or;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$;

public class PrestaShop extends BaseTest {
    private final BrowserTabSwitcher browserTabSwitcher = new BrowserTabSwitcher();
    private final Calculations calculations = new Calculations();
    private final Header header = new Header();
    private final HomePage mainPage = new HomePage();
    private final CreateAnAccount createAnAccount = new CreateAnAccount();
    private final LogIn logIn = new LogIn();
    private final Accessories accessories = new Accessories();
    private final ProductPage productPage = new ProductPage();
    private final Cart cart = new Cart();
    private final CheckoutForm checkoutForm = new CheckoutForm();
    private final OrderInformation orderInformation = new OrderInformation();
    private final static String PRESTASHOP_URL = "http://demo.prestashop.com";

    @Test
    public void mainScenario() {
        System.out.println("test \"mainScenario\" started");
        float productPrice = 0f;
        int productQuantity = 0;
        float expectedTotalProductsPriceWithTax = 0f;

//        step 1: go to http://demo.prestashop.com
        mainPage.openBrowserAndWebSitePage(PRESTASHOP_URL);
        mainPage.goToCreateAccountPage();

//        step 2: register an account, check you're logged in
        createAnAccount.prepareAndFillMandatoryAccountData();
        createAnAccount.saveAccountDataAndRegisterAnAccount();
        mainPage.checkThatLoggedInSuccessfully();

//        step 3: open "Accessories" section
        mainPage.goToAccessoriesPage();

//        step 3.1: filter out items of black colour within price range 18-23
        accessories.applySpecifiedPriceRangeAndBlackColorFilters();

//        step 4: check the items are correctly filtered
        accessories.checkPriceAndBlackColorFilter(18f, 23f);
        accessories.checkColorFilter();

//        step 5: randomly choose one of items, increase quantity of items and add to cart
        accessories.chooseItemByIndexAndOpenInANewTab(0);
        productPrice = productPage.saveProductPriceFromProductPage();
        productQuantity = productPage.setProductQuantity(2);
        productPage.addProductToCart();

//        step 6: check a price is correctly calculated
        productPage.checkIfThePriceIsCorrect(productPrice, productQuantity);
        browserTabSwitcher.closeBrowserTab();
        expectedTotalProductsPriceWithTax = calculations
                .calculateExpectedTotalProductsPriceWithTaxAfterAddingANewProductToCart
                        (productPrice, productQuantity, expectedTotalProductsPriceWithTax);

//        step 7: go back to filtered list of items, choose one more item
        browserTabSwitcher.switchToBrowserTab(0);
        accessories.chooseItemByIndexAndOpenInANewTab(1);

//        step 8: go to cart
        header.goToCartIfItIsNotEmpty();

//        step 9: check a total price is correctly calculated
        cart.checkThatTotalPriceInCartIsCorrect(expectedTotalProductsPriceWithTax);

//        step 10: Checkout
        cart.proceedToCheckout();

//        step 11: fill out the form
        checkoutForm.inputMandatoryAddressesDataAndPressContinueButton();

//        step 12: choose a shipping method, choose "payment by Check", check the total price
        expectedTotalProductsPriceWithTax = checkoutForm
                .chooseTheShippingMethodAddItsPriceToTheTotalPriceAndPressContinue
                        (ShippingMethods.MY_CARRIER_FRANCE, expectedTotalProductsPriceWithTax);
        checkoutForm.checkThatTotalPriceWithTheShipmentInTheCheckoutFormIsCorrect(expectedTotalProductsPriceWithTax);
        checkoutForm.fillThePaymentBlock(PaymentMethods.PAY_BY_CHECK);

//        step 13: confirm your order and check order details
        orderInformation.checkOrderDetails(ShippingMethods.MY_CARRIER_FRANCE, PaymentMethods.PAY_BY_CHECK);

//        step 14: logout, check you've been successfully logged out
        header.signOutFromDemoPrestashop();
        header.makingSureThatTheUserIsNotLoggedIn();

        System.out.println("test \"mainScenario\" ended");
    }
}
