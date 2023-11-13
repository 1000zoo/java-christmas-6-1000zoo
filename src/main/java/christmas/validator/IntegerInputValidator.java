package christmas.validator;

import christmas.constant.ErrorMessage;

public class IntegerInputValidator implements Validator<String> {

    private final static String NO_INTEGER_INPUT_ERROR_MESSAGE = "유효하지 않은 날짜입니다. 다시 입력해 주세요.";

    private final static String INTEGER_PATTERN = "^\\d+$";

    public void validate(String target) {
        throwIfIsNotInteger(target);
        throwIfInvalidInteger(target);
    }

    private void throwIfIsNotInteger(String target) {
        if (!target.matches(INTEGER_PATTERN)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_DATE.getMessage());
        }
    }

    private void throwIfInvalidInteger(String target) {
        try {
            Integer.parseInt(target);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_DATE.getMessage());
        }
    }
}
