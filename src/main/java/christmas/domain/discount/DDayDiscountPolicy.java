package christmas.domain.discount;

public class DDayDiscountPolicy implements SpecialEventPolicy {

    private final static int START_DISCOUNT_AMOUNT = 900;
    private final static int INCREASE_AMOUNT = 100;

    private final int day;

    public DDayDiscountPolicy(int day) {
        this.day = day;
    }

    @Override
    public int getDiscountAmount() {
        return START_DISCOUNT_AMOUNT + INCREASE_AMOUNT * day;
    }

    @Override
    public int getTotalBenefit() {
        return getDiscountAmount();
    }
}
