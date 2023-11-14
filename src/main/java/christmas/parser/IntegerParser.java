package christmas.parser;

public class IntegerParser {

    public static int parse(String input, String errorMessage) {
        validate(input, errorMessage);
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    private static void validate(String input, String errorMessage) {
        if (!PatternEnum.POSITIVE_INTEGER.matches(input)) {
            throw new IllegalArgumentException(errorMessage);
        }
    }
}
