package christmas.domain;

import christmas.domain.discount.EventPolicies;
import christmas.dto.CustomerDto;

public class Customer {

    private final OrderHistory orderHistory;
    private final EventPolicies eventPolicies;

    public Customer(OrderHistory orderHistory, EventPolicies eventPolicies) {
        this.orderHistory = orderHistory;
        this.eventPolicies = eventPolicies;
    }

    private int calculateAfterDiscountAmount() {
        return orderHistory.calculateTotalPrice() - eventPolicies.getDiscountAmount();
    }

    public CustomerDto toDTO() {
        return new CustomerDto(calculateAfterDiscountAmount(), Badge.findByAmount(orderHistory.calculateTotalPrice()));
    }
}
