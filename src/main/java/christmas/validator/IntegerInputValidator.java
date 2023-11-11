package christmas.validator;

public class IntegerInputValidator implements Validator<String> {

    private final static String NO_INTEGER_INPUT_ERROR_MESSAGE = "숫자만 입력해주세요.";
    private final static String INVALID_INTEGER_INPUT_ERROR_MESSAGE = "올바른 입력이 아닙니다.";

    private final static String INTEGER_PATTERN = "^\\d+$";

    public void validate(String target) {
        throwIfIsNotInteger(target);
        throwIfInvalidInteger(target);
    }

    private void throwIfIsNotInteger(String target) {
        if (!target.matches(INTEGER_PATTERN)) {
            throw new IllegalArgumentException(NO_INTEGER_INPUT_ERROR_MESSAGE);
        }
    }

    private void throwIfInvalidInteger(String target) {
        try {
            Integer.parseInt(target);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(INVALID_INTEGER_INPUT_ERROR_MESSAGE);
        }
    }
}
