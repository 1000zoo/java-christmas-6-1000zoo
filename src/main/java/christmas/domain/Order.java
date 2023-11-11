package christmas.domain;

import christmas.vo.MenuInformation;

public class Order {

    private final MenuInformation menuInformation;
    private final int quantity;

    public Order(MenuInformation menuInformation, int quantity) {
        validate(quantity);
        this.menuInformation = menuInformation;
        this.quantity = quantity;
    }

    private void validate(int quantity) {
        if (quantity < 1) {
            throw new IllegalArgumentException();
        }
    }

    public String getMenuName() {
        return menuInformation.name();
    }

    public int getQuantity() {
        return quantity;
    }
}
