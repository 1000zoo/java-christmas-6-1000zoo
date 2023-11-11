package christmas.domain;

import java.util.List;

public class Orders {

    private final static int ZERO_QUANTITY = 0;
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
            throw new IllegalArgumentException();
        }
    }

    private void throwIfOrderedOnlyDrink() {
        if (hasOnlyDrink()) {
            throw new IllegalArgumentException();
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
}
