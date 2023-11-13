package christmas.constant;

public enum QuantityEnum {
    MIN(0), MAX(20);

    private final int quantity;

    QuantityEnum(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }
}
