package christmas.vo;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class Date {

    private final static int YEAR = 2023;
    private final static int MONTH = 12;
    private final static int MIN_DAY = 1;
    private final static int MAX_DAY = 31;
    private final static int CHRISTMAS = 25;

    private final int day;
    private final DayOfWeek dayOfWeek;

    public Date(int day) {
        validate(day);
        this.day = day;
        LocalDate date = LocalDate.of(YEAR, MONTH, day);
        this.dayOfWeek = date.getDayOfWeek();
    }

    public boolean isNormalDay() {
        return !isWeekend() && !isSpecialDay();
    }

    public boolean isWeekend() {
        return dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.SATURDAY;
    }

    public boolean isSpecialDay() {
        return dayOfWeek == DayOfWeek.SUNDAY || day == CHRISTMAS;
    }

    private void validate(int day) {
        if (day < MIN_DAY || day > MAX_DAY) {
            throw new IllegalArgumentException();
        }
    }
}
