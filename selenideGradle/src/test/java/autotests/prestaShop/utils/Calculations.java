package autotests.prestaShop.utils;

/**
 * Class to do various calculations for 'prestaShop'
 */
public class Calculations {
    public float calculateExpectedTotalProductsPriceWithTaxAfterAddingANewProductToCart(
            float productPrice, int productQuantity, float previousExpectedTotalProductsPriceWithTax) {

        float expectedTotalProductsPriceWithTax = 0f;
        expectedTotalProductsPriceWithTax = previousExpectedTotalProductsPriceWithTax + (productPrice * productQuantity);

        return expectedTotalProductsPriceWithTax;
    }
}
