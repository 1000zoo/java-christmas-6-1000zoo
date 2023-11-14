package christmas.view.output;

import christmas.constant.KoreanWonFormat;
import christmas.constant.SummaryMessage;
import christmas.domain.Badge;
import christmas.dto.CustomerDTO;

public class CustomerResultPrinter extends OutputView {

    public void printCustomerResult(CustomerDTO customerDTO) {
        printAfterDiscount(customerDTO.afterDiscountPrice());
        printBadgeResult(customerDTO.badge());
    }

    public void printAfterDiscount(int amount) {
        printMessage(SummaryMessage.AFTER_DISCOUNT.getMessage());
        printMessage(fit(KoreanWonFormat.PRICE, amount));
    }

    public void printBadgeResult(Badge badge) {
        printMessage(SummaryMessage.BADGE_RESULT.getMessage());
        printMessage(badge.getName());
    }
}
