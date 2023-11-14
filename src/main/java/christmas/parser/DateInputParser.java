package christmas.parser;

import christmas.constant.ErrorMessage;
import christmas.vo.Date;

public class DateInputParser extends InputParser<Date> {

    @Override
    protected void validate(String input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_DATE.getMessage());
        }
    }

    @Override
    protected Date convert(String input) {
        int day = IntegerParser.parse(input, ErrorMessage.INVALID_DATE.getMessage());
        return new Date(day);
    }
}
