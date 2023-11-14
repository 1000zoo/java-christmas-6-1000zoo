package christmas.domain;

import christmas.domain.discount.EventPolicies;

public class Customer {

    private final OrderHistory orderHistory;
    private final EventPolicies eventPolicies;

    public Customer(OrderHistory orderHistory, EventPolicies eventPolicies) {
        this.orderHistory = orderHistory;
        this.eventPolicies = eventPolicies;
    }

    public int calculateAfterDiscountAmount() {
        return orderHistory.calculateTotalPrice() - eventPolicies.getDiscountAmount();
    }
}
