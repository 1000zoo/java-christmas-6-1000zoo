package christmas.view.output;

import christmas.constant.KoreanWonFormat;
import christmas.constant.SummaryMessage;
import christmas.domain.Badge;
import christmas.dto.CustomerDto;

public class CustomerResultPrinter extends OutputView {

    public void printCustomerResult(CustomerDto customerDTO) {
        printAfterDiscount(customerDTO.afterDiscountPrice());
        printBadgeResult(customerDTO.badge());
    }

    private void printAfterDiscount(int amount) {
        printMessage(SummaryMessage.AFTER_DISCOUNT.getMessage());
        printMessage(fit(KoreanWonFormat.PRICE, amount));
    }

    private void printBadgeResult(Badge badge) {
        printMessage(SummaryMessage.BADGE_RESULT.getMessage());
        printMessage(badge.getName());
    }
}
