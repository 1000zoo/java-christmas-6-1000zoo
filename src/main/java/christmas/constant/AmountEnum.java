package christmas.constant;

public enum AmountEnum {
    WEEKDAY_DISCOUNT(2023),
    WEEKEND_DISCOUNT(2023),
    SPECIAL_DISCOUNT(1000),
    MIN_BOUNDARY_FOR_EVENT(10_000),
    MIN_BOUNDARY_FOR_GIVEAWAY(120_000);

    private final int amount;

    AmountEnum(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }
}
