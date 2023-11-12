package christmas.domain.discount;

public class SpecialDayDiscountPolicy implements SpecialEventPolicy {

    private final static int SPECIAL_DISCOUNT_AMOUNT = 1000;

    @Override
    public int getDiscountAmount() {
        return SPECIAL_DISCOUNT_AMOUNT;
    }

    @Override
    public int getTotalBenefit() {
        return getDiscountAmount();
    }
}
