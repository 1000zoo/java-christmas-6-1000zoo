package christmas.domain;

import christmas.dto.OrderDTO;
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

    public int getTotalPrice() {
        return quantity * menuInformation.price();
    }

    public MenuType getMenuType() {
        return menuInformation.menuType();
    }

    public boolean isFood() {
        return menuInformation.isFood();
    }

    public OrderDTO toDTO() {
        return new OrderDTO(menuInformation.name(), quantity);
    }
}
