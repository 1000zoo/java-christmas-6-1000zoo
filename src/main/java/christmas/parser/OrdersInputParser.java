package christmas.parser;

import christmas.domain.Menu;
import christmas.domain.Order;
import christmas.domain.Orders;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OrdersInputParser extends InputParser<Orders> {

    private final static String ORDER_DELIMITER = ",";

    private final OrderParser orderParser;
    private final List<Order> orders;

    public OrdersInputParser(Menu menu) {
        this.orderParser = new OrderParser(menu);
        orders = new ArrayList<>();
    }

    @Override
    protected void validate(String input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    protected Orders convert(String input) {
        for (String stringOrder : splitInput(input)) {
            Order order = orderParser.parse(stringOrder);
            addOrder(order);
        }
        return new Orders(Collections.unmodifiableList(orders));
    }

    private void addOrder(Order order) {
        if (containsName(order.getMenuName())) {
            throw new IllegalArgumentException();
        }
        orders.add(order);
    }

    private boolean containsName(String menuName) {
        return orders.stream().distinct().anyMatch(order -> menuName.equals(order.getMenuName()));
    }

    private List<String> splitInput(String input) {
        return List.of(input.split(ORDER_DELIMITER));
    }
}
