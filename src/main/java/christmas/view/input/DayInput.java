package christmas.view.input;

import christmas.vo.Date;

public class DayInput extends Input<Date> {

    private final static String ERROR_MESSAGE_INVALID_DATE_INPUT = "유효하지 않은 날짜입니다. 다시 입력해주세요.";

    private final static String INTEGER_PATTERN = "^\\d+$";

    @Override
    protected Date convert(String line) {
        validate(line);
        return parseInt(line);
    }

    private void validate(String line) {
        throwIfIsNotInteger(line);
    }

    private void throwIfIsNotInteger(String line) {
        if (!line.matches(INTEGER_PATTERN)) {
            throw new IllegalArgumentException(ERROR_MESSAGE_INVALID_DATE_INPUT);
        }
    }

    private Date parseInt(String line) {
        try {
            return new Date(Integer.parseInt(line));
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(ERROR_MESSAGE_INVALID_DATE_INPUT);
        }
    }
}
