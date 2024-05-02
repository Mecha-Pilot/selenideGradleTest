package autotests.prestaShop.pages;

import autotests.prestaShop.enums.PaymentMethods;
import autotests.prestaShop.enums.ShippingMethods;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.text.DecimalFormat;
import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class CheckoutForm {
    //    page elements/Paths: fields:
    private final By addressFieldPath = By.xpath("//input[@id='field-address1']");
    private final By cityFieldPath = By.xpath("//input[@id='field-city']");
    private final By zipPostalCodeFieldPath = By.xpath("//input[@id='field-postcode']"); //must be 5 digits

    //    selectors:
    private final By stateSelectorPath = By.xpath("//select[@id='field-id_state']");
    private final By countryFieldPath = By.xpath("//select[@id='field-id_country']");

    //    buttons:
    private final By continueFromAddressesBlockButtonPath = By.xpath("//button[@name='confirm-addresses']");
    private final By continueFromShipmentBlockButtonPath = By.xpath("//button[@name='confirmDeliveryOption']");
    private final By placeOrderButtonPath = By.xpath("//button[@class='btn btn-primary center-block']");

    //    radio buttons:
    private final By shippingMethodMyCarrierRadioButtonPath = By.xpath("//input[@id='delivery_option_2']");
    private final By paymentMethodByCheckRadioButtonPath = By.xpath("//input[@data-module-name='ps_checkpayment']");

    //    checkbox's:
    private final By conditionsCheckboxPath = By.xpath("//input[@id='conditions_to_approve[terms-and-conditions]']");

    //    information fields:
    private final By totalTaxIncludedPriceWithShipmentFieldPath = By.xpath("//div[@class='cart-summary-line cart-total']");

    public void inputMandatoryAddressesDataAndPressContinueButton() {
        $(countryFieldPath).shouldBe(visible, Duration.ofSeconds(10)).selectOptionContainingText("France");
        $(addressFieldPath).shouldBe(visible).setValue("Whoa street 5");
        $(cityFieldPath).shouldBe(visible).setValue("Milwaukee");
        $(zipPostalCodeFieldPath).shouldBe(visible).setValue("53205");

        $(continueFromAddressesBlockButtonPath).should(exist, Duration.ofSeconds(10)).click();
    }

    public float chooseTheShippingMethodAddItsPriceToTheTotalPriceAndPressContinue(Enum shippingMethod, float previousExpectedTotalProductsPriceWithTax){
        float expectedTotalProductsPriceWithTaxAndShipping = 0f;

        if (shippingMethod == ShippingMethods.MY_CARRIER_USA){
            if (!$(shippingMethodMyCarrierRadioButtonPath).should(exist, Duration.ofSeconds(10)).isSelected()){
                $(shippingMethodMyCarrierRadioButtonPath).shouldBe(clickable, Duration.ofSeconds(10)).click();
            }

            expectedTotalProductsPriceWithTaxAndShipping = previousExpectedTotalProductsPriceWithTax + ShippingMethods.MY_CARRIER_USA.getPrice();
        }

        if (shippingMethod == ShippingMethods.MY_CARRIER_FRANCE){
            if (!$(shippingMethodMyCarrierRadioButtonPath).should(exist, Duration.ofSeconds(10)).isSelected()){
                $(shippingMethodMyCarrierRadioButtonPath).shouldBe(clickable, Duration.ofSeconds(10)).click();
            }

            expectedTotalProductsPriceWithTaxAndShipping = previousExpectedTotalProductsPriceWithTax + ShippingMethods.MY_CARRIER_FRANCE.getPrice();
            expectedTotalProductsPriceWithTaxAndShipping = (float) (Math.round(expectedTotalProductsPriceWithTaxAndShipping * 100.0) / 100.0);
        } else Assert.fail(); // because for now there are no other shipping methods

        $(continueFromShipmentBlockButtonPath).click();

        return expectedTotalProductsPriceWithTaxAndShipping;
    }

    public void fillThePaymentBlock(Enum paymentMethod){
        if (paymentMethod == PaymentMethods.PAY_BY_CHECK) {
            $(paymentMethodByCheckRadioButtonPath).shouldBe(clickable, Duration.ofSeconds(10)).click();
        }

        $(conditionsCheckboxPath).click();
        $(placeOrderButtonPath).click();
    }

    public void checkThatTotalPriceWithTheShipmentInTheCheckoutFormIsCorrect(float expectedTotalProductsPriceWithTax){
        float actualTotalPriceOnTheCartPage = 0;
        switchTo().window(1);

        actualTotalPriceOnTheCartPage = Float.parseFloat($(totalTaxIncludedPriceWithShipmentFieldPath).findElement(By.className("value")).getText().substring(1));

        Assert.assertEquals(actualTotalPriceOnTheCartPage, expectedTotalProductsPriceWithTax, "actual \'Total price\' differ from expected.");
    }

}
