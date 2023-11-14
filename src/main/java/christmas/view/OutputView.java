package christmas.view;

import christmas.constant.KoreanWonFormat;
import christmas.constant.ResultFormat;

public class OutputView {

    private final static String ERROR_PREFIX = "[ERROR] ";

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printErrorMessage(String message) {
        System.out.println(ERROR_PREFIX + message);
    }

    protected String fit(KoreanWonFormat koreanWonFormat, int amount) {
        return koreanWonFormat.getAmountMessage(amount);
    }

    protected String fit(ResultFormat resultFormat, Object... objects) {
        return resultFormat.getFormatMessage(objects);
    }
}
