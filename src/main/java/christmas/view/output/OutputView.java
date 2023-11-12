package christmas.view.output;

public class OutputView {

    private final static String ERROR_PREFIX = "[ERROR] ";

    private OutputView() {
    }

    public static void printMessage(String message) {
        System.out.println(message);
    }

    public static void printErrorMessage(String message) {
        System.out.println(ERROR_PREFIX + message);
    }
}
