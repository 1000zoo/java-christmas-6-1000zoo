package christmas.view;

import christmas.constant.KoreanWonFormat;
import christmas.constant.SummaryMessage;
import christmas.domain.Badge;

public class BenefitsResultPrinter extends OutputView {

    public void printTotalBenefitAmount(int amount) {
        printMessage(SummaryMessage.BENEFITS_RESULT.getMessage());
        printMessage(fit(KoreanWonFormat.DISCOUNT, amount));
    }

    public void printAfterDiscount(int amount) {
        printMessage(SummaryMessage.AFTER_DISCOUNT.getMessage());
        printMessage(fit(KoreanWonFormat.PRICE, amount));
        printMessage(KoreanWonFormat.PRICE.getAmountMessage(amount));
    }

    public void printBadgeResult(Badge badge) {
        printMessage(SummaryMessage.BADGE_RESULT.getMessage());
        printMessage(badge.getName());
    }
}
