package christmas.vo;

import christmas.constant.DateEnum;
import christmas.constant.ErrorMessage;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class Date {

    private final int day;
    private final DayOfWeek dayOfWeek;

    public Date(int day) {
        validate(day);
        this.day = day;
        LocalDate date = LocalDate.of(DateEnum.YEAR.getDateValue(), DateEnum.MONTH.getDateValue(), day);
        this.dayOfWeek = date.getDayOfWeek();
    }

    public boolean isWeekday() {
        return !isWeekend();
    }

    public boolean isWeekend() {
        return dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.SATURDAY;
    }

    public boolean isSpecialDay() {
        return dayOfWeek == DayOfWeek.SUNDAY || day == DateEnum.CHRISTMAS.getDateValue();
    }

    public boolean hasChristmasPassed() {
        return day > DateEnum.CHRISTMAS.getDateValue();
    }

    public int getDay() {
        return day;
    }

    private void validate(int day) {
        if (day < DateEnum.MIN_DAY.getDateValue() || day > DateEnum.MAX_DAY.getDateValue()) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_DATE.getMessage());
        }
    }
}
