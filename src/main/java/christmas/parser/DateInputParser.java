package christmas.parser;

import christmas.validator.IntegerInputValidator;
import christmas.validator.Validator;
import christmas.vo.Date;

public class DateInputParser extends InputParser<Date> {

    private final static Validator<String> validator = new IntegerInputValidator();

    @Override
    protected void validate(String input) {
        validator.validate(input);
    }

    @Override
    protected Date convert(String input) {
        return new Date(Integer.parseInt(input));
    }
}
