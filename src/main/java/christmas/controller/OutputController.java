package christmas.controller;

import christmas.constant.InstructionMessage;
import christmas.constant.KoreanWonFormat;
import christmas.constant.SummaryMessage;
import christmas.domain.Badge;
import christmas.dto.BenefitResultDto;
import christmas.dto.CustomerDto;
import christmas.view.CustomerResultPrinter;
import christmas.view.OutputView;
import christmas.view.PolicyResultPrinter;

public class OutputController {

    private final OutputView outputView;

    public OutputController() {
        outputView = new OutputView();
    }

    public void printWelcomeMessage() {
        outputView.printMessage(InstructionMessage.WELCOME.getMessage());
    }

    public void printCustomerResults(CustomerDto customerDTO) {
        CustomerResultPrinter printer = new CustomerResultPrinter();
        printer.printCustomerResults(customerDTO);
    }

    public void printPolicyResult(BenefitResultDto benefitResultDto) {
        PolicyResultPrinter printer = new PolicyResultPrinter();
        printer.printPolicyResult(benefitResultDto);
    }

    public void printTotalBenefitAmount(int amount) {
        outputView.printMessage(SummaryMessage.BENEFITS_RESULT.getMessage());
        outputView.printMessage(fit(KoreanWonFormat.DISCOUNT, amount));
    }

    public void printAfterDiscount(int amount) {
        outputView.printMessage(SummaryMessage.AFTER_DISCOUNT.getMessage());
        outputView.printMessage(fit(KoreanWonFormat.PRICE, amount));
        outputView.printMessage(KoreanWonFormat.PRICE.getAmountMessage(amount));
    }

    public void printBadgeResult(Badge badge) {
        outputView.printMessage(SummaryMessage.BADGE_RESULT.getMessage());
        outputView.printMessage(badge.getName());
    }

    private String fit(KoreanWonFormat koreanWonFormat, int amount) {
        return koreanWonFormat.getAmountMessage(amount);
    }
}
