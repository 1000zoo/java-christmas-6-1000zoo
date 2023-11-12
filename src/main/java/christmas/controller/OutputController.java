package christmas.controller;

import christmas.dto.CustomerDTO;
import christmas.dto.OrderDTO;
import christmas.dto.OrderDTOs;
import christmas.view.output.OutputView;
import java.text.DecimalFormat;

public class OutputController {

    private final static String WELCOME_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private final static String DATE_RESULT_FORMAT = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    private final static String ORDER_MENU_INSTRUCTION = "<주문 메뉴>";
    private final static String ORDER_RESULT_FORMAT = "%s %d개";
    private final static String TOTAL_AMOUNT_INSTRUCTION = "<할인 전 총주문 금액>";
    private final static String KOREAN_WON_FORMAT = "#,###원";
    private final static String GIVEAWAY_MENU_INSTRUCTION = "<증정 메뉴>";
    private final static String EVENT_RESULT_INSTRUCTION = "<혜택 내역>";
    private final static String BENEFITS_RESULT_INSTRUCTION = "<총혜택 금액>";
    private final static String AFTER_DISCOUNT_INSTRUCTION = "<할인 후 예상 결제 금액>";
    private final static String BADGE_RESULT_INSTRUCTION = "<12월 이벤트 배지>";

    private final OutputView outputView;

    public OutputController() {
        outputView = new OutputView();
    }

    public void printWelcomeMessage() {
        outputView.printMessage(WELCOME_MESSAGE);
    }

    public void printCustomerResults(CustomerDTO customerDTO) {
        printDateResult(customerDTO.day());
        printOrdersResult(customerDTO.orderDTOs());
    }

    private void printDateResult(int day) {
        outputView.printMessage(DATE_RESULT_FORMAT, day);
    }

    private void printOrdersResult(OrderDTOs orderDTOs) {
        outputView.printMessage(ORDER_MENU_INSTRUCTION);
        orderDTOs.orderDTOs().forEach(this::printOrderResult);
        printTotalAmount(orderDTOs.totalPrice());
    }

    private void printTotalAmount(int amount) {
        DecimalFormat formatter = new DecimalFormat(KOREAN_WON_FORMAT);
        outputView.printMessage(TOTAL_AMOUNT_INSTRUCTION);
        outputView.printMessage(formatter.format(amount));
    }

    private void printOrderResult(OrderDTO orderDTO) {
        outputView.printMessage(ORDER_RESULT_FORMAT, orderDTO.menuName(), orderDTO.quantity());
    }

    public void printPolicyResult() {
        printGiveawayEventResult();
        printDiscountResult();
    }

    private void printGiveawayEventResult() {
        // 증정 메뉴 출력
    }

    private void printDiscountResult() {
        // 할인 내역 출력
        // 총 혜택 금액 출력
        // 할인 후 예상 결제 금액 출력
    }

    public void printBadgeResult() {
        // 배지 결과 출력
    }

}
