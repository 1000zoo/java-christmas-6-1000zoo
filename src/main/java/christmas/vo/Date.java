package christmas.vo;

import christmas.constant.DateEnum;
import christmas.constant.ErrorMessage;
import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class Date {

    private final int day;
    private final DayOfWeek dayOfWeek;

    public Date(int day) {
        this.day = day;
        this.dayOfWeek = localDateFromDay();
    }

    private DayOfWeek localDateFromDay() {
        try {
            LocalDate date = LocalDate.of(DateEnum.YEAR.getDateValue(), DateEnum.MONTH.getDateValue(), day);
            return date.getDayOfWeek();
        } catch (DateTimeException exception) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_DATE.getMessage());
        }
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
}
