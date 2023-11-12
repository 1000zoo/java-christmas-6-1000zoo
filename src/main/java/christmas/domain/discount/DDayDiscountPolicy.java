package christmas.domain.discount;

import christmas.vo.Date;

public class DDayDiscountPolicy implements SpecialEventPolicy {

    private final static int START_DISCOUNT_AMOUNT = 900;
    private final static int INCREASE_AMOUNT = 100;

    private final Date date;

    public DDayDiscountPolicy(Date date) {
        this.date = date;
    }

    @Override
    public int getDiscountAmount() {
        if (date.hasChristmasPassed()) {
            return 0;
        }
        return START_DISCOUNT_AMOUNT + INCREASE_AMOUNT * (date.getDay());
    }

    @Override
    public int getTotalBenefit() {
        return getDiscountAmount();
    }
}
