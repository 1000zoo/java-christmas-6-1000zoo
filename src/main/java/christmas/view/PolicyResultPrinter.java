package christmas.view;

import christmas.configuration.GiveawayMenu;
import christmas.constant.KoreanWonFormat;
import christmas.constant.ResultFormat;
import christmas.constant.SummaryMessage;
import christmas.domain.discount.EventPolicyType;
import christmas.dto.BenefitResultDto;

public class PolicyResultPrinter extends OutputView {

    public void printPolicyResult(BenefitResultDto benefitResultDto) {
        printGiveawayEventResult(benefitResultDto);
        printEventList(benefitResultDto);
        printTotalBenefit(benefitResultDto);
    }

    private void printGiveawayEventResult(BenefitResultDto benefitResultDto) {
        printMessage(SummaryMessage.GIVEAWAY_MENU.getMessage());
        printGiveawayMenu(benefitResultDto);
    }

    private void printGiveawayMenu(BenefitResultDto benefitResultDto) {
        if (benefitResultDto.containsGiveaway()) {
            String menuName = GiveawayMenu.INSTANCE.getMenuName();
            printMessage(ResultFormat.ORDER_FORMAT.getFormatMessage(menuName, 1));
            return;
        }
        printNothing();
    }

    private void printEventList(BenefitResultDto benefitResultDto) {
        printMessage(SummaryMessage.EVENT_RESULT.getMessage());
        if (benefitResultDto.isEmpty()) {
            printNothing();
            return;
        }
        for (EventPolicyType eventPolicyType : EventPolicyType.values()) {
            int benefitAmount = benefitResultDto.getBenefitOf(eventPolicyType);
            printEvent(eventPolicyType, benefitAmount);
        }
    }

    private void printEvent(EventPolicyType type, int amount) {
        if (amount == 0) {
            return;
        }

        String wonFormat = fit(KoreanWonFormat.DISCOUNT, amount);
        String eventFormat = fit(ResultFormat.EVENT_FORMAT, type.getTypeName(), wonFormat);
        printMessage(eventFormat);
    }

    private void printTotalBenefit(BenefitResultDto benefitResultDto) {
        printMessage(SummaryMessage.BENEFITS_RESULT.getMessage());
        printMessage(fit(KoreanWonFormat.DISCOUNT, benefitResultDto.getTotalBenefit()));
    }
}
