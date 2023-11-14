package christmas.domain;

import christmas.constant.ErrorMessage;
import christmas.constant.QuantityEnum;
import christmas.dto.OrdersDto;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class Orders {

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
        if (calculateTotalQuantities() > QuantityEnum.MAX.getQuantity()) {
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

    private int calculateSum(Function<Order, Integer> function) {
        return orders.stream().mapToInt(function::apply).sum();
    }

    public int calculateTotalQuantities() {
        return calculateSum(Order::getQuantity);
    }

    public int calculateTotalPrice() {
        return calculateSum(Order::getTotalPrice);
    }

    private int countMenuType(Predicate<Order> menuPredicate) {
        return orders.stream().filter(menuPredicate).mapToInt(Order::getQuantity).sum();
    }

    public int countDessert() {
        return countMenuType(Order::isDessert);
    }

    public int countMain() {
        return countMenuType(Order::isMain);
    }

    public OrdersDto toDto() {
        return new OrdersDto(orders.stream().map(Order::toDTO).toList(), calculateTotalPrice());
    }
}
