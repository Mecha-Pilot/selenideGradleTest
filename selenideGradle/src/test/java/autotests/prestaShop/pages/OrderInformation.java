package autotests.prestaShop.pages;

import autotests.prestaShop.enums.PaymentMethods;
import autotests.prestaShop.enums.ShippingMethods;
import org.openqa.selenium.By;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class OrderInformation {
    //    page elements/Paths: information fields:
    private final By orderDetailsFieldsPath = By.xpath("//div[@id='order-details']/ul/li");

    public void checkOrderDetails(Enum shippingMethod, Enum paymentMethod){
        String orderReferenceString = $$(orderDetailsFieldsPath).get(0).getText();
        String paymentMethodString = $$(orderDetailsFieldsPath).get(1).getText();
        String shippingMethodString = $$(orderDetailsFieldsPath).get(2).getText();

        Assert.assertNotNull(orderReferenceString);
        Assert.assertEquals(orderReferenceString.length(), 26);

        if (paymentMethod == PaymentMethods.PAY_BY_CHECK){
            Assert.assertEquals(paymentMethodString, "Payment method: Payments by check");
        }

        if (shippingMethod == ShippingMethods.MY_CARRIER_USA || shippingMethod == ShippingMethods.MY_CARRIER_FRANCE){
            Assert.assertEquals(shippingMethodString, "Shipping method: My carrier\nDelivery next day!");
        }

        System.out.println("Order Details: \n " + orderReferenceString +
                "\n " + paymentMethodString + "\n " + shippingMethodString);
    }
}
