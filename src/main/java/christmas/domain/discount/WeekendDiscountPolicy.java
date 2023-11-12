package christmas.domain.discount;

import christmas.domain.Orders;

public class WeekendDiscountPolicy implements SpecialEventPolicy {

    private final static int DISCOUNT_AMOUNT = 2023;

    private final Orders orders;

    public WeekendDiscountPolicy(Orders orders) {
        this.orders = orders;
    }

    @Override
    public int getDiscountAmount() {
        return DISCOUNT_AMOUNT * orders.countMain();
    }

    @Override
    public int getTotalBenefit() {
        return getDiscountAmount();
    }
}
