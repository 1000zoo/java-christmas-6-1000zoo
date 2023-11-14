package christmas.view;

import christmas.constant.KoreanWonFormat;
import christmas.constant.ResultFormat;
import christmas.constant.SummaryMessage;
import christmas.dto.CustomerDto;
import christmas.dto.OrderDto;
import christmas.dto.OrdersDto;

public class CustomerResultPrinter extends OutputView {

    private final OutputView outputView = new OutputView();

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
}
