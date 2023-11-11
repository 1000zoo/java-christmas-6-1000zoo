package christmas.domain;

import java.util.List;

public class Orders {

    private final List<Order> orders;

    public Orders(List<Order> orders) {
        this.orders = orders;
    }

    public int calculateTotalQuantities() {
        return orders.stream().mapToInt(Order::getQuantity).sum();
    }

    public int calculateTotalPrice() {
        return orders.stream().mapToInt(Order::getTotalPrice).sum();
    }
}
