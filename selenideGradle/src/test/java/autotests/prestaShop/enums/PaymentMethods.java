package autotests.prestaShop.enums;

public enum PaymentMethods {
    PAY_BY_CHECK("Pay by Check");

    private final String text;

    PaymentMethods(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
