package christmas.parser;

import christmas.constant.ErrorMessage;

public class IntegerParser {

    public static int parse(String input) {
        validate(input);
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage());
        }
    }

    private static void validate(String input) {
        if (!PatternEnum.POSITIVE_INTEGER.matches(input)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_DATE.getMessage());
        }
    }
}
