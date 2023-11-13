package christmas.controller;

import christmas.configuration.GiveawayMenu;
import christmas.constant.InstructionMessage;
import christmas.constant.KoreanWonFormat;
import christmas.constant.ResultFormat;
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
        outputView.printMessage(ResultFormat.DATE_FORMAT.getFormatMessage(day));
    }

    private void printOrdersResult(OrdersDto ordersDto) {
        outputView.printMessage(SummaryMessage.ORDER_MENU.getMessage());
        ordersDto.orderDtoList().forEach(this::printOrderResult);
        printTotalAmount(ordersDto.totalPrice());
    }

    private void printOrderResult(OrderDto orderDTO) {
        String orderResult = fit(ResultFormat.ORDER_FORMAT, orderDTO.menuName(), orderDTO.quantity());
        outputView.printMessage(orderResult);
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
        printGiveawayMenu(benefitResultDto);
    }

    private void printGiveawayMenu(BenefitResultDto benefitResultDto) {
        if (benefitResultDto.containsGiveaway()) {
            String menuName = GiveawayMenu.INSTANCE.getMenuName();
            outputView.printMessage(ResultFormat.ORDER_FORMAT.getFormatMessage(menuName, 1));
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

    private void printEvent(EventPolicyType type, int amount) {
        if (amount == 0) {
            return;
        }

        String wonFormat = fit(KoreanWonFormat.DISCOUNT, amount);
        String eventFormat = fit(ResultFormat.EVENT_FORMAT, type.getTypeName(), wonFormat);
        outputView.printMessage(eventFormat);
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

    private void printNothing() {
        outputView.printMessage(InstructionMessage.DOES_NOT_EXIST.getMessage());
    }

    private String fit(KoreanWonFormat koreanWonFormat, int amount) {
        return koreanWonFormat.getAmountMessage(amount);
    }

    private String fit(ResultFormat resultFormat, Object... objects) {
        return resultFormat.getFormatMessage(objects);
    }
}
