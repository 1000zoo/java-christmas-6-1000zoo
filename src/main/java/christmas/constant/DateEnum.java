package christmas.constant;

public enum DateEnum {
    YEAR(2023),
    MONTH(12),
    MIN_DAY(1),
    MAX_DAY(31),
    CHRISTMAS(25);

    private final int dateValue;

    DateEnum(int dateValue) {
        this.dateValue = dateValue;
    }

    public int getDateValue() {
        return dateValue;
    }
}
