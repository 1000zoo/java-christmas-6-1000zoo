package christmas.controller;

import christmas.constant.FormatMessage;
import christmas.constant.InstructionMessage;
import christmas.constant.SummaryMessage;
import christmas.domain.Badge;
import christmas.domain.discount.EventPolicyType;
import christmas.dto.BenefitResultDto;
import christmas.dto.CustomerDto;
import christmas.dto.OrderDto;
import christmas.dto.OrdersDto;
import christmas.view.OutputView;
import java.text.DecimalFormat;

public class OutputController {

    private final static String KOREAN_WON_FORMAT = "#,###원";
    private final static String EVENT_RESULTS_FORMAT = "%s: %s";
    private final static String BENEFIT_KOREAN_WON_FORMAT = "-#,###원";

    private final OutputView outputView;

    public OutputController() {
        outputView = new OutputView();
    }

    public void printWelcomeMessage() {
        outputView.printMessage(InstructionMessage.WELCOME.getMessage());
    }

    public void printCustomerResults(CustomerDto customerDTO) {
        printDateResult(customerDTO.day());
        printOrdersResult(customerDTO.ordersDto());
    }

    private void printDateResult(int day) {
        outputView.printMessage(FormatMessage.DATE_RESULT.getFormatMessage(day));
    }

    private void printOrdersResult(OrdersDto ordersDto) {
        outputView.printMessage(SummaryMessage.ORDER_MENU.getMessage());
        ordersDto.orderDtoList().forEach(this::printOrderResult);
        printTotalAmount(ordersDto.totalPrice());
    }

    private void printOrderResult(OrderDto orderDTO) {
        outputView.printMessage(
                FormatMessage.ORDER_RESULT.getFormatMessage(orderDTO.menuName(), orderDTO.quantity())
        );
    }

    private void printTotalAmount(int amount) {
        DecimalFormat formatter = new DecimalFormat(KOREAN_WON_FORMAT);
        outputView.printMessage(SummaryMessage.TOTAL_AMOUNT.getMessage());
        outputView.printMessage(formatter.format(amount));
    }

    public void printPolicyResult(BenefitResultDto benefitResultDto) {
        printGiveawayEventResult(benefitResultDto);
        printEventList(benefitResultDto);
    }

    private void printGiveawayEventResult(BenefitResultDto benefitResultDto) {
        outputView.printMessage(SummaryMessage.GIVEAWAY_MENU.getMessage());
        if (benefitResultDto.containsGiveaway()) {
            outputView.printMessage("샴페인 1개");
            return;
        }
        printNothing();
    }

    private void printEventList(BenefitResultDto benefitResultDto) {
        DecimalFormat formatter = new DecimalFormat(BENEFIT_KOREAN_WON_FORMAT);
        outputView.printMessage(SummaryMessage.EVENT_RESULT.getMessage());
        if (benefitResultDto.isEmpty()) {
            printNothing();
            return;
        }
        for (EventPolicyType eventPolicyType : EventPolicyType.values()) {
            String typeName = eventPolicyType.getTypeName();
            int benefitAmount = benefitResultDto.getBenefitOf(eventPolicyType);
            if (benefitAmount == 0) {
                continue;
            }
            String benefitMessage = formatter.format(benefitAmount);
            outputView.printMessage(EVENT_RESULTS_FORMAT, typeName, benefitMessage);
        }
    }

    public void printTotalBenefitAmount(int benefitAmount) {
        DecimalFormat formatter = new DecimalFormat(BENEFIT_KOREAN_WON_FORMAT);
        outputView.printMessage(SummaryMessage.BENEFITS_RESULT.getMessage());
        outputView.printMessage(formatter.format(benefitAmount));
    }

    public void printAfterDiscount(int amount) {
        DecimalFormat formatter = new DecimalFormat(KOREAN_WON_FORMAT);
        outputView.printMessage(SummaryMessage.AFTER_DISCOUNT.getMessage());
        outputView.printMessage(formatter.format(amount));
    }

    public void printBadgeResult(Badge badge) {
        outputView.printMessage(SummaryMessage.BADGE_RESULT.getMessage());
        outputView.printMessage(badge.getName());
    }

    private void printNothing() {
        outputView.printMessage("없음");
    }
}
