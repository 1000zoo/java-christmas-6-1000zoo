package christmas.view.output;

public class Output {

    private final static String ERROR_PREFIX = "[ERROR] ";

    private Output() {
    }

    public static void printMessage(String message) {
        System.out.println(message);
    }

    public static void printErrorMessage(String message) {
        System.out.println(ERROR_PREFIX + message);
    }
}
