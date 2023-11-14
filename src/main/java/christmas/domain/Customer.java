package christmas.domain;

import christmas.domain.discount.EventPolicies;
import christmas.dto.CustomerDTO;

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

    public CustomerDTO toDTO() {
        return new CustomerDTO(calculateAfterDiscountAmount(), Badge.findByAmount(orderHistory.calculateTotalPrice()));
    }
}
