package christmas.view;

public class OutputView {

    private final static String NEW_LINE = "%n";
    private final static String ERROR_PREFIX = "[ERROR] ";

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printMessage(String format, Object... objects) {
        System.out.printf((format) + NEW_LINE, objects);
    }

    public void printErrorMessage(String message) {
        System.out.println(ERROR_PREFIX + message);
    }
}
