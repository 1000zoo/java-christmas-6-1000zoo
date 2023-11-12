package christmas.domain.discount;

import christmas.domain.Orders;

public class WeekdayDiscountPolicy implements SpecialEventPolicy {

    private final static int DISCOUNT_AMOUNT = 2023;

    private final Orders orders;

    public WeekdayDiscountPolicy(Orders orders) {
        this.orders = orders;
    }

    @Override
    public int getDiscountAmount() {
        return DISCOUNT_AMOUNT * orders.countDessert();
    }
}
