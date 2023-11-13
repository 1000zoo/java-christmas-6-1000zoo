package christmas.domain;

import christmas.constant.ErrorMessage;
import christmas.dto.OrdersDto;
import java.util.List;

public class Orders {

    private final static int MAX_ORDER_QUANTITY = 20;

    private final List<Order> orders;

    public Orders(List<Order> orders) {
        this.orders = orders;
        validate();
    }

    private void validate() {
        throwIfOrderedTooMuch();
        throwIfOrderedOnlyDrink();
    }

    private void throwIfOrderedTooMuch() {
        if (calculateTotalQuantities() > MAX_ORDER_QUANTITY) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage());
        }
    }

    private void throwIfOrderedOnlyDrink() {
        if (hasOnlyDrink()) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage());
        }
    }

    private boolean hasOnlyDrink() {
        return orders.stream().noneMatch(Order::isFood);
    }

    public int calculateTotalQuantities() {
        return orders.stream().mapToInt(Order::getQuantity).sum();
    }

    public int calculateTotalPrice() {
        return orders.stream().mapToInt(Order::getTotalPrice).sum();
    }

    public int countDessert() {
        return orders.stream()
                .filter(order -> order.getMenuType() == MenuType.DESSERT)
                .mapToInt(Order::getQuantity).sum();
    }

    public int countMain() {
        return orders.stream()
                .filter(order -> order.getMenuType() == MenuType.MAIN)
                .mapToInt(Order::getQuantity).sum();
    }

    public OrdersDto toDto() {
        return new OrdersDto(orders.stream().map(Order::toDTO).toList(), calculateTotalPrice());
    }
}
