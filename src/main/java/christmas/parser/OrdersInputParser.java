package christmas.parser;

import christmas.constant.DelimiterEnum;
import christmas.constant.ErrorMessage;
import christmas.domain.Menu;
import christmas.domain.Order;
import christmas.domain.Orders;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OrdersInputParser extends InputParser<Orders> {

    private final OrderParser orderParser;
    private final List<Order> orderList;

    public OrdersInputParser(Menu menu) {
        this.orderParser = new OrderParser(menu);
        orderList = new ArrayList<>();
    }

    @Override
    public void clear() {
        orderList.clear();
    }

    @Override
    protected void validate(String input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage());
        }
        if (input.contains(DelimiterEnum.INVALID_ORDERS.getDelimiter())) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage());
        }
    }

    @Override
    protected Orders convert(String input) {
        for (String stringOrder : input.split(DelimiterEnum.ORDERS.getDelimiter())) {
            Order order = tryCreateOrder(stringOrder);
            addOrder(order);
        }
        return new Orders(Collections.unmodifiableList(orderList));
    }

    private Order tryCreateOrder(String stringOrder) {
        try {
            return orderParser.parse(stringOrder);
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage());
        }
    }

    private void addOrder(Order order) {
        throwIfContainsAlready(order);
        orderList.add(order);
    }

    private void throwIfContainsAlready(Order order) {
        if (containsAlready(order)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage());
        }
    }

    private boolean containsAlready(Order order) {
        return orderList.stream().anyMatch(other -> other.hasSameMenu(order));
    }
}
