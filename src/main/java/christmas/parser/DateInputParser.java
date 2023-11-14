package christmas.parser;

import christmas.vo.Date;

public class DateInputParser extends InputParser<Date> {

    @Override
    protected void validate(String input) {
    }

    @Override
    protected Date convert(String input) {
        return new Date(IntegerParser.parse(input));
    }
}
