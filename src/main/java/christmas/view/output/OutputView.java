package christmas.view.output;

import christmas.constant.InstructionMessage;
import christmas.constant.KoreanWonFormat;
import christmas.constant.ResultFormat;

public class OutputView {

    public void printMessage(String message) {
        System.out.println(message);
    }

    protected void printNothing() {
        printMessage(InstructionMessage.DOES_NOT_EXIST.getMessage());
    }

    protected String fit(KoreanWonFormat koreanWonFormat, int amount) {
        return koreanWonFormat.getAmountMessage(amount);
    }

    protected String fit(ResultFormat resultFormat, Object... objects) {
        return resultFormat.getFormatMessage(objects);
    }

}
