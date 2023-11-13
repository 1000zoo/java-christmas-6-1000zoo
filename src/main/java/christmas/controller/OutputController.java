package christmas.controller;

import christmas.constant.FormatMessage;
import christmas.constant.InstructionMessage;
import christmas.constant.KoreanWonFormat;
import christmas.constant.SummaryMessage;
import christmas.domain.Badge;
import christmas.domain.discount.EventPolicyType;
import christmas.dto.BenefitResultDto;
import christmas.dto.CustomerDto;
import christmas.dto.OrderDto;
import christmas.dto.OrdersDto;
import christmas.view.OutputView;

public class OutputController {

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
        outputView.printMessage(SummaryMessage.TOTAL_AMOUNT.getMessage());
        outputView.printMessage(KoreanWonFormat.PRICE.getAmountMessage(amount));
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
        outputView.printMessage(SummaryMessage.EVENT_RESULT.getMessage());
        if (benefitResultDto.isEmpty()) {
            printNothing();
            return;
        }
        for (EventPolicyType eventPolicyType : EventPolicyType.values()) {
            int benefitAmount = benefitResultDto.getBenefitOf(eventPolicyType);
            printEvent(eventPolicyType, benefitAmount);
        }
    }

    private void printEvent(EventPolicyType eventPolicyType, int benefitAmount) {
        if (benefitAmount == 0) {
            return;
        }

        String priceWonFormat = KoreanWonFormat.DISCOUNT.getAmountMessage(benefitAmount);
        outputView.printMessage(
                FormatMessage.EVENT_RESULT.getFormatMessage(eventPolicyType.getTypeName(), priceWonFormat));
    }

    public void printTotalBenefitAmount(int benefitAmount) {
        outputView.printMessage(SummaryMessage.BENEFITS_RESULT.getMessage());
        outputView.printMessage(KoreanWonFormat.DISCOUNT.getAmountMessage(benefitAmount));
    }

    public void printAfterDiscount(int amount) {
        outputView.printMessage(SummaryMessage.AFTER_DISCOUNT.getMessage());
        outputView.printMessage(KoreanWonFormat.PRICE.getAmountMessage(amount));
    }

    public void printBadgeResult(Badge badge) {
        outputView.printMessage(SummaryMessage.BADGE_RESULT.getMessage());
        outputView.printMessage(badge.getName());
    }

    private void printNothing() {
        outputView.printMessage(InstructionMessage.DOES_NOT_EXIST.getMessage());
    }
}
