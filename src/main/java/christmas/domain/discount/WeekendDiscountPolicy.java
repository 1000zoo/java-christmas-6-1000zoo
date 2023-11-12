package christmas.domain.discount;

public class WeekendDiscountPolicy implements SpecialEventPolicy {

    private final static int DISCOUNT_AMOUNT = 2023;

    private final int countMain;

    public WeekendDiscountPolicy(int countMain) {
        this.countMain = countMain;
    }

    @Override
    public int getDiscountAmount() {
        return DISCOUNT_AMOUNT * countMain;
    }

    @Override
    public int getTotalBenefit() {
        return getDiscountAmount();
    }
}
