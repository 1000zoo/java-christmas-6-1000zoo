package christmas.domain.discount;

public class WeekdayDiscountPolicy implements SpecialEventPolicy {

    private final static int DISCOUNT_AMOUNT = 2023;

    private final int countDessert;

    public WeekdayDiscountPolicy(int countDessert) {
        this.countDessert = countDessert;
    }

    @Override
    public int getDiscountAmount() {
        return DISCOUNT_AMOUNT * countDessert;
    }

    @Override
    public int getTotalBenefit() {
        return getDiscountAmount();
    }
}
