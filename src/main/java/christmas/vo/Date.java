package christmas.vo;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class Date {

    private final static String INVALID_RANGE_INPUT_ERROR_MESSAGE = "1 ~ 31 사이의 정수만 입력해주세요.";

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

    public boolean isWeekday() {
        return !isWeekend();
    }

    public boolean isWeekend() {
        return dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.SATURDAY;
    }

    public boolean isSpecialDay() {
        return dayOfWeek == DayOfWeek.SUNDAY || day == CHRISTMAS;
    }

    public boolean hasChristmasPassed() {
        return day > CHRISTMAS;
    }

    public int getDay() {
        return day;
    }

    private void validate(int day) {
        if (day < MIN_DAY || day > MAX_DAY) {
            throw new IllegalArgumentException(INVALID_RANGE_INPUT_ERROR_MESSAGE);
        }
    }
}
