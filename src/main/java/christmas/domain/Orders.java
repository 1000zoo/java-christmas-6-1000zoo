package christmas.domain;

import christmas.vo.MenuInformation;
import java.util.Map;

public class Orders {

    private final Map<MenuInformation, Integer> orders;

    public Orders(Map<MenuInformation, Integer> orders) {
        this.orders = orders;
    }

    public int calculateTotalQuantities() {
        return orders.values().stream().reduce(Integer::sum).orElse(0);
    }

    public int calculateTotalPrice() {
        return orders.entrySet().stream()
                .mapToInt(entry -> entry.getKey().price() * entry.getValue())
                .sum();
    }
}
