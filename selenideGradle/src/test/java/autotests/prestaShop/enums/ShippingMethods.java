package autotests.prestaShop.enums;

import java.text.DecimalFormat;

public enum ShippingMethods {
    MY_CARRIER_USA("My carrier: USA", 7.00f),
    MY_CARRIER_FRANCE("My carrier: France", 8.40f);

    private final String name;
    private final float price;

    ShippingMethods(String name, float price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }
}
