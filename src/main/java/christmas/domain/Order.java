package christmas.domain;

import christmas.constant.ErrorMessage;
import christmas.dto.OrderDto;
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
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage());
        }
    }

    public String getMenuName() {
        return menuInformation.name();
    }

    public int getQuantity() {
        return quantity;
    }

    public int getTotalPrice() {
        return quantity * menuInformation.price();
    }

    public boolean isFood() {
        return menuInformation.isFood();
    }

    public boolean isDessert() {
        return menuInformation.isDessert();
    }

    public boolean isMain() {
        return menuInformation.isMain();
    }

    public OrderDto toDTO() {
        return new OrderDto(menuInformation.name(), quantity);
    }
}
